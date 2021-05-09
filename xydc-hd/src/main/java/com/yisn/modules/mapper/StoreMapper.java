package com.yisn.modules.mapper;

import com.yisn.modules.entity.Store;
import com.yisn.modules.vo.StoreVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yisn on 2021/04/21/14:23
 */
@Repository
public interface StoreMapper {
    void create(Store store);

    Store findByUserId(@Param("userId") int userId);

    void update(Store store);

    void displayChangeByStoreId(@Param("storeId") int storeId,
                                @Param("display") int display);

    void autoChangeByStoreId(@Param("storeId") int storeId,
                             @Param("auto") boolean auto);

    void deleteByStoreId(@Param("storeId")int storeId);

    Store findByStoreId(@Param("storeId") int storeId);

    List<StoreVO> findStoreWithProduct(Store store);

    List<Store> find(Store store);
}
