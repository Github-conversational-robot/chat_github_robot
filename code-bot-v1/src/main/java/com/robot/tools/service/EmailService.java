package com.robot.tools.service;

import com.robot.tools.service.dto.EmailDto;


public interface EmailService {

    /**
     * 发送邮件
     * @param emailDto 邮箱列表
     */
    void send(EmailDto emailDto);
}
