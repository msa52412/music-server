package com.example.yin.service.impl;

import com.example.yin.dao.RediskvMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetCodeServiceImpl {
    @Autowired
    RediskvMapper rediskvMapper;
    public boolean getCode(String username){
        String code="11111";
        rediskvMapper.setStringTkv(username,code);
        return true;
    }
}
