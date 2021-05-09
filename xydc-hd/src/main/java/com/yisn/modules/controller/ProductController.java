package com.yisn.modules.controller;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Product;
import com.yisn.modules.entity.Store;
import com.yisn.modules.entity.User;
import com.yisn.modules.service.ProductService;
import com.yisn.modules.service.StoreService;
import com.yisn.modules.service.UserService;
import com.yisn.modules.utils.Result;
import com.yisn.modules.utils.TableResult;
import com.yisn.modules.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yisn on 2021/04/24/16:26
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreService storeService;

    @GetMapping("list")
    public String productList(@RequestParam(name = "userId", defaultValue = "3", required = true) int userId, Model model) {
        User user = userService.findByUserId(userId);
        System.out.println(user);
        if(user == null) return "";
        if(user.getIdentity() == 3) {
            Store store = storeService.findByUserId(userId);
            if(store != null && store.getStoreId() != 0) { //这才算是合法的店主
                model.addAttribute(store);
                return "/product/listForStore";
            }
        }
        else if(user.getIdentity() == 0){ //管理员走另一边。
            return "product/listForAdmin";
        }
        return "";
    }

    @GetMapping("add")
    public String addProduct(@RequestParam(name = "storeId", required = true) int storeId, Model model) {
        Store store = storeService.findeByStoreId(storeId);
        System.out.println(store);
        model.addAttribute("store", store);
        return "product/add";
    }

    @GetMapping("detail")
    public String detail(@RequestParam(name = "productId", required = true) int productId, Model model) {
        Product product = productService.findByProductId(productId);
        if(product != null && product.getProductId() != 0) {
            System.out.println(product);
            model.addAttribute("product", product);
            return "product/detail";
        }
        return "";
    }

    @GetMapping("edit")
    public String edit(@RequestParam(name = "productId", required = true) int productId, Model model) {
        Product product = productService.findByProductId(productId);
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("create")
    @ResponseBody
    public Result create(@RequestBody Product product) {
        System.out.println(product);
        productService.create(product);
        return new Result(Result.OK, "新建餐品成功");
    }

//    @GetMapping("listJson")
//    @ResponseBody
//    public TableResult getJsonData(@RequestParam(name = "page", defaultValue = "1", required = true) int pageNum,
//                                   @RequestParam(name = "limit", defaultValue = "4", required = true) int pageSize,
//                                   @RequestParam(name = "storeId", required = true) int storeId) {
//        TableResult tableResult = new TableResult();
//        PageInfo<Product> page = productService.findPaperByPage(pageNum, pageSize, storeId);
//        List<Product> productList = page.getList();
//        tableResult.setCode(0);
//        tableResult.setCount(page.getTotal());
//        tableResult.setData(productList);
//        return tableResult;
//    }

    @GetMapping("listJsonAllStore")
    @ResponseBody
    public TableResult getJsonDataAllStore(@RequestParam(name = "page", defaultValue = "1", required = true) int pageNum,
                                           @RequestParam(name = "limit", defaultValue = "10000", required = true) int pageSize,
                                           ProductVO productVO) {
        System.out.println(productVO);
        System.out.println(productVO.getStoreId());
        TableResult tableResult = new TableResult();
        PageInfo<ProductVO> page = productService.findAllProductByPage(productVO, pageNum, pageSize);
        List<ProductVO> productVOList = page.getList();
        tableResult.setCode(0);
        tableResult.setCount(page.getTotal());
        tableResult.setData(productVOList);
        return tableResult;
    }


    @PostMapping("update")
    @ResponseBody
    public Result update(@RequestBody Product product) {
        System.out.println(product);
        productService.update(product);
        return new Result(Result.OK, "修改餐品信息成功");
    }

    @PostMapping("statusChange")
    @ResponseBody
    public Result statusChange(@RequestBody Map<String, Object> map) {
        int productId = Integer.parseInt(map.get("productId").toString());
        int sell = Integer.parseInt(map.get("sell").toString());
        productService.sellChangeByProductId(productId, sell);
        return new Result(Result.OK, "更新信息成功");
    }

    @GetMapping("delete")
    @ResponseBody
    public Result deleteByProductId(@RequestParam(name = "productId", required = true) int productId) {
        System.out.println(productId);
        productService.deleteByProductId(productId);
        return new Result(Result.OK, "删除餐品成功");
    }


}
