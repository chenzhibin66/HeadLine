package com.nuc.calvin.headline.json;

import com.nuc.calvin.headline.bean.UserCustom;

import java.io.Serializable;

public class LoginJs implements Serializable {
    private int code;
    private String msg;
    private UserCustom user;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserCustom getUser() {
        return user;
    }

    public void setUser(UserCustom user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginJs{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", user=" + user +
                '}';
    }
}
