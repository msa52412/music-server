package com.example.yin.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.yin.domain.Admin;

import java.util.stream.Stream;

public class getToken {
    public String getToken(Admin admin){
        String token="";
        token= JWT.create().withAudience(String.valueOf(admin.getId()))
                .sign(Algorithm.HMAC256(admin.getPassword()));
        return token;
    }
}
