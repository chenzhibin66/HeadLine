package com.nuc.calvin.headline.bean;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    private int commentId;
    private int user;//评论发布人
    private int toUser;
    private String commentContent;
    private Date commentTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
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
