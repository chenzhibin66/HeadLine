package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuc.calvin.headline.R;

public class ShareActivity extends BaseActivity {

    private ImageView iv_left;
    private TextView tv_submit;

    @Override
    protected void initView(Bundle savedInstanceState) {
        iv_left = findViewById(R.id.iv_actionbar_left);
        tv_submit = findViewById(R.id.tv_actionbar_submit);
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShareActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_share;
    }
}
