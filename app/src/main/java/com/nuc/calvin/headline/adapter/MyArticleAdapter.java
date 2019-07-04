package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.json.ArticleJs;


import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class MyArticleAdapter extends RecyclerView.Adapter<MyArticleAdapter.MyHolder> {

    private Context context;
    private List<ArticleJs> articleJsList = new ArrayList<>();
    private LayoutInflater layoutInflater;

    public MyArticleAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<ArticleJs> list) {
        articleJsList.clear();
        articleJsList.addAll(list);
    }

    @NonNull
    @Override
    public MyArticleAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.my_article_item, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyArticleAdapter.MyHolder myHolder, final int i) {
        ArticleJs articleJs = articleJsList.get(i);
        Log.d(TAG, "onBindViewHolder: " + articleJs.toString());
        myHolder.likeCount.setText(String.valueOf(articleJs.getLikeCount()));
        myHolder.collectCount.setText(String.valueOf(articleJs.getCollectCount()));
        myHolder.commentCount.setText(String.valueOf(articleJs.getCommentCount()));
        myHolder.myName.setText(articleJs.getUser().getUsername());
        myHolder.myTitle.setText(articleJs.getArticleTitle());
    }

    @Override
    public int getItemCount() {
        return articleJsList == null ? 0 : articleJsList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView likeCount;
        TextView collectCount;
        TextView commentCount;
        TextView myName;
        TextView myTitle;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            myTitle = itemView.findViewById(R.id.my_article_title);
            myName = itemView.findViewById(R.id.my_name);
            likeCount = itemView.findViewById(R.id.my_article_like_count);
            collectCount = itemView.findViewById(R.id.my_article_collect_count);
            commentCount = itemView.findViewById(R.id.my_article_comment_count);
        }
    }
}
