package com.robot.security.service.impl;

import com.robot.security.service.MesStoreService;
import com.robot.security.service.SysUserRepService;
import com.robot.utils.RedisUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MesStoreServiceImpl implements MesStoreService{

    private final RedisUtils redisUtils;
    private final SysUserRepService sysUserRepService;

    @Override
    public List<String> getHistoryMes() {
        String userName = sysUserRepService.getCurrentLoginUserName();
        List<String> messages = redisUtils.getLastElements(userName + "-history",15);
        // delete null messages
        int length = 0;
        for(String mes : messages) if(mes != null) length++;
        return messages.subList(0, length);
    }

    @Override
    public void storeMes(String message) {
        String historykey = sysUserRepService.getCurrentLoginUserName() + "-history";
        String chatkey = sysUserRepService.getCurrentLoginUserName() + "-chat";
        // store in history / expire
        redisUtils.rpush(historykey, message);
        redisUtils.expireHour(historykey, 48);
        // store in chat / expire
        redisUtils.rpush(chatkey, message);
        redisUtils.expireHour(historykey, 25);
    }

    @Override
    public List<String> getChatMes() {
        String userName = sysUserRepService.getCurrentLoginUserName();
        List<String> messages = redisUtils.getLastElements(userName + "-history",15);
        // delete null messages
        int length = 0;
        for(String mes : messages) if(mes != null) length++;
        return messages.subList(0, length);
    }
}
