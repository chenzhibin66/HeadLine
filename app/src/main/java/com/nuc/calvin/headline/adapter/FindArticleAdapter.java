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
import com.nuc.calvin.headline.json.ArticleJs;

import java.util.ArrayList;
import java.util.List;

public class FindArticleAdapter extends RecyclerView.Adapter<FindArticleAdapter.ArticleViewHolder> {

    Context context;
    List<ArticleJs> list = new ArrayList<>();

    public FindArticleAdapter(Context context, List<ArticleJs> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_home_choice_item, viewGroup, false);
        ArticleViewHolder myHolder = new ArticleViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder articleViewHolder, int i) {
        ArticleJs articleJs = list.get(i);
        if (articleViewHolder instanceof ArticleViewHolder) {
            articleViewHolder.bindArticle(articleJs);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        TextView likeCount;
        TextView commentCount;
        TextView collectCount;
        TextView mTitleTv;
        TextView mWhereTv;
        ImageView mCommentIv;
        ImageView mLikeIv;
        ImageView mCollectIv;
        SimpleDraweeView authorImg;

        public ArticleViewHolder(@NonNull View itemView) {
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

        public void bindArticle(ArticleJs article) {
            mTitleTv.setText(article.getArticleTitle());
            mWhereTv.setText(article.getUser().getUsername());
            authorImg.setImageURI(article.getUser().getHeadImg());
            likeCount.setText(String.valueOf(article.getLikeCount()));
            commentCount.setText(String.valueOf(article.getCommentCount()));
            collectCount.setText(String.valueOf(article.getCollectCount()));
        }
    }
}
