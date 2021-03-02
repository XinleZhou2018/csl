package com.csl.mapper.shopping;

import com.csl.pojo.shopping.Items;
import com.csl.pojo.shopping.ItemsImg;
import com.csl.pojo.shopping.ItemsParam;
import com.csl.pojo.shopping.ItemsSpec;
import com.csl.pojo.vo.shopping.SearchItemsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ItemMapperCustom {
    public Items queryItemById(@Param("itemId") String itemId);
    public List<ItemsImg> queryItemImgList(@Param("itemId") String itemId);
    public List<ItemsSpec> queryItemSpecList(@Param("itemId") String itemId);
    public ItemsParam queryItemParamById(@Param("itemId") String itemId);

    public List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> map);
    public List<SearchItemsVO> searchItemsByThirdCatId(@Param("paramsMap") Map<String, Object> map);

}
