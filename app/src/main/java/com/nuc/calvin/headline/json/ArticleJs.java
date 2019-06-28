package com.nuc.calvin.headline.json;

import com.nuc.calvin.headline.bean.Article;
import com.nuc.calvin.headline.bean.ArticleCustom;
import com.nuc.calvin.headline.bean.User;

import java.util.Date;
import java.util.List;

public class ArticleJs {
        private int articleId;
        private int userId;
        private String articleTitle;
        private String articleUrl;
        private Date postTime;
        private User user;
        private String bannerArtcile;
        private int isAdvertorial;
        private int likes;
        private int collect;
        private int commentCount;
        private int likeCount;
        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }
        public int getArticleId() {
            return articleId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
        public int getUserId() {
            return userId;
        }

        public void setArticleTitle(String articleTitle) {
            this.articleTitle = articleTitle;
        }
        public String getArticleTitle() {
            return articleTitle;
        }

        public void setArticleUrl(String articleUrl) {
            this.articleUrl = articleUrl;
        }
        public String getArticleUrl() {
            return articleUrl;
        }

        public void setPostTime(Date postTime) {
            this.postTime = postTime;
        }
        public Date getPostTime() {
            return postTime;
        }

        public void setUser(User user) {
            this.user = user;
        }
        public User getUser() {
            return user;
        }

        public void setBannerArtcile(String bannerArtcile) {
            this.bannerArtcile = bannerArtcile;
        }
        public String getBannerArtcile() {
            return bannerArtcile;
        }

        public void setIsAdvertorial(int isAdvertorial) {
            this.isAdvertorial = isAdvertorial;
        }
        public int getIsAdvertorial() {
            return isAdvertorial;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }
        public int getLikes() {
            return likes;
        }

        public void setCollect(int collect) {
            this.collect = collect;
        }
        public int getCollect() {
            return collect;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }
        public int getCommentCount() {
            return commentCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }
        public int getLikeCount() {
            return likeCount;
        }

    @Override
    public String toString() {
        return "ArticleJs{" +
                "articleId=" + articleId +
                ", userId=" + userId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", postTime=" + postTime +
                ", user=" + user +
                ", bannerArtcile='" + bannerArtcile + '\'' +
                ", isAdvertorial=" + isAdvertorial +
                ", likes=" + likes +
                ", collect=" + collect +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                '}';
    }
}
