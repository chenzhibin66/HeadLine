package com.nuc.calvin.headline.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nuc.calvin.headline.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragmentList = new ArrayList<>();
    private List<CharSequence> mTitleList = new ArrayList<>();

    public HomeAdapter(FragmentManager fm) {
        super(fm);
    }


    public void addDataList(List<BaseFragment> fragments, List<CharSequence> titles) {
        if (fragments != null && titles != null) {
            mFragmentList.clear();
            mTitleList.clear();

            mFragmentList.addAll(fragments);
            mTitleList.addAll(titles);
        }
        notifyDataSetChanged();
    }

    @Override

    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mTitleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
