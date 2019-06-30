package com.nuc.calvin.headline.utils;

public class StaticClass {

    private static String ip = "10.0.116.108";

    //登录url
    public static String loginUrl = "http://" + ip + ":8080/headline/user/loginUser";

    //注册url
    public static String registerUrl = "http://" + ip + ":8080/headline/user/userRegister";

    //文章url
    public static String articleUrl = "http://" + ip + ":8080/headline/article/getAllArticle";

    public static String shareUrl = "http://" + ip + ":8080/headline/article/postArticle";

}
