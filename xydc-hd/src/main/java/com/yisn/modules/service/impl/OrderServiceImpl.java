package com.yisn.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Order;
import com.yisn.modules.mapper.OrderMapper;
import com.yisn.modules.service.OrderService;
import com.yisn.modules.vo.OrderVO;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yisn on 2021/05/06/15:44
 */
@Service
public class OrderServiceImpl implements OrderService {
    public static int TakeCode = 0;
    public static long LastTime = 0L;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void userAdd(Order order) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date lastTime = new Date(LastTime);
        long nowStamp = new Date().getTime();
        Date now = new Date(nowStamp);
        if(!DateUtils.isSameDay(now, lastTime)) { //如果不是同一天，取货码就重置为1
            OrderServiceImpl.TakeCode = 0;
        }
        OrderServiceImpl.TakeCode += 1;
        OrderServiceImpl.LastTime = nowStamp;
        order.setTakeCode(OrderServiceImpl.TakeCode);

        System.out.println(order);
        orderMapper.userAdd(order);
    }

    @Override
    public PageInfo<OrderVO> findPaperByPage(int pageNum, int pageSize, int storeId) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderVO> orderVOList = orderMapper.findByStoreId(storeId);
        PageInfo<OrderVO> pageInfo =  new PageInfo<>(orderVOList);
        return pageInfo;
    }

    @Override
    public Order findByOrderId(int orderId) {
        Order order = orderMapper.findByOrderId(orderId);
        return order;
    }
}
