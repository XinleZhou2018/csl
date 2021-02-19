package com.csl.controller;

import com.csl.utils.ResultObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@RestController注解中包含了@ResponseBody注解
@RequestMapping("/user")
@Validated
public class UserController {

    @PostMapping("/login")
    public ResultObject login(){
        return null;
    }

    @PostMapping("/changeUserInfo")
    public ResultObject changeUserInfo(){
        return null;
    }
}
