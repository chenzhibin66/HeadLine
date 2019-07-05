package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nuc.calvin.headline.R;

public class MsgLikesActivity extends BaseActivity {

    private RecyclerView like_recy;
    private ImageView like_left;

    @Override
    protected void initView(Bundle savedInstanceState) {
        like_recy = findViewById(R.id.msg_like_recy);
        like_left = findViewById(R.id.msg_like_left);

        like_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MsgLikesActivity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_msg_likes;
    }
}
