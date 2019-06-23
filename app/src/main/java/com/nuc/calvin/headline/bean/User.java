package com.nuc.calvin.headline.bean;

import java.util.Date;

public class User {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户昵称
     */
    private String username;
    /**
     * 注册邮箱
     */
    private String email;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户头像
     */
    private String headImg;
    /**
     * 用户个性签名
     */
    private String signature;
    /**
     * 用户性别   0--男   1--女
     */
    private Integer sex;
    /**
     * 文章数
     */
    private int articleCount;
    /**
     * 关注数
     */
    private int followCount;
    /**
     * 粉丝数
     */
    private int fansCount;
    /**
     * 注册时间
     */
    private Date singUpTime;

    /**
     * 用户关系 0——未关注 1——已关注 2——相互关注
     */
    private Relation relation;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public int getFollowCount() {
        return followCount;
    }

    public void setFollowCount(int followCount) {
        this.followCount = followCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public Date getSingUpTime() {
        return singUpTime;
    }

    public void setSingUpTime(Date singUpTime) {
        this.singUpTime = singUpTime;
    }
}
