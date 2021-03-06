package com.yisn.modules.service;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Store;
import com.yisn.modules.vo.StoreVO;

import java.util.List;

/**
 * Created by yisn on 2021/04/21/14:20
 */
public interface StoreService {
    void create(Store store);

    Store findByUserId(int userId);

    void updata(Store store);

    PageInfo<Store> findPaperByPage(Store store, int pageNum, int pageSize);

    void displayChangeByStoreId(int storeId, int display);

    void autoChangeByStoreId(int storeId, boolean auto);

    void deleteByStoreId(int storeId);

    Store findeByStoreId(int storeId);

    List<StoreVO> findStoreWithProduct(Store store);
}
