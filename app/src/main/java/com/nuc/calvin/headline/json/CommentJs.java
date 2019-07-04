package com.nuc.calvin.headline.json;

import com.nuc.calvin.headline.bean.Article;
import com.nuc.calvin.headline.bean.Reply;
import com.nuc.calvin.headline.bean.User;

import java.util.Date;
import java.util.List;

public class CommentJs {

    private Integer commentId;
    private Integer userId;
    private Integer articleId;
    private String commentContent;
    private Date commentTime;
    private User user;
    private Article article;
    private String username;
    private String time;
    private int countReply;
    private List<Reply> replyList;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "CommentJs{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", articleId=" + articleId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", user=" + user +
                ", article=" + article +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", countReply=" + countReply +
                ", replyList=" + replyList +
                '}';
    }
}
