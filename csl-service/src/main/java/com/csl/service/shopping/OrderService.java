package com.csl.service.shopping;

public interface OrderService {
    //减少商品规格的库存
    public void decreaseItemCounts(String specId, Integer buyCounts);
}
