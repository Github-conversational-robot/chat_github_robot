package com.robot.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.robot.security.domain.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MesStoreMapper extends BaseMapper<Message> {
}
