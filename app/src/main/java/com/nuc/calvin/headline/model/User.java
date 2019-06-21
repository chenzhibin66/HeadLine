package com.nuc.calvin.headline.model;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String email;
    private String userHead;
    private String signature;
    private boolean following;
    private int fans_count;
    private int subscribe_count;
    private int share_count;
    private int share_id;
    private int subscribe_id;

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

    public int getFans_count() {
        return fans_count;
    }

    public void setFans_count(int fans_count) {
        this.fans_count = fans_count;
    }

    public int getSubscribe_count() {
        return subscribe_count;
    }

    public void setSubscribe_count(int subscribe_count) {
        this.subscribe_count = subscribe_count;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public int getShare_id() {
        return share_id;
    }

    public void setShare_id(int share_id) {
        this.share_id = share_id;
    }

    public int getSubscribe_id() {
        return subscribe_id;
    }

    public void setSubscribe_id(int subscribe_id) {
        this.subscribe_id = subscribe_id;
    }
}
