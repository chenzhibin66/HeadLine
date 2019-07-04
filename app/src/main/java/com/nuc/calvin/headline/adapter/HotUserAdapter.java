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

import com.facebook.drawee.view.SimpleDraweeView;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.bean.User;
import com.nuc.calvin.headline.bean.UserCustom;
import com.nuc.calvin.headline.json.UserJs;

import java.util.ArrayList;
import java.util.List;

public class HotUserAdapter extends RecyclerView.Adapter<HotUserAdapter.UserHolder> implements View.OnClickListener {
    Context context;
    List<UserJs> userList = new ArrayList<>();

    public HotUserAdapter(Context context) {
        this.context = context;
    }

    public void addHotUser(List<UserJs> list) {
        userList.addAll(list);
    }
    public void clearDataList() {
      userList.clear();
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
        final UserJs user = userList.get(pos);
        if (userHolder instanceof UserHolder) {
            userHolder.bindUser(user);
        }
        userHolder.itemView.setTag(pos);
        userHolder.subscriber.setTag(pos);
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView user_head;
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
            itemView.setOnClickListener(HotUserAdapter.this);
            subscriber.setOnClickListener(HotUserAdapter.this);
        }

        public void bindUser(UserJs user) {
            user_head.setImageURI(user.getHeadImg());
            account_name.setText(user.getUsername());
            signature.setText(user.getSignature());
            share_count.setText(String.valueOf(user.getArticleCount()));
            readme_count.setText(String.valueOf(user.getFansCount()));
        }
    }

    //item里面有多个控件可以点击（item+item内部控件）
    public enum ViewName {
        ITEM,
        PRACTISE
    }

    //自定义一个回调接口来实现Click和LongClick事件
    public interface OnItemClickListener {
        void onItemClick(View v, ViewName viewName, int position);
        void onItemLongClick(View v);
    }


    private OnItemClickListener mOnItemClickListener;//声明自定义的接口

    //定义方法并传给外面的使用者
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        if (mOnItemClickListener != null) {
            switch (v.getId()) {
                case R.id.hot_account_list:
                    mOnItemClickListener.onItemClick(v, ViewName.PRACTISE, position);
                    break;
                default:
                    mOnItemClickListener.onItemClick(v, ViewName.ITEM, position);
                    break;
            }
        }
    }
}
