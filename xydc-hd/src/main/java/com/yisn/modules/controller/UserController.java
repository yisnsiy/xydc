package com.yisn.modules.controller;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Store;
import com.yisn.modules.entity.User;
import com.yisn.modules.service.StoreService;
import com.yisn.modules.service.UserService;
import com.yisn.modules.utils.Result;
import com.yisn.modules.utils.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;


    @RequestMapping("/list")
    public String userList() {
        return "/user/list";
    }

    @RequestMapping("/add")
    public String add() { return "/user/add"; }

    @GetMapping("/detail")
    public String detail(@RequestParam(name = "userId", required = true) int userId, Model model) {
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "/user/detail";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "userId", required = true) int userId, Model model) {
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "/user/edit";
    }

//    返回userList数据
    @RequestMapping("/listJson")
    @ResponseBody
    public TableResult getJsonData(@RequestParam(name = "page", defaultValue = "1", required = false) int pageNum,
                                   @RequestParam(name = "limit", defaultValue = "7", required = false) int pageSize,
                                   User user) {
        TableResult tableResult = new TableResult();
        PageInfo<User> page = userService.findPaperByPage(user, pageNum, pageSize);
        List<User> userList = page.getList();
        tableResult.setCode(0);
        tableResult.setCount(page.getTotal());
        tableResult.setData(userList);
        return tableResult;
    }

//    创建新用户接口
    @PostMapping("/create")
    @ResponseBody
    public Result create(@RequestBody User user) {
        User u = userService.findByUserId(Integer.valueOf(user.getUserId()));
        if(u != null) return new Result(Result.ERROR, "创建失败，该用户名已存在");
        System.out.println(user);
        userService.create(user);
        return new Result(Result.OK, "创建用户成功");
    }

//    删除用户接口
    @GetMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam(name = "userId", required = true) int userId) {
        userService.deleteByUserId(userId);
        return new Result(Result.OK, "删除用户成功");
    }

//    修改用户接口
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody User user) {
        System.out.println(user);
        userService.updateByUserId(user);
        return new Result(Result.OK, "更新用户信息成功");
    }
}
