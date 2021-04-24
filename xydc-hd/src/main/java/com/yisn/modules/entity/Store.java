package com.yisn.modules.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by yisn on 2021/04/21/10:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private int storeId;
    private int userId;
    private int display;
    private boolean auto;
    private String storeName;
    private int storeTel;
    private double score;
    private String storeAddress;

    private String storePicture;
    private String licenses;
    private String kitchenPicture;
}
