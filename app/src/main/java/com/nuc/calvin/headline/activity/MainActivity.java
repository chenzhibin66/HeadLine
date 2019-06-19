package com.nuc.calvin.headline.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.Toast;

import com.git.navmenu.NavMenuLayout;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.adapter.MyFragmentAdapter;
import com.nuc.calvin.headline.fragment.HomeChoiceFragment;
import com.nuc.calvin.headline.fragment.HomeFindFragment;
import com.nuc.calvin.headline.fragment.HomeMessageFragment;
import com.nuc.calvin.headline.fragment.HomeUserFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    ViewPager mViewPager;
    NavMenuLayout mNavMenuLayout;
    ArrayList<Fragment> listFragment;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private HomeChoiceFragment homeChoiceFragment;
    private HomeMessageFragment homeMessageFragment;
    private HomeFindFragment homeFindFragment;
    private HomeUserFragment homeUserFragment;

    private int[] iconRes = {R.drawable.rb_choice, R.drawable.rb_message, R.drawable.rb_search, R.drawable.rb_self};
    private int[] iconResSelected = {R.drawable.rb_choice_select, R.drawable.rb_message_select, R.drawable.rb_search_select, R.drawable.rb_self_select};
    private String[] textRes = {"精选", "消息", "发现", "我的"};

    @Override
    protected void initView(Bundle savedInstanceState) {
        mNavMenuLayout = findViewById(R.id.nav_layout);
        mViewPager = findViewById(R.id.viewpager);

        mNavMenuLayout.setIconRes(iconRes)//设置未选中图标
                .setIconResSelected(iconResSelected)//设置选中图标
                .setTextRes(textRes)//设置文字
                .setIconSize(60, 60)//设置图标大小
//                .setIconSize(0, 60,60)//设置指定位置的图标
//                .setTextSize(20)//设置文字大小
//                .setTextSize(0, 20)//指定位置的文字大小
//                .setTextColor(Color.GRAY)//未选中状态下文字颜色
                .setTextColorSelected(getResources().getColor(R.color.nav_bottom))//选中状态下文字颜色
//                .setTextColor(0, Color.YELLOW)//设置指定位置下文字颜色
//                .setTextColorSelected(0, Color.BLUE)//设置指定位置下选中状态文字颜色
//                .setMarginTop(PixelUtil.dpToPx(MainActivity.this, 5))//
//                .setMarginTop(1, PixelUtil.dpToPx(MainActivity.this, 10))
                .setMsg(0, String.valueOf(12))//设置显示消息
                .setMsg(1, "1")//设置显示消息
                .showRedPoint(2)//设置显示红点
                // .hideMsg(0)//隐藏消息
                //.hideMsg(1)//隐藏消息
                // .hideRedPoint(2)//隐藏红点
                .setSelected(0);//设置选中的位置


        //选中的点击事件
        mNavMenuLayout.setOnItemSelectedListener(new NavMenuLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                mViewPager.setCurrentItem(position);//选中后切换viwepager
            }
        });

        //已选中状态下的点击事件
        mNavMenuLayout.setOnItemReSelectedListener(new NavMenuLayout.OnItemReSelectedListener() {
            @Override
            public void onItemReSelected(int position) {

            }
        });

        addFragment();

        mViewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), listFragment));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                mNavMenuLayout.hideMsg(i);
            }

            @Override
            public void onPageSelected(int i) {
                mNavMenuLayout.setSelected(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    private void addFragment() {
        listFragment = new ArrayList<>();
        homeChoiceFragment = new HomeChoiceFragment();
        homeFindFragment = new HomeFindFragment();
        homeMessageFragment = new HomeMessageFragment();
        homeUserFragment = new HomeUserFragment();
        listFragment.add(homeChoiceFragment);
        listFragment.add(homeMessageFragment);
        listFragment.add(homeFindFragment);
        listFragment.add(homeUserFragment);
    }
}
