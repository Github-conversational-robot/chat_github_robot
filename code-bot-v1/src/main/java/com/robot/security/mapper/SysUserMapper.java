package com.robot.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robot.security.domain.SysRole;
import com.robot.security.domain.SysUser;
import com.robot.security.service.dto.PermissionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * slect user role
     * @param userId user id
     * @return user information
     */
    @Select("select r.id,r.role_code,r.role_name,r.description,r.enabled,r.create_time,r.update_time " +
            "from sys_role r " +
            "INNER JOIN sys_user_role ur ON r.id=ur.role_id where ur.user_id=#{userId} ")
    List<SysRole> selectUserRoles(Long userId);

    /**
     * select usrers' permission
     * @param userId  user id
     * @return permission information
     */
    @Select(" SELECT * " +
            " FROM " +
            " sys_menu " +
            " WHERE " +
            " id IN ( SELECT menu_id FROM sys_permission WHERE role_id IN ( SELECT role_id FROM sys_user_role WHERE user_id =#{userId})) " +
            " and sys_menu.enabled=1 order by sys_menu.sort  "
    )
    List<PermissionDto> selectUserPermission(Long userId);
}
