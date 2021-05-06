package com.yisn.modules.controller;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Order;
import com.yisn.modules.entity.Store;
import com.yisn.modules.entity.User;
import com.yisn.modules.service.OrderService;
import com.yisn.modules.service.StoreService;
import com.yisn.modules.service.UserService;
import com.yisn.modules.utils.TableResult;
import com.yisn.modules.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public String list(@RequestParam(name = "userId", required = true) int userId, Model model) {
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

    @GetMapping("listJson")
    @ResponseBody
    public TableResult getlistJson(@RequestParam(name = "page", defaultValue = "1", required = true) int pageNum,
                                   @RequestParam(name = "limit", defaultValue = "4", required = true) int pageSize,
                                   @RequestParam(name = "storeId", required = true) int storeId) {
        TableResult tableResult = new TableResult();
        PageInfo<OrderVO> page = orderService.findPaperByPage(pageNum, pageSize, storeId);
        List<OrderVO> orderVOList = page.getList();
        tableResult.setCode(0);
        tableResult.setCount(page.getTotal());
        tableResult.setData(orderVOList);
        return tableResult;
    }

    @GetMapping("detail")
    public String detail(@RequestParam(name = "orderId", required = true) int orderId, Model model) {
        Order order = orderService.findByOrderId(orderId);
        System.out.println(order);
        model.addAttribute("order", order);
        return "order/detail";
    }
}
