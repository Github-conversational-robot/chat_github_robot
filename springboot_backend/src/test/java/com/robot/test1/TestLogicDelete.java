package com.robot.test1;

import com.robot.security.mapper.SysUserMapper;
import com.robot.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class TestLogicDelete {

    @Test
    void test() {
        SysUserMapper sysUserMapper = SpringContextHolder.getBean(SysUserMapper.class);
        sysUserMapper.deleteById(15L);
    }
}
