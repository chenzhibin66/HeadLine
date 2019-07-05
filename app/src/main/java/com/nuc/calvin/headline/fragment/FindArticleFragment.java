package com.nuc.calvin.headline.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void initView(View view) {
        Log.d(TAG, "getJsonArticle: " + articleJsList);
        recyclerView = view.findViewById(R.id.find_article_recy);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        findArticleAdapter = new FindArticleAdapter(getContext(), articleJsList);
        recyclerView.setAdapter(findArticleAdapter);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1056:
                        findArticleAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };
    }

    public void refredata(String search) {
        getQueryArticle(search);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_tab_article;
    }

    private void getQueryArticle(String keyWord) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.queryArticleByWordUrl + "?keyWord=" + keyWord).build();
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
                List<ArticleJs> temp = gson.fromJson(res, new TypeToken<List<ArticleJs>>() {
                }.getType());
                articleJsList.clear();
                articleJsList.addAll(temp);
                Log.d(TAG, "queryByWord: " + articleJsList);
                handler.sendEmptyMessage(1056);
            }
        });
    }


}
