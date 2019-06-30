package com.nuc.calvin.headline.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.LoginJs;

import java.io.IOException;
import java.io.StreamCorruptedException;

public class ShareUtils {

    // 用户名key
    public final static String KEY_NAME = "KEY_NAME";

    public final static String KEY_LEVEL = "KEY_LEVEL";

    private static ShareUtils shareUtils;

    private static UserCustom login_user = null;

    private SharedPreferences msp;

    @SuppressLint("WrongConstant")
    public ShareUtils(Context context) {
        msp = context.getSharedPreferences("ShareUtils", Context.MODE_PRIVATE | Context.MODE_APPEND);
    }

    // 初始化，一般在应用启动之后就要初始化
    public static synchronized void initSharedPreference(Context context) {
        if (shareUtils == null) {
            shareUtils = new ShareUtils(context);
        }
    }


    /**
     * 获取唯一的instance
     *
     * @return
     */
    public static synchronized ShareUtils getInstance() {
        return shareUtils;
    }

    public SharedPreferences getSharedPref() {
        return msp;
    }

    public synchronized void putUser(UserCustom user) {

        SharedPreferences.Editor editor = msp.edit();

        String str = "";
        try {
            str = SerializableUtil.obj2Str(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(KEY_NAME, str);
        editor.commit();

        login_user = user;
    }


    public synchronized UserCustom getUser() {

        if (login_user == null) {
            login_user = new UserCustom();


            //获取序列化的数据
            String str = msp.getString(ShareUtils.KEY_NAME, "");

            try {
                Object obj = SerializableUtil.str2Obj(str);
                if (obj != null) {
                    login_user = (UserCustom) obj;
                }

            } catch (StreamCorruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return login_user;
    }


    public synchronized void DeleteUser()
    {
        SharedPreferences.Editor editor = msp.edit();
        editor.putString(KEY_NAME,"");

        editor.commit();
        login_user = null;
    }
}
