package com.csl.controller.shopping;

import com.csl.pojo.shopping.Items;
import com.csl.pojo.shopping.ItemsImg;
import com.csl.pojo.shopping.ItemsParam;
import com.csl.pojo.shopping.ItemsSpec;
import com.csl.pojo.vo.shopping.ItemDetailVO;
import com.csl.pojo.vo.shopping.SearchItemsVO;
import com.csl.service.shopping.ItemService;
import com.csl.utils.ResultObject;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping("/detail")
    public ResultObject getItemDetail(@RequestParam(required = false) @NotBlank(message = "itemId不能为空") String itemId){
        Items item = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemDetailVO itemDetailVO = new ItemDetailVO();
        itemDetailVO.setItem(item);
        itemDetailVO.setItemImgList(itemsImgs);
        itemDetailVO.setItemSpecList(itemsSpecs);
        itemDetailVO.setItemParams(itemsParam);
        return ResultObject.success(itemDetailVO);
    }

    /*
        根据搜索关键字搜索商品
        页面展示元素：商品表left join 商品图片（显示主图），还要显示商品各个规格中最便宜的价格，注意这个sql的写法。
        sort可以不传，如果传只能传1或者2
     */

    @GetMapping("/searchItems")
    public ResultObject searchItems(@RequestParam(required = false) @NotBlank(message = "keyword不能为空") String keyword,
                                    @RequestParam(required = false) @Range(min = 1, max = 2, message = "sort方式为1或2") Integer sort,
                                    @RequestParam(required = false) @NotNull(message = "page不能为空") @Min(value = 1, message = "page大于1") Integer page,
                                    @RequestParam(required = false) @NotNull(message = "pageSize不能为空") @Min(value = 1, message = "pageSize大于1") Integer pageSize){
        PageInfo<SearchItemsVO> pageInfo = itemService.searchItems(keyword, sort, page, pageSize);
        return ResultObject.success(pageInfo);
    }

        /*
        根据商品三级分类号搜索商品，首页展开分类视图后，点击三级分类名，进入商品搜索页面
        页面展示元素：商品表left join 商品图片（显示主图），还要显示商品各个规格中最便宜的价格，注意这个sql的写法。
        sort可以不传，如果传只能传1或者2
     */

    @GetMapping("/searchItemsByThirdCatId")
    public ResultObject searchItemsByThirdCatId(@RequestParam(required = false) @NotNull(message = "catId不能为空") Integer catId,
                                    @RequestParam(required = false) @Range(min = 1, max = 2, message = "sort方式为1或2") Integer sort,
                                    @RequestParam(required = false) @NotNull(message = "page不能为空") @Min(value = 1, message = "page大于1") Integer page,
                                    @RequestParam(required = false) @NotNull(message = "pageSize不能为空") @Min(value = 1, message = "pageSize大于1") Integer pageSize){
        PageInfo<SearchItemsVO> pageInfo = itemService.searchItemsByThirdCatId(catId, sort, page, pageSize);
        return ResultObject.success(pageInfo);
    }
}
