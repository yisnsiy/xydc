package com.yisn.modules.service;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Product;
import com.yisn.modules.vo.ProductVO;

/**
 * Created by yisn on 2021/04/24/16:29
 */
public interface ProductService {
    void create(Product product);

    PageInfo<Product> findPaperByPage(int pageNum, int pageSize, int storeId);

    Product findByProductId(int productId);

    void update(Product product);

    void sellChangeByProductId(int productId, int sell);

    void deleteByProductId(int productId);

    PageInfo<ProductVO> findAllProductByPage(int pageNum, int pageSize);
}
