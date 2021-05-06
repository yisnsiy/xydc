package com.yisn.modules.controller;

import com.yisn.modules.entity.Address;
import com.yisn.modules.entity.Order;
import com.yisn.modules.entity.User;
import com.yisn.modules.service.AddressService;
import com.yisn.modules.service.OrderService;
import com.yisn.modules.service.StoreService;
import com.yisn.modules.service.UserService;
import com.yisn.modules.utils.Result;
import com.yisn.modules.vo.StoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result findStoreWithProduct() {
        List<StoreVO> storeVOList = storeService.findStoreWithProduct();
//        for(int i = 0; i < storeVOList.get(0).get; ++ i) {
//            System.out.println();
//        }
        return new Result(Result.OK, "查找店铺成功", storeVOList);
    }

    //    ----------------------------------------订单--------------------------------------------
    @PostMapping("/submitOrder")
    public Result submitOrder(@RequestBody Order order) {
        orderService.userAdd(order);
        return new Result(Result.OK, "支付成功");
    }
}
