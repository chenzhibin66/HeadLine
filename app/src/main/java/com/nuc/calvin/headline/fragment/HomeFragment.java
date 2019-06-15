package com.nuc.calvin.headline.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.MainActivity;
import com.nuc.calvin.headline.adapter.HomeAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;


public class HomeFragment extends BaseFragment {


//    Toolbar mToolbar;
//    TabLayout mTabLayout;
//    ViewPager mViewPager;
    private HomeAdapter homeAdapter;

    public CharSequence[] mTitles = {"精选", "订阅", "发现"};
    List<BaseFragment> mFragmentList = new ArrayList<>();


    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        homeFragment.setArguments(args);
        return homeFragment;
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//        super.onCreateView(inflater,container,savedInstanceState);
//        mToolbar = view.findViewById(R.id.toolbar);
//        mTabLayout = view.findViewById(R.id.tab_layout);
//        mViewPager = view.findViewById(R.id.viewpager);
//        return view;
//    }


    @Override
    protected void initView() {

//        mToolbar = getView().findViewById(R.id.toolbar);
//        mTabLayout = getView().findViewById(R.id.tab_layout);
//        mViewPager = getView().findViewById(R.id.viewpager);

        if (getArguments() != null) {
        }
        mToolbar.setTitle(R.string.app_name);
        mToolbar.inflateMenu(R.menu.menu_home);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        break;
                    case R.id.action_search:
                        break;
                }
                return false;
            }
        });
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = new Uri.Builder().scheme(MainActivity.SCHEME_OPEN_MENU).build();
                mListener.onFragmentInteraction(uri);
            }
        });
        homeAdapter = new HomeAdapter(getChildFragmentManager());
        mViewPager.setAdapter(homeAdapter);
        mViewPager.setOffscreenPageLimit(mTitles.length);
        mTabLayout.setupWithViewPager(mViewPager);
        initTabLayout();

    }

    @Override
    protected int getContentView() {
        initView();
        return R.layout.fragment_home;
    }

    private void loadTabData(List<BaseFragment> list, List<CharSequence> titles) {
        homeAdapter.addDataList(list, titles);
    }

    public void initTabLayout() {
        mFragmentList.clear();
        mFragmentList.add(new HomeChoiceFragment());
        mFragmentList.add(new HomeSubscribeFragment());
        mFragmentList.add(new HomeFindFragment());
        loadTabData(mFragmentList, Arrays.asList(mTitles));
    }


}
