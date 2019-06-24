package com.nuc.calvin.headline.bean;

import java.util.List;

public class CommentCustom extends Comment {

    private User user;

    /**
     * 被评论文章作者昵称
     */
    private String username;
    /**
     * json评论时间
     */
    private String time;
    /**
     * 评论回复数
     */
    private int countReply;

    /**
     * 回复列表
     */
    private List<Reply> replyList;



}
