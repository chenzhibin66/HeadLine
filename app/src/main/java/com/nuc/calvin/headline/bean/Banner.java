package com.nuc.calvin.headline.bean;

public class Banner {
    //广告id
    private int adverId;
    //广告标题
    private String bannerTitle;
    //广告图片
    private String bannerImage;
    //广告文章
    private int articleId;

    public int getAdverId() {
        return adverId;
    }

    public void setAdverId(int adverId) {
        this.adverId = adverId;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }
}
