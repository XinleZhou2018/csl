package com.csl.mapper.shopping;

import com.csl.pojo.vo.shopping.ItemCommentLevelVO;
import com.csl.pojo.vo.shopping.ItemCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemCommentMapperCustom {
    public ItemCommentLevelVO queryItemCommentLevel(@Param("itemId") String itemId);

    public List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> map);
}
