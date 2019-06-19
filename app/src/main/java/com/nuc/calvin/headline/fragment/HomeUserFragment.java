package com.nuc.calvin.headline.fragment;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nuc.calvin.headline.R;


import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class HomeUserFragment extends BaseFragment {

    private ImageView user_head;

    @Override
    protected void initView(View view) {
        user_head = view.findViewById(R.id.user_img);
        setData();
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_home_user;
    }

    private void setData() {
        Glide.with(this).load(R.drawable.head)
                .bitmapTransform(new CropCircleTransformation(getContext())).into(user_head);
    }
}
