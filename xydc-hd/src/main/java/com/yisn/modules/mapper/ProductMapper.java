package com.yisn.modules.mapper;

import com.yisn.modules.entity.Product;
import com.yisn.modules.vo.ProductVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yisn on 2021/04/24/16:30
 */
@Repository
public interface ProductMapper {
    void create(Product product);

    List<Product> findByStoreId(@Param("storeId") int storeId);

    Product findByProductId(@Param("productId") int productId);

    void update(Product product);

    void sellChangeByProductId(@Param("productId") int productId,
                               @Param(("sell")) int sell);

    void deleteByProductId(@Param("productId") int productId);

    List<ProductVO> findAllProduct();
}
