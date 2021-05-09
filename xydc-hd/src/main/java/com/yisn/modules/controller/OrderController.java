package com.yisn.modules.controller;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Order;
import com.yisn.modules.entity.Store;
import com.yisn.modules.entity.User;
import com.yisn.modules.service.OrderService;
import com.yisn.modules.service.StoreService;
import com.yisn.modules.service.UserService;
import com.yisn.modules.utils.Result;
import com.yisn.modules.utils.TableResult;
import com.yisn.modules.vo.OrderVO;
import com.yisn.modules.vo.statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yisn on 2021/04/16/14:53
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @GetMapping("list")
    public String list(){return "order/list";}

    @GetMapping("currentList")
    public String currentList(@RequestParam(name = "userId", required = true) int userId, Model model) {
        User user = userService.findByUserId(userId);
        if(user == null || user.getUserId() == 0) return "";
        model.addAttribute("user", user);
        if(user.getIdentity() == 0) { //是管理员

        }else if(user.getIdentity() == 3) { //是店主
            Store store = storeService.findByUserId(userId);
            model.addAttribute("store", store);
            return "order/currentOrderForStore";
        }
        return "";
    }

    @GetMapping("historyList")
    public String historyList(@RequestParam(name = "userId", required = true) int userId, Model model) {
        User user = userService.findByUserId(userId);
        if(user == null || user.getUserId() == 0) return "";
        model.addAttribute("user", user);
        if(user.getIdentity() == 0) { //是管理员

        }else if(user.getIdentity() == 3) { //是店主
            Store store = storeService.findByUserId(userId);
            model.addAttribute("store", store);
            return "order/historyOrderForStore";
        }
        return "";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(name = "orderId", required = true) int orderId, Model model) {
        Order order = orderService.findByOrderId(orderId);
        System.out.println(order);
        model.addAttribute("order", order);
        return "order/detail";
    }

    @GetMapping("listJson")
    @ResponseBody
    public TableResult getlistJson(@RequestParam(name = "page", defaultValue = "1", required = true) int pageNum,
                                   @RequestParam(name = "limit", defaultValue = "10000", required = true) int pageSize,
                                   Order condition) {
        System.out.println(condition);
        TableResult tableResult = new TableResult();
        PageInfo<OrderVO> page = orderService.findPaperByPage(pageNum, pageSize, condition);
        List<OrderVO> orderVOList = page.getList();
        tableResult.setCode(0);
        tableResult.setCount(page.getTotal());
        tableResult.setData(orderVOList);
        return tableResult;
    }

    @PostMapping("storeAcceptOrder")
    @ResponseBody
    public Result storeAcceptOrder(@RequestBody Map<String, Object> map) {
        int orderId = Integer.parseInt(map.get("orderId").toString());
        Long storeOrder = Long.parseLong(map.get("storeOrder").toString());

        orderService.storeAdd(orderId, storeOrder);
        return new Result(Result.OK, "接单成功");
    }

    @PostMapping("updateOrder")
    @ResponseBody
    public Result updateOrder(@RequestBody Order order) {
        System.out.println(order);
        orderService.update(order);
        return new Result(Result.OK, "更新信息成功");
    }

    @GetMapping("delete")
    @ResponseBody
    public Result delete(@RequestParam(name = "orderId", required = true) int orderId) {
        System.out.println(orderId);
        orderService.delete(orderId);
        return new Result(Result.OK, "删除订单成功");
    }
}
