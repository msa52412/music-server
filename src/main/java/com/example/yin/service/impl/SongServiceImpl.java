package com.example.yin.service.impl;

import com.example.yin.dao.RedisNumMapper;
import com.example.yin.dao.RedisZsetMapper;
import com.example.yin.dao.RediskvMapper;
import com.example.yin.dao.SongMapper;
import com.example.yin.domain.Song;
import com.example.yin.service.SongService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;
    @Autowired
    private RediskvMapper rediskvMapper;
    @Autowired
    private RedisZsetMapper redisZsetMapper;
    @Autowired
    private SongHotIncrImpl songHotIncr;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Override
    public Object allSong() {
        if (rediskvMapper.isin("allsong")){
            List<Song> list= (List<Song>) rediskvMapper.getStringkv("allsong");
            amqpTemplate.convertAndSend(list);
            return list;
        }
        List<Song> list=songMapper.allSong();
        redisZsetMapper.setHotList(list);
        rediskvMapper.setStringkv("allsong",list);
        return list;
    }

    @Override
    public boolean addSong(Song song) {
        return songMapper.insertSelective(song) > 0;
    }

    @Override
    public boolean updateSongMsg(Song song) {
        return songMapper.updateSongMsg(song) > 0;
    }

    @Override
    public boolean updateSongUrl(Song song) {

        return songMapper.updateSongUrl(song) > 0;
    }

    @Override
    public boolean updateSongPic(Song song) {

        return songMapper.updateSongPic(song) > 0;
    }

    @Override
    public boolean deleteSong(Integer id) {
        return songMapper.deleteSong(id) > 0;
    }

    @Override
    public List<Song> songOfSingerId(Integer singerId)

    {
        List<Song> song=songMapper.songOfSingerId(singerId);
        return song;
    }

    @Override
    public List<Song> songOfId(Integer id)

    {
        List<Song> song=songMapper.songOfSingerId(id);

        return song;
    }

    @Override
    public List<Song> songOfSingerName(String name)

    {
        List<Song> song=songMapper.songOfSingerName(name);

        return song;
    }

    @Override
    public List<Song> songOfName(String name)

    {
        List<Song> song=songMapper.songOfName(name);
        return song;
    }
}
