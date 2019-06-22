package com.nuc.calvin.headline.bean;

import java.util.Date;
import java.util.List;

public class Article {
    //文章id
    private int articleId;
    //是否广告
    private boolean isAdvertorial;
    //作者id
    private int authorId;
    //是否被点赞
    private boolean isfavoured;
    //点赞id
    private int favourId;
    //点赞数量
    private int favourCount;
    //评论id
    private int commentId;
    //评论数量
    private int commentCount;
    //是否被收藏
    private boolean collect;
    //文章url
    private String articleUrl;
    //发布时间
    private Date createTime;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public boolean isAdvertorial() {
        return isAdvertorial;
    }

    public void setAdvertorial(boolean advertorial) {
        isAdvertorial = advertorial;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public boolean isIsfavoured() {
        return isfavoured;
    }

    public void setIsfavoured(boolean isfavoured) {
        this.isfavoured = isfavoured;
    }

    public int getFavourId() {
        return favourId;
    }

    public void setFavourId(int favourId) {
        this.favourId = favourId;
    }

    public int getFavourCount() {
        return favourCount;
    }

    public void setFavourCount(int favourCount) {
        this.favourCount = favourCount;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
