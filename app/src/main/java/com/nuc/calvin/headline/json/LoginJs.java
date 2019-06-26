package com.nuc.calvin.headline.json;

public class LoginJs {


    private int code;
    private String msg;
    private UserBean user;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        private Integer uId;
        private String uEmail;
        private String uPassword;
        private String uName;
        private Integer uSex;
        private String uImg;

        public Integer getuId() {
            return uId;
        }

        public void setuId(Integer uId) {
            this.uId = uId;
        }

        public String getuEmail() {
            return uEmail;
        }

        public void setuEmail(String uEmail) {
            this.uEmail = uEmail;
        }

        public String getuPassword() {
            return uPassword;
        }

        public void setuPassword(String uPassword) {
            this.uPassword = uPassword;
        }

        public String getuName() {
            return uName;
        }

        public void setuName(String uName) {
            this.uName = uName;
        }

        public Integer getuSex() {
            return uSex;
        }

        public void setuSex(Integer uSex) {
            this.uSex = uSex;
        }

        public String getuImg() {
            return uImg;
        }

        public void setuImg(String uImg) {
            this.uImg = uImg;
        }
    }
}
