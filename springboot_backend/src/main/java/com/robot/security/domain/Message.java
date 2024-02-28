package com.robot.security.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(value = "对话信息表")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("github_repository")
public class Message {
    String username;
    String message;
}
