package com.csl.controller.shopping;

import com.csl.pojo.vo.shopping.ItemCommentLevelVO;
import com.csl.pojo.vo.shopping.ItemCommentVO;
import com.csl.service.shopping.ItemCommentService;
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

@RestController
@Validated
@RequestMapping("/itemComments")
public class ItemCommentController {
    @Autowired
    ItemCommentService itemCommentService;

    @GetMapping("/commentLevel")
    public ResultObject getItemCommentLevel(
            @RequestParam(required = false) @NotBlank(message = "itemId不能为空") String itemId){
        ItemCommentLevelVO itemCommentLevelVO = itemCommentService.queryItemCommentLevel(itemId);
        return ResultObject.success(itemCommentLevelVO);
    }

    @GetMapping("/comments")
    public ResultObject getItemComments(@RequestParam(required = false) @NotBlank(message = "itemId不能为空") String itemId,
                                        @RequestParam(required = false) @Range(min = 1, max = 3, message = "level为1，2，3") Integer level,
                                        @RequestParam(required = false) @NotNull(message = "page不能为空") @Min(value = 1, message = "page大于1") Integer page,
                                        @RequestParam(required = false) @NotNull(message = "pageSize不能为空") @Min(value = 1, message = "pageSize大于1") Integer pageSize){
        PageInfo<ItemCommentVO> itemCommentVOPageInfo = itemCommentService.queryItemComments(itemId, level, page, pageSize);
        return ResultObject.success(itemCommentVOPageInfo);
    }
}
