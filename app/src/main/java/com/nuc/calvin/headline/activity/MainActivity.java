package com.nuc.calvin.headline.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nuc.calvin.headline.R;
import com.nuc.calvin.headline.fragment.HomeFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private HomeFragment mHomeFragment = null;
    /**
     * 打开菜单
     */
    public static final String SCHEME_OPEN_MENU = "scheme_open_menu";

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        @SuppressLint("WrongViewCast") SimpleDraweeView avatarView = navigationView.getHeaderView(0).findViewById(R.id.sdv_avatar);
        if (avatarView != null) {
            avatarView.setImageURI(Uri.parse("https://avatars2.githubusercontent.com/u/4241807?v=3&s=460"));
        }

        //fragment Manager
        managerFragment(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SimpleDraweeView avatarView = navigationView.getHeaderView(0).findViewById(R.id.sdv_avatar);
        if (avatarView != null) {
            avatarView.setImageResource(R.drawable.head);
        }

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
            case R.id.nav_setting:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                    }
                }, 300);
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