package com.nuc.calvin.headline.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class PointImageView extends ImageView {

    /**
     * 默认模式
     */
    private int pointMode = NO_POINT;

    // 1.不显示红点
    public static final int NO_POINT = 1;
    // 2.只显示一个红点,表示有新消息
    public static final int ONLY_POINT = 2;
    // 3.显示一个红点,红点中间还有消息的数量
    public static final int NUMBER_POINT = 3;
    public PointImageView(Context context) {
        super(context);
    }

    public PointImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
