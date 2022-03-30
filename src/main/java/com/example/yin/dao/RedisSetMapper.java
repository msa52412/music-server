package com.example.yin.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisSetMapper {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    public boolean setSet(String redisKey,Object values){
        if (StringUtils.isBlank(redisKey)) {
            return false;
        }
        try {
            redisTemplate.opsForSet().add(redisKey,values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isin(String redisKey,Object values){
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(redisKey,values));
    }
}
