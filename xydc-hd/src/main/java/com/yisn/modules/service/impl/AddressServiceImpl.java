package com.yisn.modules.service.impl;

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
    public void setDefaultAddress(int nowAddressId) {
        int preAddressId = addressMapper.getDefaultAddressId();
        if(preAddressId != nowAddressId) {
            addressMapper.setDefault(preAddressId, false);
            addressMapper.setDefault(nowAddressId, true);
        }
    }
}
