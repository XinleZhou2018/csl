package com.csl.controller.shopping;

import com.csl.pojo.shopping.Items;
import com.csl.pojo.shopping.ItemsImg;
import com.csl.pojo.shopping.ItemsParam;
import com.csl.pojo.shopping.ItemsSpec;
import com.csl.pojo.vo.shopping.ItemDetailVO;
import com.csl.service.shopping.ItemService;
import com.csl.utils.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

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
}
