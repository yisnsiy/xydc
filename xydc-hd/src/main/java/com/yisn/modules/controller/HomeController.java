package com.yisn.modules.controller;

import com.yisn.modules.entity.User;
import com.yisn.modules.service.UserService;
import com.yisn.modules.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home(@RequestParam(name = "userId", required = true)int userId, Model model) {
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        System.out.println(user.getIdentity());
        if (user.getIdentity() == 0) return "adminHome";
        else if (user.getIdentity() == 3) return "storeHome";
        return "";
    }

    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/register")
    public String register() {return "register";}
    @PostMapping("/userRegister")
    @ResponseBody
    public Result userRegister(@RequestBody User user) {
        User u = userService.findByUserId(user.getUserId());
        if(u != null) return new Result(Result.ERROR, "该用户名已存在");
        userService.create(user);
        return new Result(Result.OK, "注册成功");
    }

    @PostMapping("/vertifyUser")
    @ResponseBody
    public Result vertifyUser(@RequestBody Map<String, String> map) {
        String msg = new String();
        int status = Result.OK;
        int data = 0;
        User user = userService.findByUserId(Integer.valueOf(map.get("userId")));
        if(!map.get("password").equals(user.getPassword())) {
            msg = "用户名或者密码不正确";
            status = Result.ERROR;
        }else{
            data = user.getIdentity();
            if(data == 1) msg = "用户请前往微信小程序登录";
            else if(data == 2) msg = "骑手请前往微信小程序登录";
            else if(data == 0) msg = "欢迎管理员登录";
            else if(data == 3) msg = "欢迎店主登录";

            if(data == 1 || data == 2) status = Result.ERROR;
        }
        return new Result(status, msg);
    }
}
