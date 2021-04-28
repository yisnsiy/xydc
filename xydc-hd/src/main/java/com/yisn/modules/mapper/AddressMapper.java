package com.yisn.modules.mapper;

import com.yisn.modules.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yisn on 2021/04/28/16:18
 */
@Repository
public interface AddressMapper {
    void create(Address address);

    List<Address> findAddressByUserId(@Param("userId") int userId);

    void deleteByAddressId(@Param("addressId") int addressId);

    int getDefaultAddressId();

    void setDefault(@Param("addressId") int addressId,
                    @Param("def") boolean def);
}
