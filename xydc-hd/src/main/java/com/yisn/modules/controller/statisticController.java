package com.yisn.modules.controller;

import com.alibaba.fastjson.JSON;
import com.yisn.modules.entity.Order;
import com.yisn.modules.service.OrderService;
import com.yisn.modules.service.ProductService;
import com.yisn.modules.utils.Result;
import com.yisn.modules.vo.ProductVO;
import com.yisn.modules.vo.statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by yisn on 2021/05/09/15:20
 */
@Controller
@RequestMapping("statistic")
public class statisticController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @GetMapping("storeScore")
    public String statisticStoreScore() {return "/statistic/store/score";}

    @GetMapping("storeNum")
    public String statisticStoreNum() {return "/statistic/store/num";}

    @GetMapping("carrierScore")
    public String statisticCarrierScore() {return "/statistic/carrier/score";}

    @GetMapping("carrierNum")
    public String statisticCarrierNum() {return "/statistic/carrier/num";}

    @GetMapping("productNum")
    public String statisticProductNum(@RequestParam(name = "storeId", required = true) int storeId, Model model){
        model.addAttribute("storeId", storeId);
        return "/statistic/product/num";
    }

    @PostMapping("data")
    @ResponseBody
    public Result getStatistic(@RequestBody Map<String, Object> map) {
        int identity = Integer.parseInt(map.get("identity").toString());
        Order order = JSON.parseObject(JSON.toJSONString(map), Order.class);
        System.out.println(order);
        System.out.println(identity);
        Map<Integer, statistic> res = orderService.analysis(identity, order);
        return new Result(Result.OK, "", res);
    }

    @PostMapping("productData")
    @ResponseBody
    public Result getStatisticProductData(@RequestBody ProductVO productVO) {
        System.out.println(productVO.getStoreId());
        Map<Integer, statistic> res = productService.analysis(productVO);
        return new Result(Result.OK, "", res);
    }
}
