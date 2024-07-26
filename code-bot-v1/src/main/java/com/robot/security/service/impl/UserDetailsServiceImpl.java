/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.robot.security.service.impl;

import com.robot.security.domain.SysRole;
import com.robot.security.domain.SysUser;
import com.robot.security.service.SysUserService;
import com.robot.security.service.dto.JwtUserDto;
import com.robot.security.service.dto.PermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

 /*
  * user authentication
  */
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SysUserService sysUserService;


    @Override
    // 该函数由spring security 自动调用
    public JwtUserDto loadUserByUsername(String username) {
        // 根据用户名从数据库加载用户信息
        SysUser user;
        try {
            user = sysUserService.findByUserName(username);
        } catch (RuntimeException e) {
            // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
            throw new UsernameNotFoundException("cannot find the user", e);
        }
        if (user == null) {
            throw new UsernameNotFoundException("cannot find the user");
        } else {
            if (!user.getEnabled()) {
                throw new RuntimeException("account is inactive");
            }
            return new JwtUserDto(
                    user,
                    null,
                    sysUserService.getUserRoles(user.getId()).stream().map(SysRole::getRoleCode).collect(Collectors.toList()),
                    AuthorityUtils.commaSeparatedStringToAuthorityList(
                            sysUserService.getUserPermission(user.getId()).stream().map(PermissionDto::getPath).collect(Collectors.joining(",")))
            );
        }
    }
}
