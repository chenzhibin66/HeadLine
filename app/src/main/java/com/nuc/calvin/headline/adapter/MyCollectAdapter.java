package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.json.CollectJs;

import java.util.ArrayList;
import java.util.List;

public class MyCollectAdapter extends RecyclerView.Adapter<MyCollectAdapter.CollectHolder> {
    private Context context;
    private List<CollectJs> collectJsList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public MyCollectAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<CollectJs> list) {
        collectJsList.clear();
        collectJsList.addAll(list);
    }

    @NonNull
    @Override
    public MyCollectAdapter.CollectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.view_home_choice_item, viewGroup, false);
        return new CollectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCollectAdapter.CollectHolder collectHolder, int i) {
        final CollectJs collectJs = collectJsList.get(i);
        collectHolder.bindData(collectJs);
    }

    @Override
    public int getItemCount() {
        return collectJsList == null ? 0 : collectJsList.size();
    }

    public class CollectHolder extends RecyclerView.ViewHolder {
        TextView likeCount;
        TextView commentCount;
        TextView collectCount;
        TextView mTitleTv;
        TextView mWhereTv;
        ImageView mCommentIv;
        ImageView mLikeIv;
        ImageView mCollectIv;
        SimpleDraweeView authorImg;

        public CollectHolder(@NonNull View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.item_title);
            mWhereTv = itemView.findViewById(R.id.tv_where);
            mLikeIv = itemView.findViewById(R.id.iv_like);
            mCommentIv = itemView.findViewById(R.id.iv_comment);
            mCollectIv = itemView.findViewById(R.id.iv_collect);
            authorImg = itemView.findViewById(R.id.sdv_avatar);
            likeCount = itemView.findViewById(R.id.like_count);
            commentCount = itemView.findViewById(R.id.comment_count);
            collectCount = itemView.findViewById(R.id.collect_count);
        }

        public void bindData(CollectJs collectJs) {
            mTitleTv.setText(collectJs.getArticle().getArticleTitle());
            mWhereTv.setText(collectJs.getUsername());
            authorImg.setImageURI(collectJs.getUser().getHeadImg());
            likeCount.setText(String.valueOf(collectJs.getArticle().getLikeCount()));
            commentCount.setText(String.valueOf(collectJs.getArticle().getCommentCount()));
           /* collectCount.setText(String.valueOf(collectJs.getArticle().));*/

        }
    }
}
