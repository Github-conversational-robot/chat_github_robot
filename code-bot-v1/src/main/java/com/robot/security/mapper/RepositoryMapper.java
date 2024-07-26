package com.robot.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robot.security.domain.Repository;
import com.robot.security.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RepositoryMapper  extends BaseMapper<Repository>{
}
