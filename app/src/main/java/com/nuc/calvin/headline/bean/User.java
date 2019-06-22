package com.nuc.calvin.headline.bean;

import java.util.Date;

public class User {
    //用户id
    private int userId;
    //用户名
    private String userName;
    //用户邮件
    private String email;
    //用户密码
    private String password;
    //用户头像url
    private String userHead;
    //用户个性签名
    private String signature;
    //是否被关注
    private boolean following;
    //关注的用户的id
    private int subUserId;
    //关注数量
    private int subscribeCount;
    //粉丝id
    private int fansId;
    //粉丝数量
    private int fansCount;
    //分享的文章数量
    private int shareCount;
    //注册时间
    private Date singUpTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public int getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(int subUserId) {
        this.subUserId = subUserId;
    }

    public int getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(int subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    public int getFansId() {
        return fansId;
    }

    public void setFansId(int fansId) {
        this.fansId = fansId;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public Date getSingUpTime() {
        return singUpTime;
    }

    public void setSingUpTime(Date singUpTime) {
        this.singUpTime = singUpTime;
    }
}
