package com.nuc.calvin.headline.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.UserDetailsAdapter;
import com.nuc.calvin.headline.bean.ui.Details;


import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static android.support.constraint.Constraints.TAG;


public class HomeUserFragment extends BaseFragment {
    private PullRefreshLayout userRefreshLayout;
    private RecyclerView user_recyclerview;
    private List<Details> detailsList;
    private UserDetailsAdapter userDetailsAdapter;

    private SimpleDraweeView user_head;
    private TextView user_name;
    private TextView user_signature;
    private TextView article_count;
    private TextView follow_count;
    private TextView fans_count;

    @Override
    protected void initView(View view) {
        Fresco.initialize(getContext());
        user_head = view.findViewById(R.id.user_img);
        user_name = view.findViewById(R.id.please_input_username);
        user_signature = view.findViewById(R.id.signature);
        article_count = view.findViewById(R.id.share_count);
        follow_count = view.findViewById(R.id.user_follow_count);
        fans_count = view.findViewById(R.id.user_fan_count);
        userRefreshLayout = view.findViewById(R.id.user_refresh);
        user_recyclerview = view.findViewById(R.id.user_details_recycler);
        user_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        userDetailsAdapter = new UserDetailsAdapter(getContext());
        getUserMsg();
        initDetails();
        user_recyclerview.setAdapter(userDetailsAdapter);
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

    public void getUserMsg() {
        Bundle bundle = this.getArguments();
        String userHead = bundle.getString("userHead");
        String userName = bundle.getString("userName");
        String signature = bundle.getString("signature");
        Log.d(TAG, "getUserMsgSignature: "+signature);
        /*String user_sex = bundle.getString("sex");
        String user_followCount = bundle.getString("followCount");
        Log.d(TAG, "getUserMsgfollowcount: "+signature);
        String user_fansCount = bundle.getString("fansCount");
        Log.d(TAG, "getUserMsgfanscount: "+signature);
        String articleCount = bundle.getString("articleCount");
        Log.d(TAG, "getUserMsgarticle: " +articleCount);*/
        user_head.setImageURI(userHead);
        user_name.setText(userName);
      /*  user_signature.setText(signature);*/
       /* follow_count.setText(user_followCount);
        fans_count.setText(user_fansCount);
        article_count.setText(articleCount);*/
    }
}
