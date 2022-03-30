package com.example.yin.service.impl;

import com.example.yin.dao.RedisNumMapper;
import com.example.yin.service.ClickService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ClickServiceImpl implements ClickService {
    @Autowired
    private RedisNumMapper redisNumMapper;
    @Override
    public long getClickNum(String path) {
        if (StringUtils.isBlank(path)) return 0;
        return (long) redisNumMapper.numincur(path);
    }
}
