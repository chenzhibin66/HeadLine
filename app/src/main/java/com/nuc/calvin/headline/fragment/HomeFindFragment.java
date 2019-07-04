package com.nuc.calvin.headline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.SearchActivity;
import com.nuc.calvin.headline.adapter.HotArticleAdapter;
import com.nuc.calvin.headline.adapter.HotUserAdapter;
import com.nuc.calvin.headline.bean.Article;
import com.nuc.calvin.headline.bean.User;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.ArticleJs;
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

import static android.support.constraint.Constraints.TAG;

public class HomeFindFragment extends BaseFragment {

    private RecyclerView article_recyclerView;
    private RecyclerView user_recyclerView;
    private HotArticleAdapter hotArticleAdapter;
    private HotUserAdapter hotUserAdapter;
    private PullRefreshLayout pullRefreshLayout;
    private ScrollView scrollView;
    private LinearLayout searchLayout;
    private List<UserJs> userList = new ArrayList<>();
    private List<ArticleJs> articleList = new ArrayList<>();

    private TextView search_tv;
    private boolean isChanged = false;

    private Handler handler;


    @Override
    protected void initView(View view) {
        Fresco.initialize(getContext());
        init(view);
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getHotArticle();
                        getHotUser();
                        pullRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        pullRefreshLayout.measure(0, 0);
        pullRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                pullRefreshLayout.setRefreshing(true);
                getHotArticle();
                getHotUser();

            }
        });
        article_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        user_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        article_recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        user_recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        article_recyclerView.setAdapter(hotArticleAdapter);
        user_recyclerView.setAdapter(hotUserAdapter);
        search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        refreshHotUser();
                        break;
                    case 1:
                        hotUserAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        pullRefreshLayout.setRefreshing(false);
                        break;
                    default:
                        break;
                }
            }

        };

    }

    private void refreshHotUser() {
        UserCustom userCustom = ShareUtils.getInstance().getUser();
        Integer userId = userCustom.getUserId();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.hotUserUrl + "?userId=" + userId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                userList = gson.fromJson(res, new TypeToken<List<UserJs>>() {
                }.getType());
                Log.d(TAG, "hotUser: " + userList.get(0).toString());
                hotUserAdapter.addHotUser(userList);
                handler.sendEmptyMessage(1);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_find;
    }

    private void init(View view) {
        article_recyclerView = view.findViewById(R.id.hot_article_list);
        user_recyclerView = view.findViewById(R.id.hot_account_list);
        hotArticleAdapter = new HotArticleAdapter(getContext());
        hotUserAdapter = new HotUserAdapter(getContext());
        hotUserAdapter.setOnItemClickListener(findClickListener);
        pullRefreshLayout = view.findViewById(R.id.search_refresh);
        scrollView = view.findViewById(R.id.search_scrollView);
//        searchLayout=view.findViewById(R.id.search_layout);

        search_tv = view.findViewById(R.id.search_words);
    }

    public void getHotArticle() {
        UserCustom userCustom = ShareUtils.getInstance().getUser();
        Integer userId = userCustom.getUserId();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.hotArticleUrl + "?userId=" + userId).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                articleList = gson.fromJson(res, new TypeToken<List<ArticleJs>>() {
                }.getType());
                Log.d(TAG, "hotArticle: " + articleList);
                hotArticleAdapter.addHotArticle(articleList);
                handler.sendEmptyMessage(2);
            }
        });
    }

    public void getHotUser() {
        UserCustom userCustom = ShareUtils.getInstance().getUser();
        Integer userId = userCustom.getUserId();
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.hotUserUrl + "?userId=" + userId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                userList = gson.fromJson(res, new TypeToken<List<UserJs>>() {
                }.getType());
                Log.d(TAG, "hotUser: " + userList.get(0).toString());
                hotUserAdapter.addHotUser(userList);
                handler.sendEmptyMessage(2);
            }
        });
    }

    private HotUserAdapter.OnItemClickListener findClickListener = new HotUserAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(View v, HotUserAdapter.ViewName viewName, int position) {
            switch (v.getId()) {
                case R.id.iv_subscriber:
                    if (isChanged) {
                        ((ImageView) v).setImageResource(R.drawable.ic_iv_dingyue);
                        Integer myId = getMyId();
                        Integer userId = userList.get(position).getUserId();
                        int flag = 1;
                        unfollow(userId, myId, flag);

                    } else {
                        ((ImageView) v).setImageResource(R.drawable.ic_follow_press);
                        Integer myId = getMyId();
                        Integer userId = userList.get(position).getUserId();
                        int flag = 1;
                        follow(userId, myId, flag);
                        handler.sendEmptyMessage(0);
                    }
                    isChanged = !isChanged;
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onItemLongClick(View v) {

        }
    };

    private void unfollow(Integer userId, Integer myId, int flag) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(StaticClass.unFollowUrl + "?userId=" + userId + "&myId=" + myId + "&flag=" + flag).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求服务器失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private void follow(Integer userId, Integer myId, int flag) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(StaticClass.followUrl + "?userId=" + userId + "&myId=" + myId + "&flag=" + flag).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求服务器失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    private Integer getMyId() {
        UserCustom user = ShareUtils.getInstance().getUser();
        Integer myId = user.getUserId();
        return myId;
    }

}
