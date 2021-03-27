package com.csl.impl.shopping;

import com.csl.mapper.shopping.AddressMapperCustom;
import com.csl.pojo.bo.shopping.UserAddressBO;
import com.csl.pojo.shopping.UserAddress;
import com.csl.service.shopping.AddressService;
import com.csl.utils.UserIdThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapperCustom addressMapperCustom;

    @Override
    public List<UserAddress> queryUserAddressList() {
        String userId = UserIdThreadLocal.get();
        return addressMapperCustom.queryUserAddressList(userId);
    }

    @Override
    public void addNewAddress(UserAddressBO userAddressBO) {
        //如果当前用户的地址列表为空，则新增的第一条是默认地址
        Integer isDefault = 0;
        List<UserAddress> userAddressList = this.queryUserAddressList();
        if (ObjectUtils.isEmpty(userAddressList)){
            isDefault  = 1;
        }

        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressBO, userAddress);

        userAddress.setIsDefault(isDefault);
        addressMapperCustom.addUserNewAddress(userAddress);
    }
}
