package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.CommentAdapter;
import com.nuc.calvin.headline.bean.CommentCustom;
import com.nuc.calvin.headline.json.CommentJs;
import com.nuc.calvin.headline.utils.StaticClass;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommentActivity extends BaseActivity {
    private static final String TAG = "CommentActivity";
    private ImageView back_left;
    private TextView article_title;
    private RecyclerView comment_recycler;
    private TextView postComment;
    private CommentAdapter commentAdapter;
    private List<CommentJs> list = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        Integer articleId = getIntent().getIntExtra("articleId", 0);
        Log.d(TAG, "articleIdComment: " + articleId);
        getCommentList(articleId);
        back_left = findViewById(R.id.comment_left);
        article_title = findViewById(R.id.comment_article_title);
        comment_recycler = findViewById(R.id.comment_recy);
        commentAdapter = new CommentAdapter(getApplicationContext());
        comment_recycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        comment_recycler.setAdapter(commentAdapter);
        postComment = findViewById(R.id.post_your_comment);


        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommentActivity.this, PostCommentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_commment;
    }


    private void getCommentList(Integer articleId) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.queryComment + "?articleId=" + articleId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CommentActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();

                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                });
                Gson gson = builder.create();
                list = gson.fromJson(res, new TypeToken<List<CommentJs>>() {
                }.getType());
                Log.d(TAG, "jsonResult "+list.toString());
                commentAdapter.addData(list);
            }
        });
    }
}
