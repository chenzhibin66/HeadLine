package com.nuc.calvin.headline.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments=new ArrayList<>();
    private List<String> list =new ArrayList<>();

    public SearchAdapter(FragmentManager fm, List<Fragment> fragments, List<String> list) {
        super(fm);
        this.fragments = fragments;
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
