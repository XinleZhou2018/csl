package com.csl.impl.shopping;

import com.csl.mapper.shopping.ItemMapperCustom;
import com.csl.mapper.shopping.OrderMapperCustom;
import com.csl.objects.HttpException;
import com.csl.service.shopping.OrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static jodd.util.ThreadUtil.sleep;


@Service
public class OrderServiceImpl implements OrderService {
    private static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private ItemMapperCustom itemMapperCustom;

    @Autowired
    private RedissonClient redissonClient;


    @Override
    public void decreaseItemCounts(String specId, Integer buyCounts) {

        //分布式锁
        RLock rLock = redissonClient.getLock("SPECID"+specId);
        log.info("进入了方法");

        try {
            /**
             * 1、获取分布式锁，锁的超时时间是5秒,5秒后释放。可能try方法中程序超时严重，不加超时，锁就始终无法释放
             *  2、获取到了锁，进行后续的业务操作
             */
            rLock.lock(30, TimeUnit.SECONDS);
            log.info("进入了锁");
            //模拟耗时操作
//            Thread.sleep(10000);

            int result = itemMapperCustom.decreaseItemSpecStock(specId, buyCounts);
            if (result != 1) {
                //购买失败，库存不足
                throw new HttpException(10010, 200);
            }


        }catch (Exception e){
//            throw 购买失败
        }finally {
            /**
             *  不管业务是否操作正确，随后都要释放掉分布式锁
             *   如果不释放，过了超时时间也会自动释放
             */
            log.info("释放了锁");
            rLock.unlock();
        }

        log.info("方法执行完成");

    }
}
