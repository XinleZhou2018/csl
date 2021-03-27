package com.csl.controller;

import com.csl.service.MatchCommentService;
import com.csl.utils.ResultObject;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/matchComment")
@Validated
public class MatchCommentController {

    @Autowired
    private MatchCommentService matchCommentService;

    @GetMapping("/commentList")
    public ResultObject getCommentList(
            @RequestParam(required = false) @NotNull(message = "matchId不能为空") Long matchId,
            @RequestParam(required = false) @NotNull(message = "page不能为空") @Min(value = 1, message = "page大于1") Integer page,
            @RequestParam(required = false) @NotNull(message = "pageSize不能为空") @Min(value = 1, message = "pageSize大于1") Integer pageSize) {
        PageInfo pageInfo = matchCommentService.queryMatchComments(matchId, page, pageSize);
        return ResultObject.success(pageInfo);
    }

    @GetMapping("/commentReplyList")
    public ResultObject getCommentReplyList(@RequestParam(required = false) @NotNull(message = "commentId不能为空") Long commentId,
                                            @RequestParam(required = false) @NotNull(message = "page不能为空") @Min(value = 1, message = "page大于1") Integer page,
                                            @RequestParam(required = false) @NotNull(message = "pageSize不能为空") @Min(value = 1, message = "pageSize大于1") Integer pageSize){
        PageInfo pageInfo = matchCommentService.queryMatchCommentReplies(commentId, page, pageSize);
        return ResultObject.success(pageInfo);
    }

    @PostMapping("/comment")
    public ResultObject comment(
            @RequestParam(required = false) @NotNull(message = "matchId不能为空") Long matchId,
            @RequestParam(required = false) @NotBlank(message = "content不能为空") String content){
        matchCommentService.saveComment(matchId, content);
        return ResultObject.success(null);
    }

/*    @NotNull://CharSequence, Collection, Map 和 Array 对象不能是 null, 但可以是空集（size = 0）。
    @NotEmpty://CharSequence, Collection, Map 和 Array 对象不能是 null 并且相关对象的 size 大于 0。
    @NotBlank://String 不能是 null 且去除两端空白字符后的长度（trimmed length）大于 0。*/
    @PostMapping("/replyComment")
    public ResultObject replyComment(
            @RequestParam(required = false) @NotNull(message = "commentId不能为空") Long commentId,
            @RequestParam(required = false) @NotBlank(message = "content不能为空") String content){
        matchCommentService.saveCommentReply(commentId, content);
        return ResultObject.success(null);
    }
}
