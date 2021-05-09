package com.yisn.modules.controller;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Address;
import com.yisn.modules.service.AddressService;
import com.yisn.modules.utils.Result;
import com.yisn.modules.utils.TableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yisn on 2021/04/28/16:16
 */
@Controller
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/list")
    public String list() {
        return "/address/list";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "addressId", required = true) int addressId, Model model) {
        System.out.println(addressId);
        Address address = addressService.findSingle(addressId);
        System.out.println(address);
        model.addAttribute("address", address);
        return "/address/edit";
    }

    @RequestMapping("/listJson")
    @ResponseBody
    public TableResult getJsonData(@RequestParam(name = "page", defaultValue = "1", required = true) int pageNum,
                                   @RequestParam(name = "limit", defaultValue = "10000", required = true) int pageSize,
                                   Address condition) {
        System.out.println(condition);
        TableResult tableResult = new TableResult();
        PageInfo<Address> page = addressService.findPaperByPage(condition, pageNum, pageSize);
        List<Address> addressList = page.getList();
        tableResult.setCode(0);
        tableResult.setCount(page.getTotal());
        tableResult.setData(addressList);
        return tableResult;
    }

    @PostMapping("add")
    @ResponseBody
    public Result add(@RequestBody Address address) {
        System.out.println(address);
        addressService.create(address);
        return new Result(Result.OK, "新建地址成功");
    }

    @GetMapping("delete")
    @ResponseBody
    public Result delete(@RequestParam(name = "addressId", required = true) int addressId) {
        addressService.deleteByAddressId(addressId);
        return new Result(Result.OK, "地址删除成功");
    }

    @GetMapping("setDefaultAddress")
    @ResponseBody
    public Result setDefaultAddress(@RequestParam(name = "addressId", required = true) int addressId,
                                    @RequestParam(name = "userId", required = true) int userId) {
        addressService.setDefaultAddress(addressId, userId);
        return new Result(Result.OK, "切换默认地址成功");
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody Address address) {
        System.out.println(address);
        addressService.update(address);
        return new Result(Result.OK, "更新地址信息成功");
    }

}
