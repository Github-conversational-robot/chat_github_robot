package com.robot.security.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;


@ApiModel(value = "用户仓库信息")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user_repository")
public class SysUserRep {

    // key primary
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String user_email;

    private String repository_address;

    private String repository_name;

    private Timestamp createTime;

}
