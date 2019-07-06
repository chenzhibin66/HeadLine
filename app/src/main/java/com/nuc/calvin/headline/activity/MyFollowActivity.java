package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.utils.L;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.MyFollowAdapter;
import com.nuc.calvin.headline.json.CollectJs;
import com.nuc.calvin.headline.json.UserJs;
import com.nuc.calvin.headline.utils.ShareUtils;
import com.nuc.calvin.headline.utils.StaticClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyFollowActivity extends BaseActivity {
    private static final String TAG = "MyFollowActivity ";

    private RecyclerView follow_recy;
    private ImageView followLeft;
    private List<UserJs> userJsList = new ArrayList<>();
    private MyFollowAdapter myFollowAdapter;
    private Handler handler;

    @Override
    protected void initView(Bundle savedInstanceState) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        myFollowAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

        };
        getFollowList();
        follow_recy = findViewById(R.id.my_follow_recyclerView);
        followLeft = findViewById(R.id.my_follow_left);
        follow_recy.setLayoutManager(new LinearLayoutManager(this));
        myFollowAdapter = new MyFollowAdapter(getApplicationContext(), userJsList);
        follow_recy.setAdapter(myFollowAdapter);
        followLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyFollowActivity.this, MainActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
            }
        });



    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_follow;
    }

    private void getFollowList() {
        OkHttpClient okHttpClient = new OkHttpClient();
        //构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.followListUrl + "?userId=" + ShareUtils.getInstance().getUser().getUserId()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "网络请求失败！", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                List<UserJs> temp = gson.fromJson(res, new TypeToken<List<UserJs>>() {
                }.getType());
                Log.d(TAG, "userjs: "+temp);
                userJsList.clear();
                userJsList.addAll(temp);
                handler.sendEmptyMessage(1);
            }
        });
    }
}
