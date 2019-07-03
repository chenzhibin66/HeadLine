package com.nuc.calvin.headline.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener {


    protected Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        initView(savedInstanceState);
       /* handler = initHandle();*/
    }


/*    protected abstract Handler initHandle();*/

    /**
     * 初始化UI
     *
     * @param
     */
    protected abstract void initView(Bundle savedInstanceState);


    /**
     * 销毁activity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 获得一个activity中的layout
     *
     * @return
     */
    protected abstract int getContentView();


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
