package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.json.LoginJs;
import com.nuc.calvin.headline.json.RegisterJs;
import com.nuc.calvin.headline.utils.ShareUtils;
import com.nuc.calvin.headline.utils.StaticClass;
import com.sirvar.robin.RobinActivity;
import com.sirvar.robin.Theme;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends RobinActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareUtils.initSharedPreference(getApplicationContext());
        setLoginTitle("IT头条");
        setSignupTitle("欢迎来到IT头条");
        setForgotPasswordTitle("忘记密码");
        setImage(getResources().getDrawable(R.drawable.it));
        setFont(Typeface.createFromAsset(getAssets(), "Montserrat-Medium.ttf"));
        setTheme(Theme.LIGHT);
    }

    @Override
    protected void onLogin(String email, String password) {
        //拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.loginUrl + "?email=" + email + "&password=" + password).build();
        Log.d(TAG, "onLogin: " + StaticClass.loginUrl + "?email=" + email + "&password=" + password);
        //将request封装为Call
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Log.d(TAG, "hahahahres=: " + res);
                Gson gson = new Gson();
                List<LoginJs> loginJs = gson.fromJson(res, new TypeToken<List<LoginJs>>() {
                }.getType());
                LoginJs loginJss = loginJs.get(0);
                Log.d(TAG, "loginJss: " + loginJss);
                if (loginJss.getMsg().equals("登录成功!")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    ShareUtils.getInstance().putUser(loginJss.getUser());
                    startActivity(intent);
                    finish();
                } else if (loginJss.getMsg().equals("登录失败！")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "输入框不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //注册
    @Override
    protected void onSignup(String username, String email, String password) {

        Log.d(TAG, "username: " + username);
        Log.d(TAG, "email: " + email);
        Date singUpTime = new Date();
        Log.d(TAG, "onSignup: " + StaticClass.registerUrl + "?username=" + username + "&email=" + email + "&password=" + password + "&singUpTime=" + singUpTime);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        Request request = builder.get().url(StaticClass.registerUrl + "?username=" + username + "&email=" + email + "&password=" + password + "&singUpTime=" + singUpTime).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Log.d(TAG, "body: " + response.body());
                Log.d(TAG, "hahahaha=" + res);

                Gson gson = new Gson();
                RegisterJs registerJs = gson.fromJson(res, new TypeToken<RegisterJs>() {
                }.getType());
                if (registerJs.getCode() == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (registerJs.getCode() == 2) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "该账号已经存在，请重新注册！", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

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
