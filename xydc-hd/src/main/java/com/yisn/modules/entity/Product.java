package com.yisn.modules.entity;

import lombok.Data;

/**
 * Created by yisn on 2021/04/21/13:39
 */
@Data
public class Product {
    private int productId;
    private int StoreId;
    private String productName;
    private String productPicture;
    private String productInfo;
    private String tag;
    private int sellNum;
    private int sell;
    private double price;
    private double carbs;
    private double calorie;
    private double fat;
    private double protein;
    private double weight;
}
