package com.yisn.modules.service;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Product;
import com.yisn.modules.vo.ProductVO;
import com.yisn.modules.vo.statistic;

import java.util.Map;

/**
 * Created by yisn on 2021/04/24/16:29
 */
public interface ProductService {
    void create(Product product);

//    PageInfo<Product> findPaperByPage(int pageNum, int pageSize, int storeId);

    Product findByProductId(int productId);

    void update(Product product);

    void sellChangeByProductId(int productId, int sell);

    void deleteByProductId(int productId);

    PageInfo<ProductVO> findAllProductByPage(ProductVO productVO, int pageNum, int pageSize);

    void addSellNum(int productId, int cnt);

    Map<Integer, statistic> analysis(ProductVO ProductVO);
}
