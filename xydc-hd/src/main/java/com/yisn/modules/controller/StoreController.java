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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String storeList() {return "/store/list";}

    @GetMapping("/add")
    public String add(@RequestParam(name = "userId") int userId, Model model) {
        User user = userService.findByUserId(userId);
        model.addAttribute("user", user);
        return "/store/add";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "userId") int userId, Model model) {
        Store store = storeService.findByUserId(userId);
        System.out.println(store);
        model.addAttribute("store", store);
        return "/store/edit";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(name = "userId", required = true) int userId, Model model) {
        Store store = storeService.findByUserId(userId);
        model.addAttribute("store", store);
        return "/store/detail";
    }

    @GetMapping("listJson")
    @ResponseBody
    public TableResult getJsonData(@RequestParam(name = "page", defaultValue = "1", required = true) int pageNum,
                                   @RequestParam(name = "limit", defaultValue = "4", required = true) int pageSize) {
        TableResult tableResult = new TableResult();
        PageInfo<Store> page = storeService.findPaperByPage(pageNum, pageSize);
        List<Store> storeList = page.getList();
        tableResult.setCode(0);
        tableResult.setCount(page.getTotal());
        tableResult.setData(storeList);
        return tableResult;
    }

    @PostMapping("create")
    @ResponseBody
    public Result create(@RequestBody Store store) {
        System.out.println(store);
        storeService.create(store);
        return new Result(Result.OK, "创建店铺成功");
    }

    @PostMapping("update")
    @ResponseBody
    public Result update(@RequestBody Store store) {
        System.out.println(store);
        storeService.updata(store);
        return new Result(Result.OK, "更新店铺信息成功");
    }

    @PostMapping("statusChange")
    @ResponseBody
    public Result statusChange(@RequestBody Map<String, Object> map) {
        int id = Integer.parseInt(map.get("id").toString());
        int storeId = Integer.parseInt(map.get("storeId").toString());
        System.out.println(map.get("storeId"));
        System.out.println(map.get("id"));
        if(id == 1) {
            int display = Integer.parseInt(map.get("display").toString());
            System.out.println(display);
            storeService.displayChangeByStoreId(storeId, display);
        }else if(id == 2) {
            boolean auto = Boolean.parseBoolean(map.get("auto").toString());
            storeService.autoChangeByStoreId(storeId, auto);
            System.out.println(auto);
        }
        return new Result(Result.OK, "更新信息成功");
    }

    @GetMapping("delete")
    @ResponseBody
    public Result deleteByStoreId(@RequestParam(name = "storeId") int storeId) {
        storeService.deleteByStoreId(storeId);
        return new Result(Result.OK, "删除店铺成功");
    }
}
