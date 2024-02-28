package com.robot.utils;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
@AllArgsConstructor
public class RedisUtils {
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 普通获取键对应值
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通设置键值
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 普通设置键值并设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 键
     * @return 是否成功
     */
    public boolean del(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 指定缓存的失效时间 (分钟）
     *
     * @param key  键值 @NotNull
     * @param time 时间(秒) @NotNull
     */
    public boolean expireSecond(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 指定缓存的失效时间 (小时)
     *
     * @param key  键值 @NotNull
     * @param time 时间(秒) @NotNull
     */
    public boolean expireHour(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.HOURS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 往list中 right push 内容
     */
    public boolean rpush(String key, String value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取键对应list
     * @param num 多少
     * @param key 键
     * @return list
     */
    public List<String> getLastElements(String key, int num) {
        List<String> arr = new ArrayList<>();
        // TODO: test whether this is valid change object to the string
        for(int i = 0; i < num; i++)
            arr.add((String)redisTemplate.opsForList().index(key, -i));
        return arr;
    }

    /**
     * pop all messages in the list
     */
    public List<String> popAllElements(String key){
        List<String> arr = new ArrayList<>();
        String mes = null;
        while (true) {
            mes = (String) redisTemplate.opsForList().rightPop(key);
            if(mes != null) arr.add(mes);
            else break;
        }
        return arr;
    }

    /**
     * get all the keys with the same pattern
     */
    public Set<Object> findPatternKey(String pattern){
        return redisTemplate.keys(pattern);
    }


}
