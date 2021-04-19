package com.yisn.modules.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/adminHome")
    public String func() {
        return "adminHome";
    }
}
