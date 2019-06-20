package com.nuc.calvin.headline.fragment;

import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.baoyz.widget.PullRefreshLayout;
import com.bumptech.glide.Glide;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.UserDetailsAdapter;
import com.nuc.calvin.headline.model.Details;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class HomeUserFragment extends BaseFragment {
    private PullRefreshLayout userRefreshLayout;
    private ImageView user_head;
    private RecyclerView user_recyclerview;
    private List<Details> detailsList;
    private UserDetailsAdapter userDetailsAdapter;

    @Override
    protected void initView(View view) {
        user_head = view.findViewById(R.id.user_img);
        userRefreshLayout = view.findViewById(R.id.user_refresh);
        user_recyclerview = view.findViewById(R.id.user_details_recycler);
        user_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        userDetailsAdapter = new UserDetailsAdapter(getContext());
        initDetails();
        user_recyclerview.setAdapter(userDetailsAdapter);
        setData();
        user_recyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
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

    private void initDetails() {
        detailsList = new ArrayList<>();
        Details user_signature = new Details(R.drawable.iv_imag, "我的签名");
        Details user_collection = new Details(R.drawable.user_collect, "我的收藏");
        Details user_contact = new Details(R.drawable.contact, "联系我们");
        Details user_about = new Details(R.drawable.about_us, "关于我们");
        Details reset_pwd = new Details(R.drawable.reset_pwd, "修改密码");
        Details back_login = new Details(R.drawable.back_login, "退出登录");
        detailsList.add(user_signature);
        detailsList.add(user_collection);
        detailsList.add(user_contact);
        detailsList.add(user_about);
        detailsList.add(reset_pwd);
        detailsList.add(back_login);
        userDetailsAdapter.addDetails(detailsList);
    }
}
