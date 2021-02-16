package com.csl.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Match {
    @Id
    private Long id;

    private String home;

    private String guest;

    @Column(name = "match_date")
    private Date matchDate;

    @Column(name = "match_type")
    private String matchType;

    private String description;

    private String score;

    @Column(name = "comment_on")
    private Byte commentOn;

    /**
     * 管理员添加比赛的时间
     */
    @Column(name = "add_date")
    private Date addDate;

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
     * @return home
     */
    public String getHome() {
        return home;
    }

    /**
     * @param home
     */
    public void setHome(String home) {
        this.home = home;
    }

    /**
     * @return guest
     */
    public String getGuest() {
        return guest;
    }

    /**
     * @param guest
     */
    public void setGuest(String guest) {
        this.guest = guest;
    }

    /**
     * @return match_date
     */
    public Date getMatchDate() {
        return matchDate;
    }

    /**
     * @param matchDate
     */
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * @return match_type
     */
    public String getMatchType() {
        return matchType;
    }

    /**
     * @param matchType
     */
    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return score
     */
    public String getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * @return comment_on
     */
    public Byte getCommentOn() {
        return commentOn;
    }

    /**
     * @param commentOn
     */
    public void setCommentOn(Byte commentOn) {
        this.commentOn = commentOn;
    }

    /**
     * 获取管理员添加比赛的时间
     *
     * @return add_date - 管理员添加比赛的时间
     */
    public Date getAddDate() {
        return addDate;
    }

    /**
     * 设置管理员添加比赛的时间
     *
     * @param addDate 管理员添加比赛的时间
     */
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}