package com.yisn.modules.controller;

import com.yisn.modules.entity.Store;
import com.yisn.modules.entity.User;
import com.yisn.modules.service.StoreService;
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

    @Autowired
    private StoreService storeService;

    @GetMapping("/home")
    public String home(@RequestParam(name = "userId", required = true)int userId, Model model) {
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        if (user.getIdentity() == 0) return "adminHome";
        else if (user.getIdentity() == 3){ //是店主
            Store store = storeService.findByUserId(userId);
            boolean isCreateStore;
            if (store == null || store.getStoreId() == 0) {
                isCreateStore = false;
            }else {
                model.addAttribute("store", store);
                isCreateStore = true;
            }
            model.addAttribute("isCreateStore", isCreateStore);
            return "storeHome";
        }
        return "";
    }

    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/register")
    public String register() {return "register";}

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
