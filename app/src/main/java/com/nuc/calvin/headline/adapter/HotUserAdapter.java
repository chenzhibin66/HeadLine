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
import com.nuc.calvin.headline.model.User;

import java.util.ArrayList;
import java.util.List;

public class HotUserAdapter extends RecyclerView.Adapter<HotUserAdapter.UserHolder> {
    Context context;
    List<User> userList = new ArrayList<>();

    public HotUserAdapter(Context context) {
        this.context = context;
    }

    public void addHotUser(List<User> list) {
        userList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hot_user_item, viewGroup, false);
        UserHolder userHolder = new UserHolder(view);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {
        final int pos = userHolder.getLayoutPosition();
        final User user = userList.get(pos);
        if (userHolder instanceof UserHolder) {
            userHolder.bindUser(user);
        }
        userHolder.itemView.setClickable(true);
        userHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了" + pos, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        ImageView user_head;
        TextView account_name;
        TextView signature;
        TextView share_count;
        TextView readme_count;
        ImageView subscriber;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            user_head = itemView.findViewById(R.id.user_head);
            account_name = itemView.findViewById(R.id.account_name);
            signature = itemView.findViewById(R.id.signature);
            share_count = itemView.findViewById(R.id.share_count);
            readme_count = itemView.findViewById(R.id.readme_count);
            subscriber = itemView.findViewById(R.id.iv_subscriber);
        }

        public void bindUser(User user) {
            user_head.setImageResource(R.drawable.head);
            account_name.setText("Java工程师");
            signature.setText("后台开发");
            share_count.setText("111");
            readme_count.setText("1232");
        }
    }
}
