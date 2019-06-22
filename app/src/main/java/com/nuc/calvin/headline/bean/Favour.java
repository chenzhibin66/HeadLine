package com.nuc.calvin.headline.bean;

import java.util.Date;

public class Favour {

    private int id;
    //点赞用户的id
    private int favourUserId;
    //点赞的文章id
    private int articleId;
    //点赞数量
    private int likeCount;
    //点赞时间
    private Date favourTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavourUserId() {
        return favourUserId;
    }

    public void setFavourUserId(int favourUserId) {
        this.favourUserId = favourUserId;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getFavourTime() {
        return favourTime;
    }

    public void setFavourTime(Date favourTime) {
        this.favourTime = favourTime;
    }
}
