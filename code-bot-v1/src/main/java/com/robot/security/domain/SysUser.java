package com.robot.security.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@ApiModel(value = "user information")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userName;

    @JsonIgnore
    private String password;

    private String nickName;

    /**
     * gender 0-unknown 1-male,2-female
     */
    private Integer gender;

    /**
     * 头像地址
     */
    private String avatarUrl;

    private String country;

    private String province;

    private String city;


    @Email
    private String email;

    private String phone;

    private String remarks;

    @TableLogic
    private Boolean enabled;

    private Timestamp lastPasswordResetTime;

    private Timestamp createTime;

    @Builder.Default
    private Timestamp updateTime = Timestamp.valueOf(LocalDateTime.now());

}