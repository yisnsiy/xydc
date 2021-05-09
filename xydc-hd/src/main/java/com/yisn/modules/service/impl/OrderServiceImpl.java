package com.yisn.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Order;
import com.yisn.modules.mapper.OrderMapper;
import com.yisn.modules.service.OrderService;
import com.yisn.modules.vo.OrderVO;
import com.yisn.modules.vo.statistic;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public PageInfo<OrderVO> findPaperByPage(int pageNum, int pageSize, Order condition) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderVO> orderVOList = orderMapper.findOrderVO(condition);
        PageInfo<OrderVO> pageInfo =  new PageInfo<>(orderVOList);
        return pageInfo;
    }

    @Override
    public Order findByOrderId(int orderId) {
        Order order = orderMapper.findByOrderId(orderId);
        return order;
    }

    @Override
    public void storeAdd(int orderId, Long storeOrder) {
        orderMapper.storeAdd(orderId, storeOrder);
        return;
    }

    @Override
    public int carrierAdd(Order order) {
        int cnt = orderMapper.carrierAdd(order);
        System.out.println(cnt);
        return cnt;
    }

    @Override
    public void update(Order order) {
        orderMapper.update(order);
        return;
    }

    @Override
    public void delete(int orderId) {
        orderMapper.delete(orderId);
        return;
    }
//    将所有分析数据放到map中
    @Override
    public Map<Integer, statistic> analysis(int identity, Order order) {
        Map<Integer, statistic> storeScoreMap = new HashMap<>();
        List<OrderVO> orderVOList = orderMapper.findOrderVO(order);
        for(int i = 0; i < orderVOList.size(); ++ i) {
            OrderVO orderVO = orderVOList.get(i);
            int id;
            String name;
            double score;
            if(identity == 3) {
                id = orderVO.getStoreId();
                name = orderVO.getStore().getStoreName();
                score = orderVO.getStoreScore();
            }else if(identity == 2){
                id = orderVO.getCarrierId();
                name = orderVO.getCarrier().getNickname();
                score = orderVO.getCarrierScore();
            }else {
                return storeScoreMap;
            }
            if(!storeScoreMap.containsKey(id)) { //不包含键值，先初始化数据
                storeScoreMap.put(id, new statistic());
                storeScoreMap.get(id).setId(id);
                storeScoreMap.get(id).setName(name); //店铺名字
                storeScoreMap.get(id).setNum(0); //店铺有效评价订单总数
                storeScoreMap.get(id).setScore(0); //店铺有效评价订单评分总和
            }
            storeScoreMap.get(id).setNum(storeScoreMap.get(id).getNum() + 1);
            storeScoreMap.get(id).setScore(storeScoreMap.get(id).getScore() + score);
        }
        return storeScoreMap;
    }

//    将obj解析为int
    int pn(Object obj) {
        return Integer.parseInt(obj.toString());
    }

}
