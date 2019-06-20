package com.nuc.calvin.headline.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.baoyz.widget.PullRefreshLayout;
import com.bumptech.glide.Glide;
import com.nuc.calvin.headline.R;


import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class HomeUserFragment extends BaseFragment {
    private PullRefreshLayout userRefreshLayout;
    private ImageView user_head;

    @Override
    protected void initView(View view) {
        user_head = view.findViewById(R.id.user_img);
        userRefreshLayout = view.findViewById(R.id.user_refresh);
        setData();
        userRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        userRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_user;
    }

    private void setData() {
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new CropCircleTransformation(getContext())).into(user_head);
    }
}
