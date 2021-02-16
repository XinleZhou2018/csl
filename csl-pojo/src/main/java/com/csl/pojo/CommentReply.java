package com.csl.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "comment_reply")
public class CommentReply {
    @Id
    private Long id;

    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "reply_time")
    private Date replyTime;

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
     * @return comment_id
     */
    public Long getCommentId() {
        return commentId;
    }

    /**
     * @param commentId
     */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
     * @return reply_time
     */
    public Date getReplyTime() {
        return replyTime;
    }

    /**
     * @param replyTime
     */
    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
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