package com.csl.service.shopping;

import com.csl.pojo.vo.shopping.ItemCommentLevelVO;
import com.csl.pojo.vo.shopping.ItemCommentVO;
import com.github.pagehelper.PageInfo;

public interface ItemCommentService {
    public ItemCommentLevelVO queryItemCommentLevel(String itemId);

    public PageInfo<ItemCommentVO> queryItemComments(String itemId, Integer level, Integer page, Integer pageSize);
}
