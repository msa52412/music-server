package com.example.yin.dao;

import com.example.yin.domain.Song;

import cn.hutool.json.JSONUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RedisZsetMapper {
    @Autowired
    private RedisTemplate redisTemplate;
    @Async
    public void setHotList(List<Song> list){
        Set<ZSetOperations.TypedTuple<String>> set=new HashSet<>();
        for (Song s:list){
            set.add(new DefaultTypedTuple<>(s.getName(),s.getHot()));
        }
        System.out.println(set.toString());
        redisTemplate.opsForZSet().add("rangeOfSong",set);
    }
    @Async
    public void setHotId(Song song){
        Set<ZSetOperations.TypedTuple<String>> set=new HashSet<>();
        set.add(new DefaultTypedTuple<>(song.getName(),song.getHot()));
        redisTemplate.opsForZSet().add("rangeOfSong",set);
    }
    @RabbitListener(queues = "demo")
    @RabbitHandler
    public void incrList(String msg){
        List<Song> list= JSONUtil.toBean(msg,List.class);
        for (Song s:list) {
            redisTemplate.opsForZSet().incrementScore("rangeOfSong",s.getId(),1);
        }
    }
}
