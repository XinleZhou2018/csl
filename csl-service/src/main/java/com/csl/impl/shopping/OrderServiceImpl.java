package com.csl.impl.shopping;

import com.csl.mapper.shopping.ItemMapperCustom;
import com.csl.mapper.shopping.OrderMapperCustom;
import com.csl.objects.HttpException;
import com.csl.service.shopping.OrderService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ItemMapperCustom itemMapperCustom;
    

    @Override
    public void decreaseItemCounts(String specId, Integer buyCounts) {
        //分布式锁

        int result = itemMapperCustom.decreaseItemSpecStock(specId, buyCounts);
        if (result != 1) {
            //购买失败，库存不足
            throw new HttpException(10010, 200);
        }
    }
}
