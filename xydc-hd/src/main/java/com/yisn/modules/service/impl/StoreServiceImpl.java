package com.yisn.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Store;
import com.yisn.modules.mapper.StoreMapper;
import com.yisn.modules.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yisn on 2021/04/21/14:21
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public void create(Store store) {
        storeMapper.create(store);
        return;
    }

    @Override
    public Store findByUserId(int userId) {
        return storeMapper.findByUserId(userId);
    }

    @Override
    public void updata(Store store) {
        storeMapper.update(store);
        return;
    }

    @Override
    public PageInfo<Store> findPaperByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Store> storeList = storeMapper.findAllStore();
        PageInfo<Store> pageInfo = new PageInfo<>(storeList);
        return pageInfo;
    }

    @Override
    public void displayChangeByStoreId(int storeId, int display) {
        storeMapper.displayChangeByStoreId(storeId, display);
    }

    @Override
    public void autoChangeByStoreId(int storeId, boolean auto) {
        storeMapper.autoChangeByStoreId(storeId, auto);
    }

    @Override
    public void deleteByStoreId(int storeId) {
        storeMapper.deleteByStoreId(storeId);
    }

    @Override
    public Store findeByStoreId(int storeId) {
        Store store = storeMapper.findByStoreId(storeId);
        return store;
    }
}
