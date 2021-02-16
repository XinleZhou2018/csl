package com.csl.impl;

import com.csl.mapper.MatchCommentMapperCustom;
import com.csl.pojo.vo.MatchCommentReplyVO;
import com.csl.pojo.vo.MatchCommentVO;
import com.csl.service.MatchCommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MacthCommentServiceImpl implements MatchCommentService {

    @Autowired
    MatchCommentMapperCustom matchCommentMapperCustom;

    @Override
    public PageInfo<MatchCommentVO> queryMatchComments(Long matchId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<MatchCommentVO> matchComments = matchCommentMapperCustom.queryMatchComments(matchId);
        PageInfo pageInfo = new PageInfo(matchComments);
        return pageInfo;
    }

    public PageInfo<MatchCommentReplyVO> queryMatchCommentReplies(Long commentId, Integer page, Integer pageSize){
        PageHelper.startPage(page, pageSize);

        List<MatchCommentReplyVO> matchCommentReplies = matchCommentMapperCustom.queryMatchCommentReplies(commentId);
        PageInfo pageInfo = new PageInfo(matchCommentReplies);
        return pageInfo;
    }

}
