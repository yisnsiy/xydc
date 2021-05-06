package com.yisn.modules.mapper;

import com.yisn.modules.entity.Order;
import com.yisn.modules.vo.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yisn on 2021/05/06/15:45
 */
@Repository
public interface OrderMapper {
    void userAdd(Order order);

    List<OrderVO> findByStoreId(@Param("storeId") int storeId);

    Order findByOrderId(@Param("orderId")int orderId);
}
