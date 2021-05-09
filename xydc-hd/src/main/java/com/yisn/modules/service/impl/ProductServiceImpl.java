package com.yisn.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Product;
import com.yisn.modules.mapper.ProductMapper;
import com.yisn.modules.service.ProductService;
import com.yisn.modules.vo.ProductVO;
import com.yisn.modules.vo.statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yisn on 2021/04/24/16:30
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void create(Product product) {
        productMapper.create(product);
        return;
    }

//    @Override
//    public PageInfo<Product> findPaperByPage(int pageNum, int pageSize, int storeId) {
//        PageHelper.startPage(pageNum, pageSize);
//        List<Product> productList = productMapper.findByStoreId(storeId);
//        PageInfo<Product> pageInfo = new PageInfo<>(productList);
//        return pageInfo;
//    }

    @Override
    public Product findByProductId(int productId) {
        return productMapper.findByProductId(productId);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
        return;
    }

    @Override
    public void sellChangeByProductId(int productId, int sell) {
        productMapper.sellChangeByProductId(productId, sell);
        return;
    }

    @Override
    public void deleteByProductId(int productId) {
        productMapper.deleteByProductId(productId);
        return;
    }

    @Override
    public PageInfo<ProductVO> findAllProductByPage(ProductVO productVO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductVO> productVOList = productMapper.find(productVO);
        PageInfo<ProductVO> pageInfo = new PageInfo<>(productVOList);
        return pageInfo;
    }

    @Override
    public void addSellNum(int productId, int cnt) {
        productMapper.addSellNum(productId, cnt);
        return;
    }

    @Override
    public Map<Integer, statistic> analysis(ProductVO productVO) {
        List<ProductVO> productVOList = productMapper.find(productVO);
        Map<Integer, statistic> productMap = new HashMap<>();
        for(int i = 0; i < productVOList.size(); ++ i) {
            ProductVO p = productVOList.get(i);
            int cnt = p.getSellNum();
            if(cnt > 0) {
                int id = p.getProductId();
                String name = p.getProductName();
                if(!productMap.containsKey(id)) {
                    productMap.put(id, new statistic());
                    productMap.get(id).setId(id);
                    productMap.get(id).setName(name);
                    productMap.get(id).setNum(0);
                }
                productMap.get(id).setNum(productMap.get(id).getNum() + cnt);
            }
        }
        return productMap;
    }
}
