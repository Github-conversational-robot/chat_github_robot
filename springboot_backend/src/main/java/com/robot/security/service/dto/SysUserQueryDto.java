package com.robot.security.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "用户查询条件")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserQueryDto {

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "注册起始时间")
    private Long createTimeStart;

    @ApiModelProperty(value = "注册结束时间")
    private Long createTimeEnd;

    @ApiModelProperty(value = "当前页数")
    private Integer currentPage;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;
}
