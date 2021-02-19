package com.csl.service;

import com.csl.pojo.vo.MatchCommentReplyVO;
import com.csl.pojo.vo.MatchCommentVO;
import com.github.pagehelper.PageInfo;

public interface MatchCommentService {
    public PageInfo<MatchCommentVO> queryMatchComments(Long matchId, Integer page, Integer pageSize);
    public PageInfo<MatchCommentReplyVO> queryMatchCommentReplies(Long commentId, Integer page, Integer pageSize);

    public void saveComment(Long matchId, String content);
    public void saveCommentReply(Long commentId, String content);
}
