package com.nuc.calvin.headline.fragment;


import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baoyz.widget.PullRefreshLayout;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.MsgCollectActivity;
import com.nuc.calvin.headline.activity.MsgCommentActivity;
import com.nuc.calvin.headline.activity.MsgLikesActivity;


public class HomeMessageFragment extends BaseFragment {


    private PullRefreshLayout layout;


    private LinearLayout commentLayout;
    private LinearLayout collectLayout;
    private LinearLayout likeLayout;

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

        commentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MsgCommentActivity.class);
                startActivity(intent);
            }
        });


        collectLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MsgCollectActivity.class);
                startActivity(intent);
            }
        });


        likeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MsgLikesActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_message;
    }


    private void init(View view) {
        commentLayout = view.findViewById(R.id.msg_comment_layout);
        collectLayout = view.findViewById(R.id.msg_collect_layout);
        likeLayout = view.findViewById(R.id.msg_like_layout);
        layout = view.findViewById(R.id.msg_refreshLayout);
    }


}
