package com.robot.security.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "用户发送信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChatMesDto {
    private String filePath;
    private String inputMes;
}
