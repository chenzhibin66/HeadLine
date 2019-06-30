package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.json.OkJs;
import com.nuc.calvin.headline.utils.StaticClass;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShareActivity extends BaseActivity {

    private static final String TAG = "ShareActivity";
    private ImageView iv_left;
    private TextView tv_submit;
    private EditText shareTitle;
    private EditText shareUrl;

    @Override
    protected void initView(Bundle savedInstanceState) {
        shareTitle = findViewById(R.id.share_title);
        shareUrl = findViewById(R.id.share_url);
        iv_left = findViewById(R.id.iv_actionbar_left);
        tv_submit = findViewById(R.id.tv_actionbar_submit);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer userId = getIntent().getIntExtra("userId", 0);
                String url = shareUrl.getText().toString();
                String title = shareTitle.getText().toString();
                Log.d(TAG, "onClickResult: " + userId + url + title);

                shareArticle(userId, title, url);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_share;
    }


    private void shareArticle(Integer userId, String title, String url) {
        //拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.shareUrl + "?userId=" + userId
                + "&title=" + title + "&url=" + url).build();
        Log.d(TAG, "shareArticle: "+StaticClass.shareUrl + "?userId" + userId
                + "&title" + title + "&url" + url);
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ShareActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                OkJs okJs = gson.fromJson(res, new TypeToken<OkJs>() {
                }.getType());
                Log.d(TAG, "okjsResult: " +okJs);
                if (okJs.getCode() == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ShareActivity.this, "分享成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(ShareActivity.this, MainActivity.class);
                            intent.putExtra("id", 0);
                            startActivity(intent);
                        }
                    });
                } else {
                    Toast.makeText(ShareActivity.this, "分享失败！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
