package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.bean.Article;

import java.util.ArrayList;
import java.util.List;

public class HotArticleAdapter extends RecyclerView.Adapter<HotArticleAdapter.MyHolder> {

    List<Article> articleList = new ArrayList<>();
    Context context;

    public HotArticleAdapter(Context context) {
        this.context = context;
    }

    public void addHotArticle(List<Article> list) {
        articleList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hot_article_item, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        final int pos = myHolder.getLayoutPosition();
        final Article article = articleList.get(pos);
        if (myHolder instanceof MyHolder) {
            myHolder.bindHotArticle();
        }
        myHolder.itemView.setClickable(true);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了" + pos, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView article_title;
        ImageView user_head;
        TextView reader_like;
        TextView reader_comment;
        TextView author_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            article_title = itemView.findViewById(R.id.article_title);
            user_head = itemView.findViewById(R.id.user_head);
            reader_like = itemView.findViewById(R.id.reader_like);
            reader_comment = itemView.findViewById(R.id.reader_comment);
            author_name = itemView.findViewById(R.id.author_name);
        }

        public void bindHotArticle() {
            article_title.setText("欢迎来到IT头条");
            author_name.setText("czb");
            user_head.setImageResource(R.drawable.author);
        }
    }
}
