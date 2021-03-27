package com.csl.impl.shopping;

import com.csl.mapper.shopping.ItemMapperCustom;
import com.csl.pojo.shopping.Items;
import com.csl.pojo.shopping.ItemsImg;
import com.csl.pojo.shopping.ItemsParam;
import com.csl.pojo.shopping.ItemsSpec;
import com.csl.pojo.vo.shopping.SearchItemsVO;
import com.csl.service.shopping.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapperCustom itemMapperCustom;


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

    @Override
    public PageInfo<SearchItemsVO> searchItems(String keyword, Integer sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> searchItemsVOs = itemMapperCustom.searchItems(map);
        return new PageInfo<>(searchItemsVOs);
    }

    @Override
    public PageInfo<SearchItemsVO> searchItemsByThirdCatId(Integer catId, Integer sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("catId", catId);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> searchItemsVOs = itemMapperCustom.searchItemsByThirdCatId(map);
        return new PageInfo<>(searchItemsVOs);
    }
}
