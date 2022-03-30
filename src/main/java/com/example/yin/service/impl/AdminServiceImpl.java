package com.example.yin.service.impl;

import com.example.yin.dao.AdminMapper;
import com.example.yin.dao.RediskvMapper;
import com.example.yin.service.AdminService;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private RediskvMapper rediskvMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public boolean veritypasswd(String name, String password) {
        if (StringUtils.isBlank(name)) return false;
        if (rediskvMapper.getStringkv(name).equals(password)){
            return true;
        }
        if(adminMapper.verifyPassword(name,password)>0){
            rediskvMapper.setStringkv(name,password);
            return true;
        }
        return false;
    }
}
