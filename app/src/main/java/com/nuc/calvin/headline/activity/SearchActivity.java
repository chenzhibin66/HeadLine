package com.nuc.calvin.headline.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.SearchAdapter;
import com.nuc.calvin.headline.fragment.FindArticleFragment;
import com.nuc.calvin.headline.fragment.FindUserFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {
    private TabLayout tabLayout;
    private ViewPager search_page;
    private List<String> list = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        tabLayout = findViewById(R.id.tab);
        search_page = findViewById(R.id.pager);
        FindArticleFragment findArticleFragment = new FindArticleFragment();
        FindUserFragment findUserFragment = new FindUserFragment();
        fragments.add(findArticleFragment);
        fragments.add(findUserFragment);
        list.add("文章");
        list.add("用户");
        tabLayout.setupWithViewPager(search_page);
        search_page.setAdapter(new SearchAdapter(getSupportFragmentManager(),fragments,list));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }
}
