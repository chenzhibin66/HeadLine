package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.model.Message;


import java.util.ArrayList;
import java.util.List;

public class HomeMessageAdapter extends RecyclerView.Adapter<HomeMessageAdapter.MyHolder> {
    Context context;
    List<Message> msglist = new ArrayList<>();

    public HomeMessageAdapter(Context context) {
        this.context = context;
    }

    public void addMessage(List<Message> list) {
        msglist.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 引入xml传送给viewHolder
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_message_item, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        final int postion = myHolder.getLayoutPosition();
        final Message message = msglist.get(postion);
        if (myHolder instanceof MyHolder) {
            myHolder.bindMessage(message);
        }
        myHolder.bindMessage(message);
        myHolder.itemView.setClickable(true);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (postion) {
                    case 0:
                        Toast.makeText(context, "点击了" + postion, Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(context, "点击了" + postion, Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(context, "点击了" + postion, Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(context, "点击了" + postion, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return msglist.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        ImageView msg_head;
        TextView msg_title;
        TextView msg_count;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            msg_head = itemView.findViewById(R.id.iv_message_head);
            msg_title = itemView.findViewById(R.id.tv_message_content);
            msg_count = itemView.findViewById(R.id.point);
        }

        public void bindMessage(Message message) {
            msg_head.setImageResource(message.getMessageImgId());
            msg_title.setText(message.getMessageName());
            msg_count.setText(message.getMessageCount());
        }
    }

}
