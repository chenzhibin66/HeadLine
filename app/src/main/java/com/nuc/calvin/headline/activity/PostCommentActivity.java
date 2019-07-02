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

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.utils.ShareUtils;
import com.nuc.calvin.headline.utils.StaticClass;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PostCommentActivity extends BaseActivity {
    private static final String TAG = "PostCommentActivity";
    private ImageView cancle_comment;
    private TextView post;
    private EditText comment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        cancle_comment = findViewById(R.id.iv_comment_left);
        post = findViewById(R.id.post_comment);
        comment = findViewById(R.id.comment_content);
        cancle_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostCommentActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserCustom user = ShareUtils.getInstance().getUser();
                Integer userId = user.getUserId();
                String content = comment.getText().toString().trim();
                Integer articleId = getIntent().getIntExtra("articleId", 0);
                Log.d(TAG, "getMessage: " + "userId=" + userId + "articleId=" + articleId + "content=" + content);
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder builder = new Request.Builder();
                Request request = builder.get().url(StaticClass.commentUrl + "?userId=" + userId + "&articleId=" + articleId + "&content=" + content).build();
                Log.d(TAG, "commentUrl: "+StaticClass.commentUrl + "?userId=" + userId + "&articleId=" + articleId + "&content=" + content);
                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PostCommentActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(PostCommentActivity.this, "评论成功！", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PostCommentActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
            }
        });


    }

    @Override
    protected int getContentView() {
        return R.layout.activity_post_comment;
    }

}
