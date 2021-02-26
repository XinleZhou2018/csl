package com.csl.impl.shopping;

import com.csl.mapper.shopping.ItemMapperCustom;
import com.csl.pojo.shopping.Items;
import com.csl.pojo.shopping.ItemsImg;
import com.csl.pojo.shopping.ItemsParam;
import com.csl.pojo.shopping.ItemsSpec;
import com.csl.service.shopping.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemMapperCustom itemMapperCustom;

    @Override
    public Items queryItemById(String itemId) {
        return itemMapperCustom.queryItemById(itemId);
    }

    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        return itemMapperCustom.queryItemImgList(itemId);
    }

    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        return itemMapperCustom.queryItemSpecList(itemId);
    }

    @Override
    public ItemsParam queryItemParam(String itemId) {
        return itemMapperCustom.queryItemParamById(itemId);
    }
}
