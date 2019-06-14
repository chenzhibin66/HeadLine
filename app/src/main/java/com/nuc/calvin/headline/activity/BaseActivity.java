package com.nuc.calvin.headline.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseFragment.OnFragmentInteractionListener {
    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView(savedInstanceState);
        ButterKnife.bind(this);
        if (isNeedToolbar()) {
            initToolbar();
        }
    }

    /**
     * 初始化UI
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * init toolbar
     */
    private void initToolbar() {
        if (mToolbar == null) {
            throw new IllegalArgumentException("Toolbar must not be null");
        }
        setSupportActionBar(mToolbar);
    }

    /**
     * is or need toolbar
     *
     * @return
     */
    protected boolean isNeedToolbar() {
        return true;
    }

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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //不建议用finish
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}