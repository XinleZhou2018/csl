package com.csl.service.shopping;

import com.csl.pojo.bo.shopping.UserAddressBO;
import com.csl.pojo.shopping.UserAddress;

import java.util.List;

public interface AddressService {
    public List<UserAddress> queryUserAddressList();

    public void addNewAddress(UserAddressBO userAddressBO);
}
