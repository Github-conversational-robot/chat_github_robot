package com.robot.security.security;



import com.robot.security.domain.Message;
import com.robot.security.mapper.MesStoreMapper;
import com.robot.security.mapper.RepositoryMapper;
import com.robot.utils.RedisUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
@EnableScheduling
@Slf4j
public class ChatMessageSaveTask {

    private final RedisUtils redisUtils;
    private final MesStoreMapper mesStoreMapper;

    /**
     * 定时任务，每天凌晨3点进行redis缓存的持久化，将聊天记录保存到数据库
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void chatMessageSave() {
        log.info("【定时任务】 开始！");
        // get all the keys
        Set<Object> keys = redisUtils.findPatternKey("*-chat");
        // 获取缓存中所有chat messages
        for (Object key : keys) {
            List<String> gm = redisUtils.popAllElements((String) key);
            // sotre
            if (gm != null) {
                try {
                    String useKey = (String) key;
                    String username = useKey.substring(0, useKey.length() - 5);
                    for (String chat : gm) {
                        Message mes = new Message();
                        mes.setUsername(username);
                        mes.setMessage(chat);
                        mesStoreMapper.insert(mes);
                    }
                    // 清空缓存
                    redisUtils.del((String) key);
                    log.info("【定时任务】 " + gm.size() + "条消息被更新到mysql" + "[用户]" + (String) key);
                    log.info("【定时任务】 " + gm.size() + "条消息被清空" + "[用户]" + (String) key);
                } catch (Exception e) {
                    log.error("【定时任务】 Redis出错！");
                }
            } else {
                log.info("【定时任务】 聊天记录持久化到MySQL。");
            }
        }
    }
}
