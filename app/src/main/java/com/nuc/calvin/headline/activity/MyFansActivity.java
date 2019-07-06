package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nuc.calvin.headline.R;

public class MyFansActivity extends BaseActivity {

    private ImageView my_fans_left;


    @Override
    protected void initView(Bundle savedInstanceState) {
        my_fans_left = findViewById(R.id.my_fans_left);
        my_fans_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyFansActivity.this, MainActivity.class);
                intent.putExtra("id", 3);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_fans;
    }
}
