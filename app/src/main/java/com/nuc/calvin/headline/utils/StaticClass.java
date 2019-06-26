package com.nuc.calvin.headline.utils;

public class StaticClass {

    private static String ip = "10.0.116.108";

    //登录url
    public static String loginUrl = "http://" + ip + ":8080/headline/user/loginUser";

    //注册url
    public static String registerUrl = "http://" + ip + ":8080/headline/user/userRegister";
}
