package com.yisn.modules.vo;

import com.yisn.modules.entity.Address;
import com.yisn.modules.entity.Order;
import com.yisn.modules.entity.Store;
import com.yisn.modules.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yisn on 2021/05/06/19:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO extends Order {
    private User user;
    private User carrier;
    private Store store;
    private Address address;
}
