package com.example.yin.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.yin.domain.Consumer;
import org.springframework.stereotype.Service;

@Service("TokenService")
public class TokenService {
    public String getToken(String name,String pass) {
        String token="";
        token= JWT.create().withAudience(name)// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(pass));// 以 password 作为 token 的密钥
        return token;
    }
}
