package com.robot.security.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@ApiModel(value = "仓库信息表")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@TableName("github_repository")
public class Repository {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 仓库名称
    private String name;
    // 仓库地址
    private String address;
    // 创建时间
    private Timestamp createTime;
}
