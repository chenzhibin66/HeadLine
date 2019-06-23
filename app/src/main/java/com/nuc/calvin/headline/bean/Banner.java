package com.nuc.calvin.headline.bean;

public class Banner {
    //广告id
    private int bannerId;
    //广告标题
    private String bannerTitle;
    //广告图片
    private String bannerImage;

    //广告所属的文章   一对一关系
    private Article article;


}
