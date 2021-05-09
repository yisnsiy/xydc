package com.yisn.modules.controller;

import com.yisn.modules.entity.Address;
import com.yisn.modules.entity.Order;
import com.yisn.modules.entity.Store;
import com.yisn.modules.entity.User;
import com.yisn.modules.service.*;
import com.yisn.modules.utils.Result;
import com.yisn.modules.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yisn on 2021/04/27/19:18
 */
@RestController
@RequestMapping("wx")
public class WxController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    //    ----------------------------------------用户--------------------------------------------

    @GetMapping("/userDetail")
    public Result findUserByUserId(@RequestParam(name = "userId", required = true) int userId) {
        User user = userService.findByUserId(userId);
        if (user != null || user.getUserId() != 0) {
            return new Result(Result.OK, "", user);
        }
        return new Result(Result.ERROR, "查找失败");
    }

    @PostMapping("/updateUser")
    public Result updateUserByUserId(@RequestBody User user) {
        System.out.println(user);
        if (user == null || user.getUserId() == 0) {
            return new Result(Result.ERROR, "修改用户失败");
        }
        userService.updateByUserId(user);
        return new Result(Result.OK, "修改用户成功");
    }

    //    ----------------------------------------地址--------------------------------------------

    @GetMapping("/findAddress")
    public Result findAddressByUserId(@RequestParam(name = "userId", required = true) int userId) {
        List<Address> addressList = addressService.findAddressByUserId(userId);
        return new Result(Result.OK, "查找成功", addressList);
    }

    //    ----------------------------------------店铺--------------------------------------------
    @GetMapping("/findStore")
    @ResponseBody
    public Result findStoreWithProduct(Store store) {
        List<StoreVO> storeVOList = storeService.findStoreWithProduct(store);
        return new Result(Result.OK, "查找店铺成功", storeVOList);
    }

    //    ----------------------------------------订单--------------------------------------------
    @PostMapping("/submitOrder")
    public Result submitOrder(@RequestBody Order order) {
        orderService.userAdd(order);
        return new Result(Result.OK, "支付成功");
    }

    @PostMapping("/carrierAcceptOrder")
    public Result carrierAcceptOrder(@RequestBody Order order) {
        Result result = new Result();
        System.out.println(order);
        int flag = orderService.carrierAdd(order);
        if(flag == 1) {
            return new Result(Result.OK, "抢单成功");
        }else {
            return new Result(Result.ERROR, "抢单失败，已被其他骑手先一步抢下");
        }
    }

    //    ----------------------------------------餐品--------------------------------------------
    @PostMapping("addSellNum")
    public Result addSellNum(@RequestBody Map<String, Object> map) {
        int productId = Integer.parseInt(map.get("productId").toString());
        int cnt = Integer.parseInt(map.get("cnt").toString());
        productService.addSellNum(productId, cnt);
        return new Result(Result.OK, "");
    }
}
