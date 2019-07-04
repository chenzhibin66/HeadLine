package com.nuc.calvin.headline.json;

import com.nuc.calvin.headline.bean.Relation;

public class UserJs {
    private Integer userId;
    private String username;
    private String email;
    private String password;
    private String headImg;
    private String signature;
    private String sex;
    private Relation relation;
    private int articleCount;
    private int followCount;
    private int fansCount;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    @Override
    public String toString() {
        return "UserJs{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", headImg='" + headImg + '\'' +
                ", signature='" + signature + '\'' +
                ", sex='" + sex + '\'' +
                ", relation=" + relation +
                ", articleCount=" + articleCount +
                ", followCount=" + followCount +
                ", fansCount=" + fansCount +
                '}';
    }
}
