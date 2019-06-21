package com.nuc.calvin.headline.bean;

import java.util.Date;

public class User {
    //用户id
    private int userId;
    //用户名
    private String userName;
    //用户密码
    private String password;
    //用户邮件
    private String email;
    //用户头像url
    private String userHead;
    //用户个性签名
    private String signature;
    //是否被关注
    private boolean following;
    //粉丝数量
    private int fansCount;
    //粉丝的用户id
    private int followingId;
    //关注的用户的id
    private int subUserId;
    //关注数量
    private int subscribeCount;
    //分享的文章数量
    private int shareCount;
    //分享的文章id
    private int shareId;
    //注册时间
    private Date singUpTime;

    public int getSubUserId() {
        return subUserId;
    }

    public void setSubUserId(int subUserId) {
        this.subUserId = subUserId;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getFollowingId() {
        return followingId;
    }

    public void setFollowingId(int followingId) {
        this.followingId = followingId;
    }

    public int getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(int subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }
}
