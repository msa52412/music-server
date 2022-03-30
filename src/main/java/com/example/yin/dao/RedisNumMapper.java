package com.example.yin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public class RedisNumMapper {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Async
    public Object numincur(String key){
        if (redisTemplate.hasKey(key)){
           return redisTemplate.opsForValue().increment(key);
        }
        else {
         redisTemplate.opsForValue().set(key,1);
         return 1;
        }
    }
}
