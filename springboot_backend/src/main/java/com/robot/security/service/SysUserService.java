package com.robot.security.service;

import com.robot.security.service.dto.SysUserDto;
import com.robot.security.service.dto.SysUserQueryDto;
import com.robot.security.domain.SysRole;
import com.robot.security.domain.SysUser;
import com.robot.security.service.dto.PermissionDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface SysUserService {

    /**
     * create user
     * @param user new user
     */
    SysUser create(SysUser user);

    /**
     * delete user from system
     */
    SysUser delete(SysUser user);

    /**
     * update user info
     */
    SysUser update(SysUser user);

    /**
     * find user based on ip
     * @return  related user
     */
    SysUser findById(Long id);

    /**
     * find user based on user namae
     * @param userName
     * @return related user
     */
    SysUser findByUserName(String userName);

    /**
     * check email whether exist in the system
     * @param email
     * @return ture - > exist, false -> do not exist
     */
    boolean registerEmailExist(String email);

    /**
     * get user info
     * @return user info
     */
    UserDetails getUserInfo();

    // TODO: delete
    /**
     * 修改用户头像
     * @param file 文件
     * @return json
     */
    Map<String, String> updateAvatar(MultipartFile file);

    /**
     * get user role
     */
    List<SysRole> getUserRoles(Long userId);

    /**
     * 保存用户角色
     *
     * @param userId 用户id
     * @param roleIds 角色id列表
     * @return 是否成功
     */
    Boolean saveUserRoles(Long userId,Set<Long> roleIds);

    /**
     * 获取用户权限信息
     *
     * @param userId 用户id
     * @return 权限信息
     */
    List<PermissionDto> getUserPermission(Long userId);

    /**
     * 根据条件查询用户信息，并返回全部用户列表
     *
     * @param sysUserQueryDto 查询条件
     * @return 用户列表
     */
    List<SysUser> list(SysUserQueryDto sysUserQueryDto);

    /**
     * 根据条件查询用户信息,并返回分页用户列表
     * @param sysUserQueryDto 查询条件
     * @return 分页用户数据
     */
    SysUserDto page(SysUserQueryDto sysUserQueryDto);

    /**
     * 批量删除用户
     *
     * @param ids 待删除的用户id列表
     * @return 是否成功
     */
    Boolean delete(Set<Long> ids);

}
