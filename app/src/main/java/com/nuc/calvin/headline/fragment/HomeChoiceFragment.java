package com.nuc.calvin.headline.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.melnykov.fab.FloatingActionButton;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.ShareActivity;
import com.nuc.calvin.headline.adapter.HomeChoiceAdapter;
import com.nuc.calvin.headline.bean.Article;
import com.nuc.calvin.headline.json.ArticleJs;
import com.nuc.calvin.headline.utils.StaticClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;


public class HomeChoiceFragment extends BaseFragment {
    @Bind(R.id.swipe_refresh_layout)
    PullRefreshLayout pullRefreshLayout;
    @Bind(R.id.recyclerview)
    FamiliarRecyclerView mRecyclerView;
    ConvenientBanner banner;
    private HomeChoiceAdapter mAdapter;
    private DisplayImageOptions options;
    private List<String> banner_image = new ArrayList<>();
    private List<ArticleJs> datas = null;
    private List<Map<String, Object>> list = new ArrayList<>();
    private String[] imagesString = new String[]{
            "https://preview.qiantucdn.com/58picmark/element_origin_pic/33/82/49/66j58PIC933eZbU9yYePiMaRk.png!w1024_small",
            "https://preview.qiantucdn.com/58picmark/element_origin_pic/33/82/49/66j58PIC933eZbU9yYePiMaRk.png!w1024_small",
            "https://preview.qiantucdn.com/58picmark/element_origin_pic/33/82/49/66j58PIC933eZbU9yYePiMaRk.png!w1024_small"
    };

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_choice;
    }

    @Override
    protected void initView(View view) {
        Fresco.initialize(getContext());
        View header = LayoutInflater.from(getContext()).inflate(R.layout.view_home_banner, null);
        banner = header.findViewById(R.id.banner);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        initBanner();
        mAdapter = new HomeChoiceAdapter(getActivity());
        getAllArticle();
        mAdapter.setHeaderView(banner);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShareActivity.class);
                /*  intent.putExtra("userId", getUserId());*/
                Bundle bundle = new Bundle();
                bundle.putInt("userId",getUserId());
                intent.putExtras(bundle);
                getActivity().startActivity(intent);
            }
        });

      /*  mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        }, 100);*/
    }


    private Integer getUserId() {
        Bundle bundle = this.getArguments();
        Integer userId = bundle.getInt("userId",0);
        return userId;
    }


    @Override
    public void onResume() {
        super.onResume();
        //开始执行轮播，并设置轮播时长
        banner.startTurning(2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        //停止轮播
        banner.stopTurning();
    }


    /**
     * 初始化轮播图
     * setPageIndicator 设置指示器样式
     * setPageIndicatorAlign 设置指示器位置
     * setPointViewVisible 设置指示器是否显示
     * setCanLoop 设置是否轮播
     * setOnItemClickListener 设置每一张图片的点击事件
     */
    private void initBanner() {
        initImage();

        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new BannerImageHolderView();
            }
        }, banner_image).setPageIndicator(new int[]{R.drawable.ic_banner_indicator_unselected
                , R.drawable.ic_banner_indicator_selected})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(), "你点击了" + position, Toast.LENGTH_LONG).show();
                    }
                });
    }

    public class BannerImageHolderView implements Holder<String> {

        private ImageView imageView;


        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_banner_item, null);
            imageView = ButterKnife.findById(view, R.id.iv_banner);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, String data) {
            Glide.with(context).load(data).into(imageView);
        }
    }


    /**
     * 初始化
     * 添加三张展示照片，网上随便找的，正常形式是调用接口从自己的后台服务器拿取
     */
    private void initImage() {
        banner_image = Arrays.asList(imagesString);
    }


    private void getAllArticle() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(StaticClass.articleUrl).get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "请求服务器失败", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                /* gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();*/
                datas = gson.fromJson(res, new TypeToken<List<ArticleJs>>() {
                }.getType());
                mAdapter.addDataList(datas);
                Log.d(TAG, "datas=: " + datas);
            }
        });
    }
}
