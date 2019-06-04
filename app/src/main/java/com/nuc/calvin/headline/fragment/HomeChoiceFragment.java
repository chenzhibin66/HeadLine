package com.nuc.calvin.headline.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.HomeChoiceAdapter;
import com.nuc.calvin.headline.model.Banner;

import butterknife.Bind;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;


public class HomeChoiceFragment extends BaseFragment {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview)
    FamiliarRecyclerView mRecyclerView;
    private HomeChoiceAdapter mAdapter;
    private ConvenientBanner<Banner> mBannerV;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBannerV = (ConvenientBanner<Banner>) View.inflate(getActivity(), R.layout.view_home_banner, null);
        mRecyclerView.addHeaderView(mBannerV);
        mAdapter = new HomeChoiceAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                }, 2000);
            }
        });

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_choice;
    }
}
