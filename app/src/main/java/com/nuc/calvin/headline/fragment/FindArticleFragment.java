package com.nuc.calvin.headline.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.MainActivity;
import com.nuc.calvin.headline.activity.SearchActivity;
import com.nuc.calvin.headline.adapter.FindArticleAdapter;
import com.nuc.calvin.headline.json.ArticleJs;
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

public class FindArticleFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private List<ArticleJs> articleJsList = new ArrayList<>();
    private FindArticleAdapter findArticleAdapter;


    @Override
    protected void initView(View view) {
        articleJsList = StaticClass.articleJsList;
        Log.d(TAG, "getJsonArticle: " + articleJsList);
        recyclerView = view.findViewById(R.id.find_article_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        findArticleAdapter = new FindArticleAdapter(getContext(), articleJsList);
        recyclerView.setAdapter(findArticleAdapter);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_tab_article;
    }




}
