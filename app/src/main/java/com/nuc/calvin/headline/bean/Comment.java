package com.nuc.calvin.headline.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Comment implements Serializable {
    /**
     * 评论id
     */
    private Integer commentId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 评论时间
     */
    private Date commentTime;

    /**
     * 被评论文章作者昵称
     */
    private String authorName;
    /**
     * 评论回复数
     */
    private int countReply;

    /**
     * 回复列表
     */
    private List<Reply> replyList;
    private User user;
    private Article article;

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getCountReply() {
        return countReply;
    }

    public void setCountReply(int countReply) {
        this.countReply = countReply;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
