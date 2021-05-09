package com.yisn.modules.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yisn.modules.entity.Address;
import com.yisn.modules.mapper.AddressMapper;
import com.yisn.modules.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yisn on 2021/04/28/16:18
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public void create(Address address) {
        addressMapper.create(address);
        return;
    }

    @Override
    public List<Address> findAddressByUserId(int userId) {
        List<Address> addressList = addressMapper.findAddressByUserId(userId);
        return addressList;
    }

    @Override
    public void deleteByAddressId(int addressId) {
        addressMapper.deleteByAddressId(addressId);
    }

    @Override
    public void setDefaultAddress(int nowAddressId, int userId) {
        Integer preAddressId = addressMapper.getDefaultAddressId(userId);
        Address address = new Address();
        address.setAddressId(nowAddressId);
        address.setDef(true);
        addressMapper.update(address);
        if(preAddressId != null && preAddressId != nowAddressId) {
            address.setAddressId(preAddressId);
            address.setDef(false);
            addressMapper.update(address);
        }
    }

    @Override
    public PageInfo<Address> findPaperByPage(Address condition, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Address> AddressList = addressMapper.find(condition);
        PageInfo<Address> pageInfo = new PageInfo<>(AddressList);
        return pageInfo;
    }

    @Override
    public Address findSingle(int addressId) {
        return addressMapper.findSingle(addressId);
    }

    @Override
    public void update(Address address) {
        addressMapper.update(address);
        return;
    }
}
