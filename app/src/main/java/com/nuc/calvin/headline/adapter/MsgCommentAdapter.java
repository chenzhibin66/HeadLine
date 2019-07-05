package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.json.CommentJs;

import java.util.ArrayList;
import java.util.List;

public class MsgCommentAdapter extends RecyclerView.Adapter<MsgCommentAdapter.CommentHolder> {

    Context context;
    private List<CommentJs> commentJsList = new ArrayList<>();

    public MsgCommentAdapter(Context context, List<CommentJs> commentJsList) {
        this.context = context;
        this.commentJsList = commentJsList;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.msg_comment_item, viewGroup, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder commentHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return commentJsList == null ? 0 : commentJsList.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView commentor_head;
        TextView commentorName;
        TextView commentTime;
        TextView content;
        TextView comment_title;

        public CommentHolder(@NonNull View itemView) {
            super(itemView);

            commentor_head = itemView.findViewById(R.id.commentor_img);
            commentorName = itemView.findViewById(R.id.commentor_name);
            commentTime = itemView.findViewById(R.id.comment_time);
            content = itemView.findViewById(R.id.comment_content);
            comment_title = itemView.findViewById(R.id.comment_article_title);
        }

    }
}
