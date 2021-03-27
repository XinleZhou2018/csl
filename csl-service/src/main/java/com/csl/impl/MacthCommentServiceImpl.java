package com.csl.impl;

import com.csl.mapper.MatchCommentMapperCustom;
import com.csl.pojo.vo.MatchCommentReplyVO;
import com.csl.pojo.vo.MatchCommentVO;
import com.csl.service.MatchCommentService;
import com.csl.utils.UserIdThreadLocal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MacthCommentServiceImpl implements MatchCommentService {

    @Autowired
    private MatchCommentMapperCustom matchCommentMapperCustom;

    @Override
    public PageInfo<MatchCommentVO> queryMatchComments(Long matchId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<MatchCommentVO> matchComments = matchCommentMapperCustom.queryMatchComments(matchId);
        PageInfo pageInfo = new PageInfo(matchComments);
        return pageInfo;
    }

    @Override
    public PageInfo<MatchCommentReplyVO> queryMatchCommentReplies(Long commentId, Integer page, Integer pageSize){
        PageHelper.startPage(page, pageSize);

        List<MatchCommentReplyVO> matchCommentReplies = matchCommentMapperCustom.queryMatchCommentReplies(commentId);
        PageInfo pageInfo = new PageInfo(matchCommentReplies);
        return pageInfo;
    }

    @Override
    public void saveComment(Long matchId, String content){

        //获取用户id
        String userid = UserIdThreadLocal.get();

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userid);
        map.put("matchId", matchId);
        map.put("content", content);

        matchCommentMapperCustom.saveComment(map);
    }

    @Override
    public void saveCommentReply(Long commentId, String content){
        //获取用户id
        String userid = UserIdThreadLocal.get();

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userid);
        map.put("commentId", commentId);
        map.put("content", content);

        matchCommentMapperCustom.saveCommentReply(map);
    }
}
