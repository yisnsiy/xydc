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

    List<OrderVO> findOrderVO(Order condition);

    Order findByOrderId(@Param("orderId")int orderId);

    void userAdd(Order order);

    void storeAdd(@Param("orderId") int orderId,
                  @Param("storeOrder") Long storeOrder);

    int carrierAdd(Order order);

    void update(Order order);

    void delete(@Param("orderId") int orderId);
}
