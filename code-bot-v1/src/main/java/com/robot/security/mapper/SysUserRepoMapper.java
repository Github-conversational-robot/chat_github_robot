package com.robot.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robot.security.domain.SysUserRep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface SysUserRepoMapper extends BaseMapper<SysUserRep> {
    /**
     * 查询用户的仓库列表
     * @param email_address
     * @return 仓库列表
     */
    @Select("select r.repository_name " +
            "from sys_user_repository r " +
            "where r.user_email = #{email_address}")
    List<String> selectUserReps(String email_address);
}
