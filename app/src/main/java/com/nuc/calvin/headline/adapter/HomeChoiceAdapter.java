package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.model.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeChoiceAdapter extends RecyclerView.Adapter<HomeChoiceAdapter.ChoiceViewHolder> {
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
    public ChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ChoiceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_home_choice_item
                , viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceViewHolder choiceViewHolder, int i) {
        choiceViewHolder.bindData(dataList.get(i));
        choiceViewHolder.itemView.setClickable(true);
        choiceViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ChoiceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView mTitleTv;
        @Bind(R.id.tv_where)
        TextView mWhereTv;
        @Bind(R.id.tv_comment)
        TextView mCommentTv;
        @Bind(R.id.tv_like)
        TextView mLikeTv;
        @Bind(R.id.sdv_avatar)
        ImageView author;

        public ChoiceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        public void bindData(Article article) {
            mTitleTv.setText("欢迎来到IT头条");
            mWhereTv.setText("czb");
            author.setImageResource(R.drawable.author);
        }
    }
}
