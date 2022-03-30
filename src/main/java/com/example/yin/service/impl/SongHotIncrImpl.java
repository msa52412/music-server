package com.example.yin.service.impl;

import com.example.yin.dao.RedisNumMapper;
import com.example.yin.domain.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongHotIncrImpl {
    @Autowired
   private RedisNumMapper redisNumMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Async
    public void ListI(List<Song> list){
        for (Song s:list){
            redisTemplate.opsForZSet();
            redisNumMapper.numincur(s.getId()+"Hot");
        }
    }
}
