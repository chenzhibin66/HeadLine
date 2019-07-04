package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.activity.ArticleDetailActivity;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.ArticleJs;
import com.nuc.calvin.headline.utils.ShareUtils;
import com.wx.goodview.GoodView;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * 根据不同的 ViewType 返回不同的 ViewHolder
 * 通过 setter 方法将不同的 View 注入进 Adapter
 */
public class HomeChoiceAdapter extends RecyclerView.Adapter<HomeChoiceAdapter.ChoiceViewHolder> implements View.OnClickListener {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NOMAL = 1;

    private View HeaderView;

    private List<ArticleJs> dataList = new ArrayList<>();

    private Context context;

    public HomeChoiceAdapter(Context context) {
        this.context = context;
    }


    public void setHeaderView(View mHeaderView) {
        this.HeaderView = mHeaderView;
        notifyItemInserted(0);//插入下标0位置
    }

    public void addDataList(List<ArticleJs> articleJs) {
        dataList.addAll(articleJs);
    }

    public void clearDataList() {
        dataList.clear();
    }

/*
    public void addList(List<ArticleJs> list, boolean append) {
        if (list != null) {
            if (!append) {
                dataList.clear();
            }
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }*/


    @Override
    public int getItemViewType(int position) {
        if (HeaderView == null) {
            return TYPE_NOMAL;
        }
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_NOMAL;
    }

    @NonNull
    @Override
    public ChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (HeaderView != null && i == TYPE_HEADER) {
            return new ChoiceViewHolder(HeaderView);
        }
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_home_choice_item, viewGroup, false);
        return new ChoiceViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceViewHolder choiceViewHolder, int i) {

        if (getItemViewType(i) == TYPE_HEADER) {
            return;
        }
        final int pos = getRealPosition(choiceViewHolder);//这里的 position 实际需要不包括 header
        final ArticleJs articleJs = dataList.get(pos);
        Log.d(TAG, "onBindViewHolderLikeCount: " + articleJs.getLikeCount());
        if (choiceViewHolder instanceof ChoiceViewHolder) {
            choiceViewHolder.bindData(articleJs);
        }
        choiceViewHolder.itemView.setTag(pos);
        choiceViewHolder.mLikeIv.setTag(pos);
        choiceViewHolder.mCommentIv.setTag(pos);
        choiceViewHolder.mCollectIv.setTag(pos);
       /* choiceViewHolder.itemView.setClickable(true);
        choiceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ArticleDetailActivity.class);
                intent.putExtra("title", articleJs.getArticleTitle());
                intent.putExtra("url", articleJs.getArticleUrl());
                intent.putExtra("authorName", articleJs.getUser().getUsername());
                v.getContext().startActivity(intent);
            }
        });*/
    }

    /**
     * 添加头部布局后的位置
     * headerView 不为空则 position - 1
     */
    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return HeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        //header 不为空，则 reclclerview 的总 Count 需要 +1（把 Header 加上算一个 item）
        return HeaderView == null ? dataList.size() : dataList.size() + 1;
    }


    public class ChoiceViewHolder extends RecyclerView.ViewHolder {
        TextView likeCount;
        TextView commentCount;
        TextView collectCount;
        TextView mTitleTv;
        TextView mWhereTv;
        ImageView mCommentIv;
        ImageView mLikeIv;
        ImageView mCollectIv;
        SimpleDraweeView authorImg;

        public ChoiceViewHolder(View itemView) {
            super(itemView);
            if (itemView == HeaderView) {
                return;
            }
            mTitleTv = itemView.findViewById(R.id.item_title);
            mWhereTv = itemView.findViewById(R.id.tv_where);
            mLikeIv = itemView.findViewById(R.id.iv_like);
            mCommentIv = itemView.findViewById(R.id.iv_comment);
            mCollectIv = itemView.findViewById(R.id.iv_collect);
            authorImg = itemView.findViewById(R.id.sdv_avatar);
            likeCount = itemView.findViewById(R.id.like_count);
            commentCount = itemView.findViewById(R.id.comment_count);
            collectCount = itemView.findViewById(R.id.collect_count);
            itemView.setOnClickListener(HomeChoiceAdapter.this);
            mLikeIv.setOnClickListener(HomeChoiceAdapter.this);
            mCommentIv.setOnClickListener(HomeChoiceAdapter.this);
            mCollectIv.setOnClickListener(HomeChoiceAdapter.this);

        }

        public void bindData(ArticleJs article) {
            mTitleTv.setText(article.getArticleTitle());
            mWhereTv.setText(article.getUser().getUsername());
            authorImg.setImageURI(article.getUser().getHeadImg());
            likeCount.setText(String.valueOf(article.getLikeCount()));
            commentCount.setText(String.valueOf(article.getCommentCount()));
            collectCount.setText(String.valueOf(article.getCollectCount()));

        }
    }

    //item里面有多个控件可以点击（item+item内部控件）
    public enum ViewName {
        ITEM,
        PRACTISE
    }

    //自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, ViewName viewName, int position);

        void onItemLongClick(View v);
    }


    private OnItemClickListener mOnItemClickListener;//声明自定义的接口

    //定义方法并传给外面的使用者
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            switch (v.getId()) {
                case R.id.recyclerview:
                    mOnItemClickListener.onItemClick(v, ViewName.PRACTISE, position);
                    break;
                default:
                    mOnItemClickListener.onItemClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }
}
