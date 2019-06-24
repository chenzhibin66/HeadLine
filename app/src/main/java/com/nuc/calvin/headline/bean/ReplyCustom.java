package com.nuc.calvin.headline.bean;

public class ReplyCustom extends Reply {
    /**
     * from 昵称
     */
    private String fromName;
    /**
     * to 昵称
     */
    private String toName;
    /**
     * 头像
     */
    private String fromImg;
    private String toImg;
    /**
     * 源文章
     */
    private Article article;

    /**
     * 回复时间 sql扩展
     */
    private String rtime;
}
