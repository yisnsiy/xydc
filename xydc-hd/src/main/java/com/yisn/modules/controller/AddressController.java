package com.yisn.modules.controller;

import com.yisn.modules.entity.Address;
import com.yisn.modules.service.AddressService;
import com.yisn.modules.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yisn on 2021/04/28/16:16
 */
@Controller
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("add")
    @ResponseBody
    public Result add(@RequestBody Address address) {
        System.out.println(address);
        addressService.create(address);
        return new Result(Result.OK, "新建地址成功");
    }

    @GetMapping("delete")
    @ResponseBody
    public Result findAddressByUserId(@RequestParam(name = "addressId", required = true) int addressId) {
        addressService.deleteByAddressId(addressId);
        return new Result(Result.OK, "地址删除成功");
    }

    @GetMapping("setDefaultAddress")
    @ResponseBody
    public Result setDefaultAddress(@RequestParam(name = "addressId", required = true) int addressId) {
        addressService.setDefaultAddress(addressId);
        return new Result(Result.OK, "切换默认地址成功");
    }

}
