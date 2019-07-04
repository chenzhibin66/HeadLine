package com.nuc.calvin.headline.utils;

import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.ArticleJs;

import java.util.ArrayList;
import java.util.List;

public class StaticClass {

    /*private static String ip = "10.0.116.108";*/
    private static String ip = "10.0.117.73";

    //登录url
    public static String loginUrl = "http://" + ip + ":8080/headline/user/loginUser";

    //注册url
    public static String registerUrl = "http://" + ip + ":8080/headline/user/userRegister";

    //文章url
    public static String articleUrl = "http://" + ip + ":8080/headline/article/getAllArticle";

    //发布文章
    public static String shareUrl = "http://" + ip + ":8080/headline/article/postArticle";

    //点赞
    public static String likeUrl = "http://" + ip + ":8080/headline/likes/like";


    //发布评论
    public static String commentUrl = "http://" + ip + ":8080/headline/comment/commentArticle";

    //收藏
    public static String collectUrl = "http://" + ip + ":8080/headline/collection/collect";

    //查询我的文章
    public static String myArticleUrl = "http://" + ip + ":8080/headline/article/getMyArticle";
    //删除文章
    public static String deleteArticleUrl = "http://" + ip + ":8080/headline/article/deleteArticle";

    public static String hotArticleUrl = "http://" + ip + ":8080/headline/article/queryHotArticle";

    public static String hotUserUrl = "http://" + ip + ":8080/headline/user/queryUserExSelf";

    public static String queryArticleByWordUrl = "http://" + ip + ":8080/headline/article/queryArticleByWord";

    public static String queryUserByWordUrl = "http://" + ip + ":8080/headline/user/queryUserByWord";

    public static String queryComment = "http://" + ip + ":8080/headline/comment/commentList";


    public static String unFollowUrl = "http://" + ip + ":8080/headline/relation/unFollow";
    public static String followUrl = "http://" + ip + ":8080/headline/relation/follow";
    //取消收藏
    public static String unCollectUrl = "http://" + ip + ":8080/headline/collection/unCollect";

    //收藏列表
    public static String collectListUrl = "http://" + ip + ":8080/headline/collection/queryAllCollection";

    public static String bannerUrl = "http://" + ip + ":8080/headline/banner/queryAllBanner";




    public static List<ArticleJs> articleJsList = new ArrayList<>();
    public static List<UserCustom> userList = new ArrayList<>();


}
