package com.csl.controller.shopping;

import com.csl.service.shopping.OrderService;
import com.csl.utils.ResultObject;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@Validated
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //用户下单接口 TODO


    //我的订单接口


    //模拟购买商品（规格）接口
    //模拟超卖问题，解决
    @GetMapping("/buyItem")
    public ResultObject simulateBuyItemSpec(@RequestParam(required = false) @NotBlank(message = "itemId不能为空") String specId,
                                            @RequestParam(required = false) @Min(value = 1, message = "购买数量不少于1") Integer buyCounts) {
        orderService.decreaseItemCounts(specId, buyCounts);
        return ResultObject.success(null);
    }

}
