package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.bean.Article;
import com.nuc.calvin.headline.json.ArticleJs;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class HotArticleAdapter extends RecyclerView.Adapter<HotArticleAdapter.MyHolder> {

    List<ArticleJs> articleList = new ArrayList<>();
    Context context;

    public HotArticleAdapter(Context context) {
        this.context = context;
    }

    public void addHotArticle(List<ArticleJs> list) {
        articleList.clear();
        articleList.addAll(list);
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
        final ArticleJs article = articleList.get(pos);
        if (myHolder instanceof MyHolder) {
            myHolder.bindHotArticle(article);
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
        SimpleDraweeView user_head;
        ImageView reader_like;
        ImageView reader_comment;
        ImageView reader_collect;
        TextView author_name;
        TextView hot_likeCount;
        TextView hot_commentCount;
        TextView hot_collectCount;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            article_title = itemView.findViewById(R.id.hot_title);
            user_head = itemView.findViewById(R.id.hot_article_img);
            reader_like = itemView.findViewById(R.id.hot_article_like);
            reader_comment = itemView.findViewById(R.id.hot_article_comment);
            author_name = itemView.findViewById(R.id.hot_article_author);
            reader_collect = itemView.findViewById(R.id.hot_article_collect);
            hot_likeCount = itemView.findViewById(R.id.hot_like_count);
            hot_collectCount = itemView.findViewById(R.id.hot_collect_count);
            hot_commentCount = itemView.findViewById(R.id.hot_comment_count);
        }

        public void bindHotArticle(ArticleJs articleJs) {
            article_title.setText(articleJs.getArticleTitle());
            author_name.setText(articleJs.getUser().getUsername());
            Log.d(TAG, "bindHotArticle: "+articleJs.getUser().getHeadImg());
            user_head.setImageURI(articleJs.getUser().getHeadImg());
            hot_collectCount.setText(String.valueOf(articleJs.getCollectCount()));
            hot_likeCount.setText(String.valueOf(articleJs.getLikeCount()));
            hot_commentCount.setText(String.valueOf(articleJs.getCommentCount()));
        }
    }
}
