package com.robot.security.security;

import com.robot.security.config.JwtSecurityProperties;
import com.robot.security.utils.JwtTokenUtils;
import com.robot.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private JwtTokenUtils jwtTokenUtils;

    public JwtAuthenticationTokenFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        JwtSecurityProperties jwtSecurityProperties = SpringContextHolder.getBean(JwtSecurityProperties.class);
        String requestRri = httpServletRequest.getRequestURI();
        //get request token
        String token = null;
        String bearerToken = httpServletRequest.getHeader(jwtSecurityProperties.getHeader());
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtSecurityProperties.getTokenStartWith())) {
            token = bearerToken.substring(jwtSecurityProperties.getTokenStartWith().length());
        }
        // check wehtehr token is valid
        if (StringUtils.hasText(token) && jwtTokenUtils.validateToken(token)) {
            Authentication authentication = jwtTokenUtils.getAuthentication(token);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestRri);
            }
        } else {
            log.debug("no valid JWT token found, uri: {}", requestRri);
        }
        // 放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
