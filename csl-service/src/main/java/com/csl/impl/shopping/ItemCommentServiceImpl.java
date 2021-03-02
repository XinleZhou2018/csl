package com.csl.impl.shopping;

import com.csl.mapper.shopping.ItemCommentMapperCustom;
import com.csl.pojo.vo.shopping.ItemCommentLevelVO;
import com.csl.pojo.vo.shopping.ItemCommentVO;
import com.csl.service.shopping.ItemCommentService;
import com.csl.utils.DesensitizationUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemCommentServiceImpl implements ItemCommentService {
    @Autowired
    ItemCommentMapperCustom itemCommentMapperCustom;

    @Override
    public ItemCommentLevelVO queryItemCommentLevel(String itemId) {
        return itemCommentMapperCustom.queryItemCommentLevel(itemId);
    }

    @Override
    public PageInfo<ItemCommentVO> queryItemComments(String itemId, Integer level, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);

        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> itemCommentVOList = itemCommentMapperCustom.queryItemComments(map);
        //用户昵称脱敏
        for (ItemCommentVO itemCommentVO: itemCommentVOList){
            itemCommentVO.setNickname(DesensitizationUtil.commonDisplay(itemCommentVO.getNickname()));
        }
        return new PageInfo<>(itemCommentVOList);
    }
}
