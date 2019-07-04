package com.nuc.calvin.headline.json;

import com.nuc.calvin.headline.bean.Article;
import com.nuc.calvin.headline.bean.User;


public class BannerJs {

    private Integer BannerId;
    private Integer articleId;
    private String bannerImage;
    private Article article;
    private User user;

    public Integer getBannerId() {
        return BannerId;
    }

    public void setBannerId(Integer bannerId) {
        BannerId = bannerId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "BannerJs{" +
                "BannerId=" + BannerId +
                ", articleId=" + articleId +
                ", bannerImage='" + bannerImage + '\'' +
                ", article=" + article +
                ", user=" + user +
                '}';
    }
}
