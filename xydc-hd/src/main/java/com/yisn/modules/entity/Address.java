package com.yisn.modules.entity;

import lombok.Data;

/**
 * Created by yisn on 2021/04/28/16:11
 */
@Data
public class Address {

    private int addressId;
    private int userId;
    private String realName;
    private int tel;
    private String content;
    private boolean def; //是否是默认地址
}
