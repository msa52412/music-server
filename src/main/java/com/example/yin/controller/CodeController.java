package com.example.yin.controller;

import com.example.yin.dao.RediskvMapper;
import com.example.yin.service.impl.GetCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {
    @Autowired
    RediskvMapper rediskvMapper;
    @Autowired
    GetCodeServiceImpl getCodeService;
    @RequestMapping(value = "/code")
    public Object code(@RequestParam("username") String name){
        return getCodeService.getCode(name);
    }
    @RequestMapping(value = "/isTCode")
    public Object isTCode(@RequestParam("username") String name,@RequestParam("code") String code){
        String flag= (String) rediskvMapper.getStringkv(name);
        if (flag==null) return "过期";
        if(rediskvMapper.getStringkv(name).equals(code)){
            return true;
        }
        return "过期";
    }
}
