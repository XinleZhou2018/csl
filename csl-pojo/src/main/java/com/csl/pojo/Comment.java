package com.csl.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Comment {
    @Id
    private Long id;

    @Column(name = "match_id")
    private Long matchId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "comment_time")
    private Date commentTime;

    private String content;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return match_id
     */
    public Long getMatchId() {
        return matchId;
    }

    /**
     * @param matchId
     */
    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return comment_time
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * @param commentTime
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}