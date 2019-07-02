package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.MyArticleAdapter;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.ArticleJs;
import com.nuc.calvin.headline.utils.ShareUtils;
import com.nuc.calvin.headline.utils.StaticClass;
import com.yanzhenjie.recyclerview.OnItemClickListener;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyArticleActivity extends BaseActivity {
    private static final String TAG = "MyArticleActivity";
    private List<ArticleJs> articleJsList = new ArrayList<>();
    private ImageView my_left;
    private SwipeRecyclerView delete_recy;
    private MyArticleAdapter adapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        adapter = new MyArticleAdapter(getApplicationContext());
        getMyArticle();
        delete_recy = findViewById(R.id.my_article_recyclerView);
        delete_recy.setSwipeMenuCreator(swipeMenuCreator);
        delete_recy.setOnItemMenuClickListener(mMenuItemClickListener);
        delete_recy.setLayoutManager(new LinearLayoutManager(this));
        delete_recy.setAdapter(adapter);

        my_left = findViewById(R.id.my_article_left);
        my_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyArticleActivity.this, MainActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_article;
    }


    private void getMyArticle() {
        UserCustom userCustom = ShareUtils.getInstance().getUser();
        Integer userId = userCustom.getUserId();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.myArticleUrl + "?userId=" + userId).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyArticleActivity.this, "网络请求失败！", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                articleJsList = gson.fromJson(res, new TypeToken<List<ArticleJs>>() {
                }.getType());
                Log.d(TAG, "onResponse: " + articleJsList);
                adapter.setList(articleJsList);
            }
        });
    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(MyArticleActivity.this).setBackground(R.drawable.selector_red)
                    .setImage(R.drawable.ic_action_delete)
                    .setText("删除")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            rightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(MyArticleActivity.this, "list第" + position + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            } else if (direction == SwipeRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(MyArticleActivity.this, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };


}
