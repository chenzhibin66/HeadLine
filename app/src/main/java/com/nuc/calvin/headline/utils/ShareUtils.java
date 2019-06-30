package com.nuc.calvin.headline.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ShareUtils {

    public static final String NAME = "config";

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key , String defValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,defValue);
    }

    public static void putInt(Context context , String key , int value){
        SharedPreferences sp= context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue){
        SharedPreferences sp = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static void putBoolean(Context mContext , String key , boolean value){
        SharedPreferences sp= mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context mContext, String key, boolean defValue){
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }


    //删除单个
    public static void deleShare(Context mContext, String key){
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    //删除全部
    public static void deleAll(Context mContext){
        SharedPreferences sp = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
