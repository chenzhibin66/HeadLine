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
import com.nuc.calvin.headline.bean.Comment;
import com.nuc.calvin.headline.bean.CommentCustom;
import com.nuc.calvin.headline.bean.User;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.CommentJs;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {

    Context context;
    private List<CommentJs> list=new ArrayList<>();

    public CommentAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<CommentJs> commentJsList) {
        list.addAll(commentJsList);
    }

    @NonNull
    @Override
    public CommentAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_list_item, viewGroup, false);
        CommentHolder commentHolder = new CommentHolder(view);
        return commentHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.CommentHolder commentHolder, int i) {
        CommentJs commentCustom = list.get(i);
        commentHolder.bindData(commentCustom);

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView commentor_head;
        TextView commentorName;
        TextView commentContent;
        TextView time;


        public CommentHolder(@NonNull View itemView) {
            super(itemView);
            commentor_head = itemView.findViewById(R.id.comment_head);
            commentorName = itemView.findViewById(R.id.comment_name);
            commentContent = itemView.findViewById(R.id.commentContents);
            time = itemView.findViewById(R.id.comment_time);
        }

        private void bindData(CommentJs comment) {
            User user = comment.getUser();
            commentor_head.setImageURI(user.getHeadImg());
            commentorName.setText(user.getUsername());
            commentContent.setText(comment.getCommentContent());
            time.setText(comment.getTime());


        }
    }
}
