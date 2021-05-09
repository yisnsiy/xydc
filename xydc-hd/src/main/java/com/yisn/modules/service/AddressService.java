package com.yisn.modules.service;

import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Address;

import java.util.List;

/**
 * Created by yisn on 2021/04/28/16:17
 */
public interface AddressService {
    void create(Address address);

    List<Address> findAddressByUserId(int userId);

    void deleteByAddressId(int addressId);

    void setDefaultAddress(int addressId, int userId);

    PageInfo<Address> findPaperByPage(Address condition, int pageNum, int pageSize);

    Address findSingle(int addressId);

    void update(Address address);
}
