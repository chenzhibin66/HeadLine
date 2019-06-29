package com.nuc.calvin.headline.bean;



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
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章url
     */
    private String articleUrl;


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

}
