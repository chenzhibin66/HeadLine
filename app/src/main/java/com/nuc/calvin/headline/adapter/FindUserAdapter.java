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

import java.util.ArrayList;
import java.util.List;

public class FindUserAdapter extends RecyclerView.Adapter<FindUserAdapter.UserHolder> {

    Context context;
    private List<UserCustom> userList = new ArrayList<>();

    public FindUserAdapter(Context context, List<UserCustom> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.hot_user_item, viewGroup, false);
        UserHolder userHolder = new UserHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {
        UserCustom userCustom = userList.get(i);
        userHolder.bindData(userCustom);

    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView userImg;
        TextView userName;
        TextView user_signature;
        TextView user_share_count;
        TextView followCount;
        ImageView iv_collect;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            userImg = itemView.findViewById(R.id.user_head);
            userName = itemView.findViewById(R.id.account_name);
            user_signature = itemView.findViewById(R.id.signature);
            user_share_count = itemView.findViewById(R.id.share_count);
            followCount = itemView.findViewById(R.id.readme_count);
            iv_collect = itemView.findViewById(R.id.iv_subscriber);
        }

        private void bindData(UserCustom userCustom) {
            userImg.setImageURI(userCustom.getHeadImg());
            userName.setText(userCustom.getUsername());
            user_signature.setText(userCustom.getSignature());
            user_share_count.setText(String.valueOf(userCustom.getArticleCount()));
            followCount.setText(String.valueOf(userCustom.getFollowCount()));

        }
    }
}
