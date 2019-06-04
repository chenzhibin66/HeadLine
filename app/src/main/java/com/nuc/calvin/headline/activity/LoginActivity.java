package com.nuc.calvin.headline.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nuc.calvin.headline.R;
import com.sirvar.robin.RobinActivity;
import com.sirvar.robin.Theme;

public class LoginActivity extends RobinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLoginTitle("IT头条");
        setSignupTitle("欢迎来到IT头条");
        setForgotPasswordTitle("忘记密码");
        setImage(getResources().getDrawable(R.drawable.it));
        setFont(Typeface.createFromAsset(getAssets(), "Montserrat-Medium.ttf"));
        setTheme(Theme.LIGHT);
    }

    @Override
    protected void onLogin(String email, String password) {

    }

    @Override
    protected void onSignup(String name, String email, String password) {

    }

    @Override
    protected void onForgotPassword(String email) {

    }

    @Override
    protected void onGoogleLogin() {

    }

    @Override
    protected void onFacebookLogin() {

    }
}
