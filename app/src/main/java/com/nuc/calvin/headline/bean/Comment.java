package com.nuc.calvin.headline.bean;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    //评论id
    private int id;
    //评论的文章id
    private int articleId;
    //评论发布人
    private int fromUserId;
    //评论接收人
    private int toUserId;
    //评论内容
    private String commentContent;
    //评论时间
    private Date commentTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
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
}
