package com.nuc.calvin.headline.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.HomeMessageAdapter;
import com.nuc.calvin.headline.model.Message;

import java.util.ArrayList;
import java.util.List;


public class HomeMessageFragment extends BaseFragment implements AdapterView.OnClickListener {

    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private HomeMessageAdapter homeMessageAdapter;
    private List<Message> msgList;

    @Override
    protected void initView(View view) {
        msgList = new ArrayList<>();
        listView = view.findViewById(R.id.list_view);
        swipeRefreshLayout = view.findViewById(R.id.msg_refreshLayout);
        homeMessageAdapter = new HomeMessageAdapter(getContext(), R.layout.listview_message_item, msgList);
        listView.setAdapter(homeMessageAdapter);
        listView.setOnClickListener(new a);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_message;
    }
}
