package com.nuc.calvin.headline.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.HomeChoiceAdapter;
import com.nuc.calvin.headline.model.Article;
import com.nuc.calvin.headline.model.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

import static android.support.constraint.Constraints.TAG;


public class HomeChoiceFragment extends BaseFragment {
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview)
    FamiliarRecyclerView mRecyclerView;
    ConvenientBanner banner;
    private HomeChoiceAdapter mAdapter;
    //是否自动轮播,控制如果是一张图片，不能滑动
    private boolean mCanLoop = true;
    private DisplayImageOptions options;
    private List<String> banner_image = new ArrayList<>();
    private List<Article> datas = new ArrayList<>();
    private String[] imagesString = new String[]{
            "http://39.105.110.19/static/images/文徵明/临兰亭序/fatie-000.jpg",
            "http://39.105.110.19/static/images/文徵明/临兰亭序/fatie-000.jpg",
            "http://39.105.110.19/static/images/文徵明/临兰亭序/fatie-000.jpg",
    };

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_choice;
    }

    @Override
    protected void initView(View view) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.view_home_banner, null);
        banner = header.findViewById(R.id.banner);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        initBanner();

        mAdapter = new HomeChoiceAdapter(getActivity());
        initArticle();
        mAdapter.setHeaderView(banner);
        mRecyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });

      /*  mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        }, 100);*/
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
        },banner_image).setPageIndicator(new int[]{R.drawable.ic_banner_indicator_unselected
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

    private void initArticle() {
        for (int i = 0; i < 20; i++) {
            Article article = new Article();
            article.setTitle("欢迎来到IT头条");
            article.setImage(R.drawable.author);
            article.setContributor("czb");
            datas.add(article);
        }
        mAdapter.addDataList(datas);
    }

    /**
     * 初始化
     * 添加三张展示照片，网上随便找的，正常形式是调用接口从自己的后台服务器拿取
     */
    private void initImage() {
        banner_image = Arrays.asList(imagesString);
    }


}
