package com.nuc.calvin.headline.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.melnykov.fab.FloatingActionButton;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.ArticleDetailActivity;
import com.nuc.calvin.headline.activity.CommentActivity;
import com.nuc.calvin.headline.activity.PostCommentActivity;
import com.nuc.calvin.headline.activity.ShareActivity;
import com.nuc.calvin.headline.adapter.HomeChoiceAdapter;
import com.nuc.calvin.headline.bean.Banner;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.ArticleJs;
import com.nuc.calvin.headline.json.BannerJs;
import com.nuc.calvin.headline.utils.ShareUtils;
import com.nuc.calvin.headline.utils.StaticClass;
import com.wx.goodview.GoodView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    GoodView goodView;
    @Bind(R.id.swipe_refresh_layout)
    PullRefreshLayout pullRefreshLayout;
    @Bind(R.id.recyclerview)
    FamiliarRecyclerView mRecyclerView;
    ConvenientBanner banner;
    private HomeChoiceAdapter mAdapter;
    private DisplayImageOptions options;
    private List<String> banner_image = new ArrayList<>();
    private List<ArticleJs> datas = null;
    private List<BannerJs> bannerJs = new ArrayList<>();
    private List<Map<String, Object>> list = new ArrayList<>();
    private Handler handler;
    private boolean isChanged = false;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_choice;
    }


    @SuppressLint("HandlerLeak")
    @Override
    protected void initView(View view) {
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getAllArticle();
                        pullRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        pullRefreshLayout.measure(0, 0);
        pullRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                pullRefreshLayout.setRefreshing(true);
                getAllArticle();

            }
        });
        goodView = new GoodView(getContext());
        Fresco.initialize(getContext());
        View header = LayoutInflater.from(getContext()).inflate(R.layout.view_home_banner, null);
        banner = header.findViewById(R.id.banner);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mAdapter = new HomeChoiceAdapter(getActivity());
        mAdapter.setHeaderView(banner);
        initImage();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(homeClickListener);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter.notifyDataSetChanged();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShareActivity.class);
                getActivity().startActivity(intent);
            }
        });


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        refreshData();
                        break;
                    case 1:
                        mAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        pullRefreshLayout.setRefreshing(false);
                        break;
                    case 1056:
                        initBanner();
                        break;
                    default:
                        break;
                }
            }

        };
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
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new BannerImageHolderView();
            }
        }, bannerJs).setPageIndicator(new int[]{R.drawable.ic_banner_indicator_unselected
                , R.drawable.ic_banner_indicator_selected})
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        BannerJs bannerarticle = bannerJs.get(position);
                        Intent intent1 = new Intent(getContext(), ArticleDetailActivity.class);
                        intent1.putExtra("title", bannerarticle.getArticle().getArticleTitle());
                        intent1.putExtra("url", bannerarticle.getArticle().getArticleUrl());
                        intent1.putExtra("authorName", bannerarticle.getUser().getUsername());
                        startActivity(intent1);

                    }
                });
    }


    public class BannerImageHolderView implements Holder<BannerJs> {

        private SimpleDraweeView imageView;
        private TextView bannerTitle;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.view_banner_item, null);
            imageView = ButterKnife.findById(view, R.id.iv_banner);
            bannerTitle = ButterKnife.findById(view, R.id.banner_title);
            return view;
        }

        @Override
        public void UpdateUI(Context context, int position, BannerJs data) {
            Glide.with(context).load(data.getBannerImage()).into(imageView);
            bannerTitle.setText(data.getArticle().getArticleTitle());
        }


    }


    /**
     * 初始化
     * 添加三张展示照片
     */
    private void initImage() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.bannerUrl).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "获取广告图片失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                bannerJs = gson.fromJson(res, new TypeToken<List<BannerJs>>() {
                }.getType());
                Log.d(TAG, "bannerResult" + bannerJs.toString());
                for (int i = 0; i < bannerJs.size(); i++) {
                    banner_image.add(bannerJs.get(i).getBannerImage());
                    Log.d(TAG, "banIMg " + banner_image.toString());
                    handler.sendEmptyMessage(1056);
                }
            }
        });
    }


    private void getAllArticle() {
        UserCustom user = ShareUtils.getInstance().getUser();
        Log.d(TAG, "getAllArticle: " + user.getUserId());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(StaticClass.articleUrl + "?userId=" + user.getUserId()).get().build();
        Log.d(TAG, "getAllArticleUrl: " + StaticClass.articleUrl + "?userId=" + user.getUserId());
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
                datas = gson.fromJson(res, new TypeToken<List<ArticleJs>>() {
                }.getType());
                mAdapter.addDataList(datas);
                handler.sendEmptyMessage(2);
            }
        });
    }

    private void refreshData() {
        UserCustom user = ShareUtils.getInstance().getUser();
        Log.d(TAG, "getAllArticle: " + user.getUserId());
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(StaticClass.articleUrl + "?userId=" + user.getUserId()).get().build();
        Log.d(TAG, "getAllArticleUrl: " + StaticClass.articleUrl + "?userId=" + user.getUserId());
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
                Log.d(TAG, "onResponse: " + res);
                Gson gson = new Gson();
                ArrayList temp = gson.fromJson(res, new TypeToken<List<ArticleJs>>() {
                }.getType());
                mAdapter.clearDataList();
                mAdapter.addDataList(temp);
                handler.sendEmptyMessage(1);
            }
        });
    }

    private HomeChoiceAdapter.OnItemClickListener homeClickListener = new HomeChoiceAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View v, HomeChoiceAdapter.ViewName viewName, final int position) {
            final ArticleJs articleJs = datas.get(position);
            final UserCustom userCustom = ShareUtils.getInstance().getUser();
            final Integer userId = userCustom.getUserId();
            final Integer articleId = articleJs.getArticleId();
            Log.d(TAG, "onItemClickArticle: " + articleJs.toString() + "pos=" + position);
            switch (v.getId()) {
                case R.id.iv_like:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            like(userId, articleId);

                        }
                    });
                    if (isChanged) {
                        ((ImageView) v).setImageResource(R.drawable.ic_iv_like);
                    } else {
                        ((ImageView) v).setImageResource(R.drawable.ic_iv_like_press);
                    }

                    goodView.setText("+1");
                    goodView.show(v);
                    break;
                case R.id.iv_comment:
                    if (articleJs.getCommentCount() == 0) {
                        Intent intent1 = new Intent(v.getContext(), PostCommentActivity.class);
                        intent1.putExtra("articleId", articleJs.getArticleId());
                        v.getContext().startActivity(intent1);
                    } else {
                        Intent intent = new Intent(v.getContext(), CommentActivity.class);
                        intent.putExtra("articleTitle", articleJs.getArticleTitle());
                        intent.putExtra("articleId", articleJs.getArticleId());
                        v.getContext().startActivity(intent);
                    }
                    break;
                case R.id.iv_collect:
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            collectArticle(userId, articleId);
                        }
                    });
                    if (isChanged) {
                        ((ImageView) v).setImageResource(R.drawable.ic_mini_collect_press);
                    } else {
                        ((ImageView) v).setImageResource(R.drawable.ic_mini_collect);
                    }
                    break;
                default:
                    Intent intent = new Intent(v.getContext(), ArticleDetailActivity.class);
                    intent.putExtra("title", articleJs.getArticleTitle());
                    intent.putExtra("url", articleJs.getArticleUrl());
                    intent.putExtra("authorName", articleJs.getUser().getUsername());
                    v.getContext().startActivity(intent);
                    break;
            }

        }

        @Override
        public void onItemLongClick(View v) {

        }
    };

    private void collectArticle(Integer userId, Integer articleId) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.collectUrl + "?userId="
                + userId + "&articleId=" + articleId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "网络请求失败!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /* mAdapter.notifyDataSetChanged();*/
                        handler.sendEmptyMessage(0);
                        goodView.setText("收藏成功");
                        goodView.show(getView());

                    }
                });
            }
        });
    }


    private void like(Integer userId, Integer articleId) {
        //拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        Request request = builder.get().url(StaticClass.likeUrl + "?userId="
                + userId + "&articleId=" + articleId).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.sendEmptyMessage(0);
            }
        });

    }
}
