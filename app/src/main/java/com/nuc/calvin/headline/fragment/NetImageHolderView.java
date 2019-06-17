package com.nuc.calvin.headline.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.nuc.calvin.headline.R;

public class NetImageHolderView extends Holder<String> {
    private ImageView imageView;
    private Context mContext;

    public NetImageHolderView(View itemView, Context context) {
        super(itemView);
        mContext = context;
    }

    @Override
    protected void initView(View itemView) {
        imageView = itemView.findViewById(R.id.iv_banner);
    }

    @Override
    public void updateUI(String data) {
        Glide.with(mContext).load(data).into(imageView);
    }
}
