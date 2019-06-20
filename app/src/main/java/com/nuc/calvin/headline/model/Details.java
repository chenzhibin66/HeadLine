package com.nuc.calvin.headline.model;

public class Details {

    private int detailImgId;
    private String details_title;


    public Details() {
    }

    public Details(int detailImgId, String details_title) {
        this.detailImgId = detailImgId;
        this.details_title = details_title;
    }

    public int getDetailImgId() {
        return detailImgId;
    }

    public void setDetailImgId(int detailImgId) {
        this.detailImgId = detailImgId;
    }

    public String getDetails_title() {
        return details_title;
    }

    public void setDetails_title(String details_title) {
        this.details_title = details_title;
    }
}
