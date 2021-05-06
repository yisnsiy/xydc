package com.yisn.modules.vo;

import com.yisn.modules.entity.Product;
import com.yisn.modules.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by yisn on 2021/04/28/23:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreVO extends Store {
    private List<Product> productList;
}
