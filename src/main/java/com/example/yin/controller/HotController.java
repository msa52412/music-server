package com.example.yin.controller;

import com.example.yin.service.impl.HotSongImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotController {
    @Autowired
    private HotSongImpl hotSong;
    @GetMapping("/hotsong")
    public Object hotSong(){
        return hotSong.getHotSong();
    }
}
