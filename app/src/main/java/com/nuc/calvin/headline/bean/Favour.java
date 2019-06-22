package com.nuc.calvin.headline.bean;

import java.util.Date;

public class Favour {

    private int id;
    //点赞用户的id
    private int favourUserId;
    //点赞时间
    private Date favourTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavourUserId() {
        return favourUserId;
    }

    public void setFavourUserId(int favourUserId) {
        this.favourUserId = favourUserId;
    }

    public Date getFavourTime() {
        return favourTime;
    }

    public void setFavourTime(Date favourTime) {
        this.favourTime = favourTime;
    }
}
