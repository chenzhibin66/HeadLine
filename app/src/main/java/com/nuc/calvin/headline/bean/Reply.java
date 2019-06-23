package com.nuc.calvin.headline.bean;

import java.util.Date;

public class Reply {
    /**
     * 回复id
     */
    private Integer replyId;
    /**
     * 评论id
     */
    private Integer commentId;
    /**
     * from 哪个user
     */
    private Integer fromId;
    /**
     * to哪个user
     */
    private Integer toId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * from 昵称
     */
    private String fromName;
    /**
     * to 昵称
     */
    private String toName;
    /**
     * 头像
     */
    private String fromImg;
    private String toImg;
    /**
     * 源文章
     */
    private Article article;
    /**
     * 回复时间
     */
    private Date replyTime;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getFromImg() {
        return fromImg;
    }

    public void setFromImg(String fromImg) {
        this.fromImg = fromImg;
    }

    public String getToImg() {
        return toImg;
    }

    public void setToImg(String toImg) {
        this.toImg = toImg;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }
}
