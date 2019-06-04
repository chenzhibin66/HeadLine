package com.nuc.calvin.headline.activity;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.fragment.HomeFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private HomeFragment mHomeFragment;
    /**
     * 打开菜单
     */
    public static final String SCHEME_OPEN_MENU = "scheme_open_menu";

    @Override
    protected void initView(Bundle savedInstanceState) {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

////        SimpleDraweeView avatarView = navigationView.getHeaderView(0).findViewById(R.id.sdv_avatar);
//        if (avatarView != null) {
////            avatarView.setImageResource(head_img);
//        }

    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected boolean isNeedToolbar() {
        return false;
    }

    /**
     * 管理Fragment
     *
     * @param savedInstanceState
     */
    private void managerFragment(Bundle savedInstanceState) {
        FragmentManager fm = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mHomeFragment = HomeFragment.newInstance();
            fm.beginTransaction().add(R.id.container, mHomeFragment).commit();
        }

        fm.beginTransaction().show(mHomeFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().hide(mHomeFragment).commit();
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (id) {
            case R.id.nav_home:
                fm.beginTransaction().show(mHomeFragment).commit();
                break;
        }
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        super.onFragmentInteraction(uri);
        if (SCHEME_OPEN_MENU.equals(uri.getScheme())) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }
}