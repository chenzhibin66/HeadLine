package com.nuc.calvin.headline.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.MainActivity;
import com.nuc.calvin.headline.adapter.HomeChoiceAdapter;
import com.nuc.calvin.headline.model.Banner;

import java.util.ArrayList;

import butterknife.Bind;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;


public class HomeChoiceFragment extends BaseFragment {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recyclerview)
    FamiliarRecyclerView mRecyclerView;
    @Bind(R.id.banner)
    ConvenientBanner banner;
    private HomeChoiceAdapter mAdapter;
    //是否自动轮播,控制如果是一张图片，不能滑动
    private boolean mCanLoop = true;
    private ArrayList<String> arrayList;
    private DisplayImageOptions options;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initImage();
        initBanner();
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
    }

    /**
     * 初始化
     * 添加三张展示照片，网上随便找的，正常形式是调用接口从自己的后台服务器拿取
     */
    private void initImage() {
        arrayList = new ArrayList<>();
        arrayList.add("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=JAVA&step_word=&hs=0&pn=1&spn=0&di=187000&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=2488957830%2C52389778&os=2066427650%2C3240754944&simid=0%2C0&adpicid=0&lpn=0&ln=1429&fr=&fmq=1560510872016_R&fm=&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fwww.cssxt.com%2Fuploadfile%2F2017%2F1113%2F20171113040829627.gif&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bvgks52f_z%26e3Bv54AzdH3Fz57999mmm-AzdH3FrAzdH3F0bnabbc_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
        arrayList.add("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=Python&step_word=&hs=0&pn=1&spn=0&di=155870&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3200469551%2C3330621307&os=1361394155%2C1103094659&simid=4221720956%2C663623900&adpicid=0&lpn=0&ln=1370&fr=&fmq=1560510931419_R&fm=detail&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimg.btwedo.com%2Fbccsk_com%2F20181210%2Fa20032001384f42c7f4af8afe8b7b31f1297.png&fromurl=ippr_z2C%24qAzdH3FAzdH3Fkvvfh_z%26e3Bv54AzdH3FfvAzdH3Fbmdaa_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
        arrayList.add("https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=C%2B%2B&step_word=&hs=0&pn=1&spn=0&di=199430&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=388184746%2C1369556294&os=2132567719%2C1987119204&simid=3330971565%2C468544053&adpicid=0&lpn=0&ln=1526&fr=&fmq=1560510951600_R&fm=detail&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fstatic.makeru.com.cn%2Fupload%2Feduplat%2Farticle%2F20180122%2F1516611015322168812.png&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3B4whj67_z%26e3Bv54_z%26e3BvgAzdH3FgjofAzdH3Flm0_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined");
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

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_choice;
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
        if (arrayList.size() <= 1) {
            mCanLoop = false;
        }
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new NetImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.view_home_banner;
            }
        }, arrayList).setPageIndicator(new int[]{R.drawable.ic_banner_indicator_unselected
                , R.drawable.ic_banner_indicator_selected})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Toast.makeText(getActivity(), "你点击了" + position, Toast.LENGTH_LONG).show();
                    }
                });
    }

    /**
     * 轮播图1 对应的holder
     */
    private class NetImageHolderView extends Holder<String> {

        private ImageView mImageView;
        private TextView mTextView;

        public NetImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            //找到对应展示图片的imageview
            mImageView = itemView.findViewById(R.id.iv_banner);
            //设置图片加载模式为铺满，具体请搜索 ImageView.ScaleType.FIT_XY
            mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //初始化options，可以加载不同情况下的默认图片
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.ic_launcher)//设置加载图片时候的图片
                    .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片uri为空或是错误的时候显示的图片
                    .showImageOnFail(R.mipmap.ic_launcher)//设置获取图片失败的默认图片
                    .cacheInMemory(true)//设置内存缓存
                    .cacheOnDisk(true)//设置外存缓存
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        }

        @Override
        public void updateUI(String data) {
            //使用ImageLoader加载图片
            ImageLoader.getInstance().displayImage(data, mImageView, options);
        }
    }
}
