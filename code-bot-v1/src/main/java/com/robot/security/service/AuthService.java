package com.robot.security.service;

import com.robot.security.service.dto.AuthUserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public interface AuthService {

    /**
     * @param authUserDto authentication user information
     * @return whether success
     */
    boolean register(AuthUserDto authUserDto);

    /**
     * @param authUserDto authentication user information
     * @return //TODO
     */
    Map<String, Object> login(AuthUserDto authUserDto);

    /**
     * send code to the email
     * @param email number
     */
    void sendMailCode(String email);

    /**
     * @param request Http request
     */
    void logout(HttpServletRequest request);
}
