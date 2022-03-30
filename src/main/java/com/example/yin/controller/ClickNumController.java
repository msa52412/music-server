package com.example.yin.controller;

import com.example.yin.service.impl.ClickServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClickNumController {
    @Autowired
    private ClickServiceImpl clickService;
    @ResponseBody
    @RequestMapping(value = "/clicknum")
    public Object getNum(@RequestParam("path") String path){
        return clickService.getClickNum(path);
    }

}
