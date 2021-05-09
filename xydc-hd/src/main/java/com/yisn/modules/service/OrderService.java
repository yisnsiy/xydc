package com.yisn.modules.service;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Order;
import com.yisn.modules.vo.OrderVO;
import com.yisn.modules.vo.statistic;

import java.util.Map;

/**
 * Created by yisn on 2021/05/06/15:44
 */
public interface OrderService {
    void userAdd(Order order);

    PageInfo<OrderVO> findPaperByPage(int pageNum, int pageSize, Order condition);

    Order findByOrderId(int orderId);

    void storeAdd(int orderId, Long storeOrder);

    int carrierAdd(Order order);

    void update(Order order);

    void delete(int orderId);

    public Map<Integer, statistic> analysis(int identity, Order order);
}
