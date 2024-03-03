package com.robot.security.controller;

import com.robot.security.service.AuthService;
import com.robot.security.service.dto.AuthUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;



@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = "system authentication interface")
public class AuthController {
    @Autowired
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("send code to the email")
    @PostMapping(value = "/getEmailCode")
    public ResponseEntity<Object> getEmailCode(@RequestParam String email) {
        authService.sendMailCode(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("register")
    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@RequestBody AuthUserDto authUserDto) {
        return ResponseEntity.ok(authService.register(authUserDto));
    }

    @ApiOperation("login")
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody AuthUserDto authUserDto) {
        return ResponseEntity.ok(authService.login(authUserDto));
    }

    @ApiOperation("logout")
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        authService.logout(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
