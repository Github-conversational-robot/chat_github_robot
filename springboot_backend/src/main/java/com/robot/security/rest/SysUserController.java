package com.robot.security.rest;

import com.robot.security.domain.SysUser;
import com.robot.security.service.SysUserService;
import com.robot.security.service.dto.SysUserQueryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Api(tags = "用户信息接口")
public class SysUserController {

    private final SysUserService sysUserService;

    @ApiOperation("获取当前登录用户信息")
    @GetMapping()
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(sysUserService.getUserInfo());
    }


    @ApiOperation("获取用户权限")
    @GetMapping("/permission/{userId}")
    public ResponseEntity<Object> getUserPermission(@PathVariable Long userId) {
        return ResponseEntity.ok(sysUserService.getUserPermission(userId));
    }

}
