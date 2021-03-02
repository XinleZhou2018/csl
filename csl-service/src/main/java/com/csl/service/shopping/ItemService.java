package com.csl.service.shopping;

import com.csl.pojo.shopping.Items;
import com.csl.pojo.shopping.ItemsImg;
import com.csl.pojo.shopping.ItemsParam;
import com.csl.pojo.shopping.ItemsSpec;
import com.csl.pojo.vo.shopping.SearchItemsVO;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ItemService {
    /**
     * 根据商品ID查询详情
     * @param itemId
     * @return
     */
    public Items queryItemById(String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    public List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    public List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    public ItemsParam queryItemParam(String itemId);

    /**
     * 根据关键字搜索商品
     * @param keyword, sort
     * @return
     */
    public PageInfo<SearchItemsVO> searchItems(String keyword, Integer sort, Integer page, Integer pageSize);

    public PageInfo<SearchItemsVO> searchItemsByThirdCatId(Integer catId, Integer sort, Integer page, Integer pageSize);
}
