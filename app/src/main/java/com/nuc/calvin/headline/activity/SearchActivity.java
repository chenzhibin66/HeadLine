package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.SearchAdapter;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.fragment.FindArticleFragment;
import com.nuc.calvin.headline.fragment.FindUserFragment;
import com.nuc.calvin.headline.json.ArticleJs;
import com.nuc.calvin.headline.utils.StaticClass;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";
    private TabLayout tabLayout;
    private ViewPager search_page;
    private List<String> list = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private EditText keyWord;
    private TextView cancle;
    private ImageView beginSearch;
    private List<ArticleJs> articleJsList = new ArrayList<>();
    private List<UserCustom> userCustomList = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        beginSearch = findViewById(R.id.begin_search);
        keyWord = findViewById(R.id.key_word);
        cancle = findViewById(R.id.tv_cancel);
        tabLayout = findViewById(R.id.tab);
        search_page = findViewById(R.id.pager);
        final FindArticleFragment findArticleFragment = new FindArticleFragment();
        final FindUserFragment findUserFragment = new FindUserFragment();
        fragments.add(findArticleFragment);
        fragments.add(findUserFragment);
        list.add("文章");
        list.add("用户");
        tabLayout.setupWithViewPager(search_page);
        search_page.setAdapter(new SearchAdapter(getSupportFragmentManager(), fragments, list));
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticClass.articleJsList = null;
                StaticClass.userList = null;
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("id", 2);
                startActivity(intent);
                finish();
            }
        });

        beginSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = keyWord.getText().toString();
                getQueryArticle(word);
                queryUser(word);
            }
        });

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }


    private void getQueryArticle(String keyWord) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.queryArticleByWordUrl + "?keyWord=" + keyWord).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SearchActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                articleJsList = gson.fromJson(res, new TypeToken<List<ArticleJs>>() {
                }.getType());
                Log.d(TAG, "queryByWord: " + articleJsList);
                StaticClass.articleJsList = articleJsList;
            }
        });
    }

    private void queryUser(String keyWord) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.queryUserByWordUrl + "?keyWord=" + keyWord).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SearchActivity.this, "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                userCustomList = gson.fromJson(res, new TypeToken<List<UserCustom>>() {
                }.getType());
                Log.d(TAG, "useraaaaaa: " + userCustomList.toString());
                StaticClass.userList = userCustomList;
            }
        });
    }
}