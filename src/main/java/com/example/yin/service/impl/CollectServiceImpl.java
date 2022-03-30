package com.example.yin.service.impl;

import com.example.yin.dao.CollectMapper;
import com.example.yin.dao.RedisSetMapper;
import com.example.yin.dao.RediskvMapper;
import com.example.yin.domain.Collect;
import com.example.yin.service.CollectService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private RedisSetMapper redisSetMapper;
    @Autowired
    private RediskvMapper rediskvMapper;

    @Override
    public boolean addCollection(Collect collect) {//添加收藏
        rediskvMapper.delete("allcollect");
        int flag=collectMapper.insertSelective(collect);
        Time.sleep(1000);
        return rediskvMapper.delete("allcollect")&&flag>0;
    }

    @Override
    public boolean existSongId(Integer userId, Integer songId) {//用户收藏是否存在歌曲
            if (redisSetMapper.isin(String.valueOf(userId),String.valueOf(songId))){
                return true;
            }
         if(collectMapper.existSongId(userId, songId)>0){
            redisSetMapper.setSet(String.valueOf(userId),String.valueOf(songId));
             return true;
         }
         return false;
    }

    @Override
    public boolean updateCollectMsg(Collect collect) {//更新收藏
        rediskvMapper.delete("allcollect");
        int flag=collectMapper.updateCollectMsg(collect);
        Time.sleep(1000);
        return rediskvMapper.delete("allcollect")&&flag>0;
    }

    @Override
    public boolean deleteCollect(Integer userId, Integer songId) {
        return rediskvMapper.delete("allcollect")&&collectMapper.deleteCollect(userId, songId) > 0;
    }

    @Override
    public List<Collect> allCollect() {
        if (rediskvMapper.isin("allcollect")){
            return (List<Collect>) rediskvMapper.getStringkv("allcollect");
        }
        List<Collect> all=collectMapper.allCollect();
        rediskvMapper.setStringkv("allcollect",all);
        return all;

    }

    @Override
    public List<Collect> collectionOfUser(Integer userId) {
        if (rediskvMapper.isin(userId+"list")){
        return (List<Collect>) rediskvMapper.getStringkv(userId+"list");
        }
        List<Collect>  list=collectMapper.collectionOfUser(userId);
        rediskvMapper.setStringkv(userId+"list",list);
        return list;
    }
}
