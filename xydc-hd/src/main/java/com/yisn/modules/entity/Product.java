package com.yisn.modules.entity;

import lombok.Data;

/**
 * Created by yisn on 2021/04/21/13:39
 */
@Data
public class Product {
    private int productId;
    private String productName;
    private String productPicture;
    private String product_info;
    private String tag;
    private int sell_num;
    private boolean is_sell;
    private double carbs;
    private double calorie;
    private double fat;
    private double protein;
}
