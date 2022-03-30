package com.example.yin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class HotSongImpl {
    @Autowired
    RedisTemplate redisTemplate;
    public Object getHotSong(){
        if (redisTemplate.hasKey("rangeOfSong")){
            return redisTemplate.opsForZSet().reverseRangeWithScores("rangeOfSong",0,100);
        }
        else  return null;
    }

}
