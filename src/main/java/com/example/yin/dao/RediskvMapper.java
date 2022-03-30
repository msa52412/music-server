package com.example.yin.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RediskvMapper {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    public boolean setStringkv(String key,Object value){
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Object getStringkv(String key){
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean isin(String key){
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
    public boolean delete(String key){
        int i=10;
        while (i>0) {
            i--;
            boolean flag1 = redisTemplate.delete(key);
            if (flag1) {
                return true;
            }
        }
       return false;
    }
    public boolean setStringTkv(String key,Object value){
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value,60, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
