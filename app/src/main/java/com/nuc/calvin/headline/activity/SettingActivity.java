package com.nuc.calvin.headline.activity;

import android.support.annotation.Nullable;
import android.os.Bundle;

import com.nuc.calvin.headline.R;

public class SettingActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
    }
}
