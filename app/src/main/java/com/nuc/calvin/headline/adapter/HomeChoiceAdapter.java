package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.model.Article;

import java.util.ArrayList;
import java.util.List;


public class HomeChoiceAdapter extends RecyclerView.Adapter {
    private final List<Article> dataList = new ArrayList<>();
    private Context context;

    public HomeChoiceAdapter(Context context) {
        this.context = context;
    }

    public void addDataList(List<Article> list) {
        addDataList(list, false);
    }


    public void addDataList(List<Article> list, boolean append) {
        if (list != null) {
            if (!append) {
                dataList.clear();
            }
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ChoiceViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTv;
        TextView mWhereTv;
        TextView mCommentTv;
        TextView mLikeTv;

        public ChoiceViewHolder(View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.tv_title);
            mWhereTv = itemView.findViewById(R.id.tv_comment);
            mLikeTv = itemView.findViewById(R.id.tv_like);
        }

        public void bindData(Article article) {
            if (article == null) return;
            try {
                mTitleTv.setText(article.getTitle());
                mWhereTv.setText("选自 " + article.getSubject().getName());
//                mIconSdv.setImageURI(Uri.parse(article.getUser().getAvatar()));
                mLikeTv.setText(article.getLike_count() + "");
                mCommentTv.setText(article.getComment_count() + "");
            } catch (Exception e) {
                //TODO 日志处理 ，待优化
            }
        }
    }
}
