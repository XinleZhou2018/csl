package com.csl.controller.shopping;

import com.csl.pojo.bo.shopping.UserAddressBO;
import com.csl.pojo.shopping.UserAddress;
import com.csl.service.shopping.AddressService;
import com.csl.utils.ResultObject;
import com.csl.validationGroups.UserAddressModifyGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.List;

@RestController
@Validated
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/list")
    public ResultObject getUserAddressList(){
        List<UserAddress> userAddressList = addressService.queryUserAddressList();
        return ResultObject.success(userAddressList);
    }

    /**
     * (@RequestBody(required = false)因为，如果不写，默认为true，userAddressBO不传直接就会被required的验证拦截，不能再验证后面的@NotNull，也就不会返回NotNull里面的message
     * @param userAddressBO
     * @return
     */
    @PostMapping("/add")
    public ResultObject addNewAddress(@RequestBody(required = false) @NotNull(message = "userAddress不能为空") @Validated UserAddressBO userAddressBO){
        addressService.addNewAddress(userAddressBO);
        return ResultObject.success(null);
    }

    @PostMapping("/modify")
    public ResultObject modifyUserAddress(@RequestBody(required = false) @NotNull(message = "userAddress不能为空") @Validated({UserAddressModifyGroup.class, Default.class}) UserAddressBO userAddressBO){
        return null;
    }
}
