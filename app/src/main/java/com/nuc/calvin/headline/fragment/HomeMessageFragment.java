package com.nuc.calvin.headline.fragment;


import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.HomeMessageAdapter;
import com.nuc.calvin.headline.model.Message;

import java.util.ArrayList;
import java.util.List;


public class HomeMessageFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private HomeMessageAdapter homeMessageAdapter;
    private List<Message> msgList;
    private ImageView msg_head;
    private TextView msg_name;
    private TextView msg_count;
    private PullRefreshLayout layout;

    @Override

    protected void initView(View view) {
        init(view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(homeMessageAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initMessage();
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
        return R.layout.fragment_home_message;
    }

    private void initMessage() {
        Message attentionMsg = new Message(R.drawable.ic_msg_atten, "订阅消息", "1");
        Message commentMsg = new Message(R.drawable.ic_msg_comment, "评论消息", "1");
        Message guanzhuMsg = new Message(R.drawable.ic_msg_guanzhu, "关注消息", "1");
        Message goodMsg = new Message(R.drawable.ic_msg_good, "赞", "1");
        msgList.add(attentionMsg);
        msgList.add(commentMsg);
        msgList.add(guanzhuMsg);
        msgList.add(goodMsg);
        homeMessageAdapter.addMessage(msgList);
    }

    private void init(View view) {
        msgList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.list_view);
        msg_head = view.findViewById(R.id.iv_message_head);
        msg_name = view.findViewById(R.id.tv_message_content);
        msg_count = view.findViewById(R.id.msg_point);
        homeMessageAdapter = new HomeMessageAdapter(getContext());
        layout = view.findViewById(R.id.msg_refreshLayout);
    }

}
