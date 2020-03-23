package com.example.bootdemo.units;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisUnits {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 默认国企时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60*60*24;

    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1;

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean existsKey(String key){
        return redisTemplate.hasKey(key);
    }



}
