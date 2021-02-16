package com.csl.controller;

import com.csl.service.MatchCommentService;
import com.csl.utils.ResultObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/matchComment")
@Validated
public class MatchCommentController {

    @Autowired
    MatchCommentService matchCommentService;

    @GetMapping("/commentList")
    public ResultObject getCommentList(
            @RequestParam(required = false) @NotNull(message = "matchId不能为空") Long matchId,
            @RequestParam(required = false) @NotNull(message = "page不能为空") @Min(value = 1, message = "page大于1") Integer page,
            @RequestParam(required = false) @NotNull(message = "pageSize不能为空") @Min(value = 1, message = "pageCount大于1") Integer pageSize) {
        PageInfo pageInfo = matchCommentService.queryMatchComments(matchId, page, pageSize);
        return ResultObject.success(pageInfo);
    }

    @GetMapping("/commentReplyList")
    public ResultObject getCommentReplyList(@RequestParam(required = false) @NotNull(message = "commentId不能为空") Long commentId,
                                            @RequestParam(required = false) @NotNull(message = "page不能为空") @Min(value = 1, message = "page大于1") Integer page,
                                            @RequestParam(required = false) @NotNull(message = "pageSize不能为空") @Min(value = 1, message = "pageCount大于1") Integer pageSize){
        PageInfo pageInfo = matchCommentService.queryMatchCommentReplies(commentId, page, pageSize);
        return ResultObject.success(pageInfo);
    }
}
