package com.csl.mapper.shopping;

import com.csl.pojo.shopping.UserAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapperCustom {
    public List<UserAddress> queryUserAddressList(@Param("userId") String userId);

    public void addUserNewAddress(@Param("userAddress") UserAddress userAddress);
}
