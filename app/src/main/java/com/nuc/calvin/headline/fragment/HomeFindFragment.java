package com.nuc.calvin.headline.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.HotArticleAdapter;
import com.nuc.calvin.headline.adapter.HotUserAdapter;
import com.nuc.calvin.headline.bean.Article;
import com.nuc.calvin.headline.bean.User;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.ArticleJs;
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
    private LinearLayout search_layout;
    private List<UserCustom> userList=new ArrayList<>();
    private List<ArticleJs> articleList = new ArrayList<>();

    @Override
    protected void initView(View view) {
        Fresco.initialize(getContext());
        init(view);
        getHotArticle();
        getHotUser();
        article_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        user_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        article_recyclerView.setAdapter(hotArticleAdapter);
        user_recyclerView.setAdapter(hotUserAdapter);

        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        search_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        pullRefreshLayout = view.findViewById(R.id.search_refresh);
        scrollView = view.findViewById(R.id.search_scrollView);
        search_layout = view.findViewById(R.id.search_layout);
    }

   /* private void initArticle() {
        articleList = new ArrayList<>();
        Article article = new Article();
        for (int i = 0; i < 2; i++) {
            articleList.add(article);
        }
        hotArticleAdapter.addHotArticle(articleList);
    }*/

   /* private void initUser() {
        userList = new ArrayList<>();
        User user = new User();
        for (int i = 0; i < 2; i++) {
            userList.add(user);
        }
        hotUserAdapter.addHotUser(userList);
    }*/

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
                Log.d(TAG, "hotArticle: "+articleList);
                hotArticleAdapter.addHotArticle(articleList);
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
                userList = gson.fromJson(res, new TypeToken<List<UserCustom>>() {
                }.getType());
                Log.d(TAG, "hotUser: "+userList);
               hotUserAdapter.addHotUser(userList);
            }
        });
    }
}
