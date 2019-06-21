package com.nuc.calvin.headline.model;

public class Article {
    private int id;
    private boolean is_advertorial;
    private String title;
    private boolean liked;
    private int like_count;
    private int comment_Count;
    private boolean subscribe;
    private int subscribe_count;
    private Object author_head;
    private String author_name;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_advertorial() {
        return is_advertorial;
    }

    public void setIs_advertorial(boolean is_advertorial) {
        this.is_advertorial = is_advertorial;
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

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComment_Count() {
        return comment_Count;
    }

    public void setComment_Count(int comment_Count) {
        this.comment_Count = comment_Count;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }

    public int getSubscribe_count() {
        return subscribe_count;
    }

    public void setSubscribe_count(int subscribe_count) {
        this.subscribe_count = subscribe_count;
    }

    public Object getAuthor_head() {
        return author_head;
    }

    public void setAuthor_head(Object author_head) {
        this.author_head = author_head;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
