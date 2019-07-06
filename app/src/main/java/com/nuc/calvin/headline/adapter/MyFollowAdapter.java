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
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.UserJs;

import java.util.List;

public class MyFollowAdapter extends RecyclerView.Adapter<MyFollowAdapter.FollowHolder> {

    Context context;
    private List<UserJs> userCustomList;

    public MyFollowAdapter(Context context, List<UserJs> userCustomList) {
        this.context = context;
        this.userCustomList = userCustomList;
    }

    @NonNull
    @Override
    public FollowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.my_follow_list, viewGroup, false);
        return new FollowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowHolder followHolder, int i) {
        UserJs userJs = userCustomList.get(i);
        followHolder.bindUser(userJs);
    }

    @Override
    public int getItemCount() {
        return userCustomList == null ? 0 : userCustomList.size();
    }

    public class FollowHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView user_head;
        TextView account_name;
        TextView signature;
        TextView share_count;
        TextView readme_count;
        ImageView subscriber;

        public FollowHolder(@NonNull View itemView) {
            super(itemView);
            user_head = itemView.findViewById(R.id.user_head);
            account_name = itemView.findViewById(R.id.account_name);
            signature = itemView.findViewById(R.id.signature);
            share_count = itemView.findViewById(R.id.share_count);
            readme_count = itemView.findViewById(R.id.readme_count);
            subscriber = itemView.findViewById(R.id.iv_subscriber);
        }

        public void bindUser(UserJs user) {
            user_head.setImageURI(user.getHeadImg());
            account_name.setText(user.getUsername());
            signature.setText(user.getSignature());
            share_count.setText(String.valueOf(user.getArticleCount()));
            readme_count.setText(String.valueOf(user.getFansCount()));
        }
    }
}
