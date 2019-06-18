package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.fragment.HomeChoiceFragment;
import com.nuc.calvin.headline.fragment.HomeFindFragment;
import com.nuc.calvin.headline.fragment.HomeMessageFragment;
import com.nuc.calvin.headline.fragment.HomeUserFragment;


public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup radioGroup;
    private ImageView iv_share;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private HomeChoiceFragment homeChoiceFragment;
    private HomeMessageFragment homeMessageFragment;
    private HomeFindFragment homeFindFragment;
    private HomeUserFragment homeUserFragment;
    //记录当前正在使用的fragment
    private Fragment isFragment;


    @Override
    protected void initView(Bundle savedInstanceState) {
        radioGroup = findViewById(R.id.rd_group);
        iv_share = findViewById(R.id.iv_add);
        radioGroup.setOnCheckedChangeListener(this);
        iv_share.setOnClickListener(this);
        /*homeChoiceFragment = new HomeChoiceFragment();
        homeMessageFragment = new HomeMessageFragment();
        homeFindFragment = new HomeFindFragment();
        homeUserFragment = new HomeUserFragment();*/
        initFragment(savedInstanceState);

    }

    private void initFragment(Bundle savedInstanceState) {
        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if (savedInstanceState == null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            if (homeChoiceFragment == null) {
                homeChoiceFragment = new HomeChoiceFragment();
            }
            isFragment = homeChoiceFragment;
            fragmentTransaction.replace(R.id.content, homeChoiceFragment).commit();
        }
    }

    private void switchFragment(Fragment from, Fragment to) {
        if (isFragment != to) {
            isFragment = to;
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!to.isAdded()) {// 先判断是否被add过
                fragmentTransaction.hide(from).add(R.id.content, to).commit();// 隐藏当前的fragment，add下一个到Activity中
            } else {
                fragmentTransaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_read:
                if (homeChoiceFragment == null) {
                    homeChoiceFragment = new HomeChoiceFragment();
                }
                switchFragment(isFragment, homeChoiceFragment);
                break;
            case R.id.rb_meassage:
                if (homeMessageFragment == null) {
                    homeMessageFragment = new HomeMessageFragment();
                }
                switchFragment(isFragment, homeMessageFragment);
                break;
            case R.id.rb_search:
                if (homeFindFragment == null) {
                    homeFindFragment = new HomeFindFragment();
                }
                switchFragment(isFragment, homeFindFragment);
                break;
            case R.id.rb_user:
                if (homeUserFragment == null) {
                    homeUserFragment = new HomeUserFragment();
                }
                switchFragment(isFragment, homeUserFragment);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, ShareActivity.class);
        startActivity(intent);
    }
}