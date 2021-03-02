package com.csl.pojo.bo.shopping;

import com.csl.validationGroups.UserAddressModifyGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserAddressBO {
    @NotBlank(message = "addressId不能为空", groups = UserAddressModifyGroup.class)
    private String addressId;

    @NotBlank(message = "receiver不能为空")
    @Length(min = 1, max = 12, message = "收货人姓名不能超过12")
    private String receiver;

    @NotBlank(message = "mobile不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    @NotBlank(message = "province不能为空")
    private String province;

    @NotBlank(message = "city不能为空")
    private String city;

    @NotBlank(message = "district不能为空")
    private String district;

    @NotBlank(message = "detail不能为空")
    private String detail;

    String extand;


    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getExtand() {
        return extand;
    }

    public void setExtand(String extand) {
        this.extand = extand;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
