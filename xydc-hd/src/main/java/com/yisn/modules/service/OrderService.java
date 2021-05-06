package com.yisn.modules.service;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Order;
import com.yisn.modules.vo.OrderVO;

/**
 * Created by yisn on 2021/05/06/15:44
 */
public interface OrderService {
    void userAdd(Order order);

    PageInfo<OrderVO> findPaperByPage(int pageNum, int pageSize, int storeId);

    Order findByOrderId(int orderId);
}
