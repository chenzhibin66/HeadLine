package com.nuc.calvin.headline.fragment;

import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
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
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;


public class HomeChoiceFragment extends BaseFragment {
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview)
    FamiliarRecyclerView mRecyclerView;

    ConvenientBanner banner;
    private HomeChoiceAdapter mAdapter;
    //是否自动轮播,控制如果是一张图片，不能滑动
    private boolean mCanLoop = true;
    private List<String> banner_image;
    private List<Article> datas = new ArrayList<>();
    private String[] imagesString = new String[]{
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=JAVA&step_word=&hs=0&pn=1&spn=0&di=187000&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2488957830%2C52389778&os=2066427650%2C3240754944&simid=0%2C0&adpicid=0&lpn=0&ln=1429&fr=&fmq=1560510872016_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fwww.cssxt.com%2Fuploadfile%2F2017%2F1113%2F20171113040829627.gif&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bvgks52f_z%26e3Bv54AzdH3Fz57999mmm-AzdH3FrAzdH3F0bnabbc_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=Python&step_word=&hs=0&pn=0&spn=0&di=184910&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=1997646403%2C2041715424&os=122982239%2C800321103&simid=0%2C0&adpicid=0&lpn=0&ln=1343&fr=&fmq=1560690028753_R&fm=detail&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg.mp.itc.cn%2Fupload%2F20160902%2Fd4d813ba49224604b5b3de8d99296c0f_th.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bf5i7_z%26e3Bv54AzdH3FwAzdH3F88nnaab8b_da8a8b&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined",
            "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E9%98%BF%E6%B3%95%E7%8B%97&step_word=&hs=0&pn=1&spn=0&di=330&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3153533189%2C2191281777&os=3192963016%2C514120430&simid=3483198314%2C357259670&adpicid=0&lpn=0&ln=1060&fr=&fmq=1560690048787_R&fm=detail&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F1603%2F15%2F7661360_alphago_thumb.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Brv5gstgj_z%26e3Bv54_z%26e3BvgAzdH3Fw7p5pjviAzdH3F0mmAzdH3F0mm8nma_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined"
    };
    private DisplayImageOptions options;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_choice;
    }

    @Override
    protected void initView(View view) {
        banner = (ConvenientBanner) View.inflate(getActivity(), R.layout.view_home_banner, null);
        mRecyclerView.addHeaderView(banner);

        mAdapter = new HomeChoiceAdapter(getActivity());
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

        initBanner();
        initArticle();
       /* mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        }, 100);*/
    }

    /**
     * 初始化
     * 添加三张展示照片，网上随便找的，正常形式是调用接口从自己的后台服务器拿取
     */
    private void initImage() {
        banner_image = Arrays.asList(imagesString);

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
        if (banner_image.size() <= 1) {
            mCanLoop = false;
        }

        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new NetImageHolderView(itemView, getContext());
            }

            @Override
            public int getLayoutId() {
                return R.layout.view_banner_item;
            }
        }, banner_image)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器，不需要圆点指示器可以不设
                .setPageIndicator(new int[]{R.drawable.ic_banner_indicator_unselected, R.drawable.ic_banner_indicator_selected})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                //设置指示器是否可见
                .setPointViewVisible(true)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(), "你点击了" + position, Toast.LENGTH_LONG).show();
                    }
                });
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
}
