package com.nuc.calvin.headline.fragment;


import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.baoyz.widget.PullRefreshLayout;
import com.nuc.calvin.headline.R;


public class HomeMessageFragment extends BaseFragment {


    private PullRefreshLayout layout;

    private ImageView iv_dingyue;
    private ImageView iv_comment;
    private ImageView iv_attention;
    private ImageView iv_like;

    @Override

    protected void initView(View view) {
        init(view);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_message;
    }



    private void init(View view) {
        iv_dingyue = view.findViewById(R.id.msg_iv_dingyue);
        iv_comment = view.findViewById(R.id.msg_iv_comment);
        iv_attention = view.findViewById(R.id.msg_iv_guanzhu);
        iv_like = view.findViewById(R.id.msg_iv_good);
        layout = view.findViewById(R.id.msg_refreshLayout);
    }


}
