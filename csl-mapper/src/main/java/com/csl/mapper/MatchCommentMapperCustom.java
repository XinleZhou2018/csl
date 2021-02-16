package com.csl.mapper;

import com.csl.pojo.vo.MatchCommentReplyVO;
import com.csl.pojo.vo.MatchCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchCommentMapperCustom {
    public List<MatchCommentVO> queryMatchComments(@Param("matchId") Long matchId);
    public List<MatchCommentReplyVO> queryMatchCommentReplies(@Param("commentId") Long commentId);
}
