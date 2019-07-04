package com.nuc.calvin.headline.bean;

import java.io.Serializable;
import java.util.Date;

public class UserCustom extends User implements Serializable {
    /**
     * 用户关系 0——未关注 1——已关注 2——相互关注
     */
    private Relation relation;

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
        return "UserCustom{" +
                "relation=" + relation +
                ", articleCount=" + articleCount +
                ", followCount=" + followCount +
                ", fansCount=" + fansCount +
                '}';
    }
}
