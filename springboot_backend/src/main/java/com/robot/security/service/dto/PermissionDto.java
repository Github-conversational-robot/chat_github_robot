package com.robot.security.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@ApiModel(value = "用户权限信息")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "路由")
    private String path;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组件路径")
    private String component;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否缓存")
    private Boolean cache;

    @ApiModelProperty(value = "重定向")
    private String redirect;

    @ApiModelProperty(value = "父id")
    private Long pId;


}
