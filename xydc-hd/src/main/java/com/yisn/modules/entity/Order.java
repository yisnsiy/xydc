package com.yisn.modules.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yisn on 2021/05/06/15:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private int userId;
    private int carrierId;
    private int storeId;
    private int addressId;
    private int takeCode;
    private String products;
    private Double totalMoney;
    private Double storeScore;
    private Double carrierScore;
    private String evaluateContent;
    private long userOrder;
    private long storeOrder;
    private long carrierOrder;
    private long arriveStore;
    private long getProducts;
    private long accomplish;
}
