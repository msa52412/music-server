package com.example.yin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.example.yin.dao")
@EnableAsync
public class YinMusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(YinMusicApplication.class, args);
    }

}

