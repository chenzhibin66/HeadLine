package com.nuc.calvin.headline.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.SearchActivity;
import com.nuc.calvin.headline.adapter.FindUserAdapter;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.utils.StaticClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;

public class FindUserFragment extends BaseFragment {

    private RecyclerView user_Recyclerview;
    private List<UserCustom> userList = new ArrayList<>();
    private FindUserAdapter userAdapter;
    private Handler handler;


    @SuppressLint("HandlerLeak")
    @Override
    protected void initView(View view) {
        user_Recyclerview = view.findViewById(R.id.find_user_recy);
        user_Recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        user_Recyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        userAdapter = new FindUserAdapter(getContext(), userList);
        user_Recyclerview.setAdapter(userAdapter);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1056:
                        userAdapter.notifyDataSetChanged();
                        break;
                }
            }
        };
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_tab_user;
    }

    public void refreshData(String search) {
        queryUser(search);
    }

    private void queryUser(String keyWord) {
        Log.d(TAG, "queryUserword: " + keyWord);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.queryUserByWordUrl + "?keyWord=" + keyWord).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                List<UserCustom> userTemp = gson.fromJson(res, new TypeToken<List<UserCustom>>() {
                }.getType());
                userList.clear();
                userList.addAll(userTemp);
                Log.d(TAG, "onResponseresult: " + userList);
                handler.sendEmptyMessage(1056);
            }
        });
    }
}
