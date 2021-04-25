package com.yisn.modules.vo;

import com.yisn.modules.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yisn on 2021/04/24/16:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO extends Product{
    private String storeName;
}
