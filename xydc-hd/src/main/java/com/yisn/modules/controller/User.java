package com.yisn.modules.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class User {

    @RequestMapping("/test")
    public String firstFunction() {
        return "你好，springboot！";
    }
}
