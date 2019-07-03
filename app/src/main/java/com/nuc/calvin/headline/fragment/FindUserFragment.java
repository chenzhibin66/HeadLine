package com.nuc.calvin.headline.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.FindUserAdapter;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.utils.StaticClass;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class FindUserFragment extends BaseFragment {

    private RecyclerView user_Recyclerview;
    private List<UserCustom> userList = new ArrayList<>();
    private FindUserAdapter userAdapter;

    @Override
    protected void initView(View view) {
        userList = StaticClass.userList;
        Log.d(TAG, "userListstatic: "+userList);
        user_Recyclerview = view.findViewById(R.id.find_user_recy);
        user_Recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        userAdapter = new FindUserAdapter(getContext(), userList);
        user_Recyclerview.setAdapter(userAdapter);

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_tab_user;
    }
}
