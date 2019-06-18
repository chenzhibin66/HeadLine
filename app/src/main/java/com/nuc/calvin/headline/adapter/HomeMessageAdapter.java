package com.nuc.calvin.headline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.model.Message;

import java.util.List;

public class HomeMessageAdapter extends ArrayAdapter {

    private final int resourceId;

    List<Message> list;

    public HomeMessageAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = (Message) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView msgHead = view.findViewById(R.id.iv_message_head);
        TextView msgName = view.findViewById(R.id.tv_message_content);
        msgHead.setImageResource(message.getMessageImgId());
        msgName.setText(message.getMessageName());
        return view;

    }
}
