package com.nuc.calvin.headline.fragment;


import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.HomeMessageAdapter;
import com.nuc.calvin.headline.model.Message;

import java.util.ArrayList;
import java.util.List;


public class HomeMessageFragment extends BaseFragment {

    private ListView listView;
    private HomeMessageAdapter homeMessageAdapter;
    private List<Message> msgList;
    private ImageView msg_head;
    private TextView msg_name;
    private SwipeRefreshLayout layout;

    @Override

    protected void initView(View view) {
        init(view);
        initMessage();
        listView.setAdapter(homeMessageAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_home_message;
    }

    private void initMessage() {
        msgList = new ArrayList<>();
        Message attentionMsg = new Message(R.drawable.ic_msg_atten, "订阅消息");
        Message commentMsg = new Message(R.drawable.ic_msg_comment, "评论消息");
        Message guanzhuMsg = new Message(R.drawable.ic_msg_guanzhu, "关注消息");
        Message goodMsg = new Message(R.drawable.ic_msg_good, "赞");
        msgList.add(attentionMsg);
        msgList.add(commentMsg);
        msgList.add(guanzhuMsg);
        msgList.add(goodMsg);
    }

    private void init(View view) {
        msgList = new ArrayList<>();
        listView = view.findViewById(R.id.list_view);
        msg_head = view.findViewById(R.id.iv_message_head);
        msg_name = view.findViewById(R.id.tv_message_content);
        homeMessageAdapter = new HomeMessageAdapter(getContext(), R.layout.listview_message_item, msgList);
        layout = view.findViewById(R.id.msg_refreshLayout);
    }
}
