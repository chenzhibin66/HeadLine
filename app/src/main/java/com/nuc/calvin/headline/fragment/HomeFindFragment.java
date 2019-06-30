package com.nuc.calvin.headline.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.baoyz.widget.PullRefreshLayout;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.HotArticleAdapter;
import com.nuc.calvin.headline.adapter.HotUserAdapter;
import com.nuc.calvin.headline.bean.Article;
import com.nuc.calvin.headline.bean.User;

import java.util.ArrayList;
import java.util.List;

public class HomeFindFragment extends BaseFragment {

    private RecyclerView article_recyclerView;
    private RecyclerView user_recyclerView;
    private HotArticleAdapter hotArticleAdapter;
    private HotUserAdapter hotUserAdapter;
    private PullRefreshLayout pullRefreshLayout;
    private ScrollView scrollView;
    private LinearLayout search_layout;
    private List<User> userList;
    private List<Article> articleList;

    @Override
    protected void initView(View view) {
        init(view);
        initArticle();
        initUser();
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

    private void initArticle() {
        articleList = new ArrayList<>();
        Article article = new Article();
        for (int i = 0; i < 2; i++) {
            articleList.add(article);
        }
        hotArticleAdapter.addHotArticle(articleList);
    }

    private void initUser() {
        userList = new ArrayList<>();
        User user = new User();
        for (int i = 0; i < 2; i++) {
            userList.add(user);
        }
        hotUserAdapter.addHotUser(userList);
    }

    public void getHotArticle(){

    }

    public void getHotUser(){

    }
}
