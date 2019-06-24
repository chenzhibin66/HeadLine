package com.nuc.calvin.headline.bean;

import java.util.Date;
import java.util.List;

public class Article {
    /**
     * 文章id
     */
    private Integer articleId;
    /**
     * 作者id
     */
    private Integer userId;
    /**
     * 发布时间
     */
    private Date postTime;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章url
     */
    private String articleUrl;

    /**
     * 是否广告   1-是   0-否
     */
    private int isAdvertorial;

    /**
     * 赞 0-否  1-是
     */
    private int likes;
    /**
     * 收藏 0-否  1-是
     */
    private int collect;
    /**
     * 被评论次数
     */
    private int commentCount;
    /**
     * 被点赞数
     */
    private int likeCount;
    /**
     * 扩展user
     */
    private User user;

    public int getIsAdvertorial() {
        return isAdvertorial;
    }

    public void setIsAdvertorial(int isAdvertorial) {
        this.isAdvertorial = isAdvertorial;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
