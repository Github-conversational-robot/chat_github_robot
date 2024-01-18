package com.robot.security.service.impl;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.robot.security.config.JwtSecurityProperties;
import com.robot.security.domain.SysUser;
import com.robot.security.service.AuthService;
import com.robot.security.service.SysUserService;
import com.robot.security.utils.JwtTokenUtils;
import com.robot.tools.service.EmailService;
import com.robot.tools.service.dto.EmailDto;
import com.robot.utils.RedisUtils;
import com.robot.utils.RsaUtils;
import com.robot.security.service.dto.AuthUserDto;
import com.robot.security.service.dto.JwtUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {

    @Value("${rsa.private-key}")
    private String privateKey;
    @Value("${code.expiration}")
    private Long expiration;

    private final JwtTokenUtils jwtTokenUtils;
    private final SysUserService userService;
    private final JwtSecurityProperties properties;
    private final RedisUtils redisUtils;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final SysUserService sysUserService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean register(AuthUserDto authUserDto) {
        // based on register get the qr code in redis
        Object value = redisUtils.get(authUserDto.getEmail());
        if (value == null || !value.toString().equals(authUserDto.getCode())) {
            throw new RuntimeException("无效验证码");
        } else {
            redisUtils.del(authUserDto.getEmail());
        }

        // default: email is username
        String userName = StringUtils.isEmpty(authUserDto.getUserName()) ? authUserDto.getEmail() : authUserDto.getUserName();

        if (userService.findByUserName(userName) != null) {
            throw new RuntimeException("用户名已存在");
        }

        // create a user
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        sysUser.setCreateTime(Timestamp.valueOf(LocalDateTime.now()));
        try {
            sysUser.setPassword(passwordEncoder.encode(RsaUtils.decryptByPrivateKey(privateKey, authUserDto.getPassword())));
        } catch (Exception e) {
            throw new RuntimeException("注册密码异常");
        }
        sysUser.setEmail(authUserDto.getEmail());
        return userService.create(sysUser) != null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> login(AuthUserDto authUserDto, HttpServletRequest request) {

        try {
            String password = RsaUtils.decryptByPrivateKey(privateKey, authUserDto.getPassword());
            // use username and password to create a token
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authUserDto.getUserName(), password);
            // valid the username and password whether correct
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // generate the token
            String token = jwtTokenUtils.createToken(authentication);
            // token -> jwtUserDto
            JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
            redisUtils.set(token, jwtUserDto, properties.getTokenValidityInSeconds() / 1000);
            return new HashMap<String, Object>(2) {{
                put("token", properties.getTokenStartWith() + token);
                put("user", jwtUserDto);
            }};
        } catch (Exception ex) {
            throw new RuntimeException("异常错误：" + ex.getMessage());
        }

    }

    @Override
    public void sendMailCode(String email) {

        // check whether email has existed
        if (sysUserService.registerEmailExist(email)) {
            throw new RuntimeException("注册邮箱已存在");
        }

        // html mode for send email back
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));
        Template template = engine.getTemplate("email-code.ftl");

        // get the code from the redis
        Object code = redisUtils.get(email);
        if (code == null) {
            // create a code fot the user
            code = RandomUtil.randomNumbers(6);
            if (!redisUtils.set(email, code, expiration)) {
                throw new RuntimeException("后台缓存服务异常");
            }
        }
        // send code
        emailService.send(new EmailDto(Collections.singletonList(email),
                "邮箱验证码", template.render(Dict.create().set("code", code))));

    }

    @Override
    public void logout(HttpServletRequest request) {
        redisUtils.del(jwtTokenUtils.getToken(request));
    }
}
