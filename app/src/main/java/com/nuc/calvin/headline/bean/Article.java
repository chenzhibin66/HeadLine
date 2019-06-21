package com.nuc.calvin.headline.bean;

import java.util.Date;
import java.util.List;

public class Article {
    //文章id
    private int articleId;
    //是否广告
    private boolean isAdvertorial;
    //文章标题
    private String title;
    //是否被点赞
    private boolean liked;
    //点赞id
    private int likeId;
    //被点赞数量
    private int likeCount;
    //评论id
    private int idComment;
    //评论数量
    private int commentCount;
    //是否被收藏
    private boolean collect;
    //收藏的数量
    private int collectCount;
    //作者ID
    private int authorId;
    //作者头像url
    private String authorHead;
    //作者名字
    private String authorName;
    //文章url
    private String articleUrl;
    //收藏的用户id
    private int collectUserId;
    //发布时间
    private Date createTime;

    private List<Comment> commentList;
    private List<Favour> favourList;
    private Date articleTime;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public boolean isAdvertorial() {
        return isAdvertorial;
    }

    public void setAdvertorial(boolean advertorial) {
        isAdvertorial = advertorial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorHead() {
        return authorHead;
    }

    public void setAuthorHead(String authorHead) {
        this.authorHead = authorHead;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public int getCollectUserId() {
        return collectUserId;
    }

    public void setCollectUserId(int collectUserId) {
        this.collectUserId = collectUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Favour> getFavourList() {
        return favourList;
    }

    public void setFavourList(List<Favour> favourList) {
        this.favourList = favourList;
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }
}
