package com.nuc.calvin.headline.json;

import com.nuc.calvin.headline.bean.ArticleCustom;
import com.nuc.calvin.headline.bean.User;
import com.nuc.calvin.headline.bean.UserCustom;

import java.util.Date;

public class CollectJs {
    private Integer collectId;
    private Integer userId;
    private Date collectTime;
    private String date;
    private String username;
    private String userImg;

    private UserCustom user;
    private ArticleCustom article;
    private Integer articleId;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public UserCustom getUser() {
        return user;
    }

    public void setUser(UserCustom user) {
        this.user = user;
    }

    public ArticleCustom getArticle() {
        return article;
    }

    public void setArticle(ArticleCustom article) {
        this.article = article;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
