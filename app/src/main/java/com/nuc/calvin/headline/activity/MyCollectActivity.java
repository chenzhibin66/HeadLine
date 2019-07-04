package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.MyCollectAdapter;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.ArticleJs;
import com.nuc.calvin.headline.json.CollectJs;
import com.nuc.calvin.headline.utils.ShareUtils;
import com.nuc.calvin.headline.utils.StaticClass;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

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

public class MyCollectActivity extends BaseActivity {


    private SwipeRecyclerView collect_recy;
    private ImageView collect_left;
    private MyCollectAdapter myCollectAdapter;
    private List<CollectJs> collectJsList = new ArrayList<>();
    private Handler handler;

    @Override
    protected void initView(Bundle savedInstanceState) {
        getCollectList();
        collect_left = findViewById(R.id.my_collect_left);
        collect_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyCollectActivity.this, MainActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
                finish();
            }
        });
        myCollectAdapter = new MyCollectAdapter(getApplicationContext());
        collect_recy = findViewById(R.id.my_collect_recyclerView);
        collect_recy.setSwipeMenuCreator(swipeMenuCreator);
        collect_recy.setOnItemMenuClickListener(mMenuItemClickListener);
        collect_recy.setLayoutManager(new LinearLayoutManager(this));
        collect_recy.setAdapter(myCollectAdapter);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        refreshCollect();
                        break;
                    case 1:
                        myCollectAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
            }

        };
    }

    private void refreshCollect() {
        UserCustom userCustom = ShareUtils.getInstance().getUser();
        Integer userId = userCustom.getUserId();
        OkHttpClient okHttpClient = new OkHttpClient();
        //构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.collectListUrl + "?userId=" + userId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

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
                collectJsList = gson.fromJson(res, new TypeToken<List<CollectJs>>() {
                }.getType());
                myCollectAdapter.setList(collectJsList);
                handler.sendEmptyMessage(1);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_collect;
    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_70);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(MyCollectActivity.this).setBackground(R.drawable.selector_red)
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
                Integer articleId = collectJsList.get(position).getArticleId();
                unCollectArticle(articleId);

            } else if (direction == SwipeRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(MyCollectActivity.this, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };


    private void unCollectArticle(Integer articleId) {
        UserCustom userCustom = ShareUtils.getInstance().getUser();
        Integer userId = userCustom.getUserId();
        OkHttpClient okHttpClient = new OkHttpClient();
        //构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.unCollectUrl + "?articleId=" + articleId + "&userId=" + userId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyCollectActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyCollectActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                          handler.sendEmptyMessage(0);
                    }
                });
            }
        });
    }

    public void getCollectList() {
        UserCustom userCustom = ShareUtils.getInstance().getUser();
        Integer userId = userCustom.getUserId();
        OkHttpClient okHttpClient = new OkHttpClient();
        //构造Request
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.collectListUrl + "?userId=" + userId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

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
                collectJsList = gson.fromJson(res, new TypeToken<List<CollectJs>>() {
                }.getType());
                myCollectAdapter.setList(collectJsList);
            }
        });
    }
}
