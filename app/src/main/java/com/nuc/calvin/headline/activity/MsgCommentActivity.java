package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nuc.calvin.headline.R;

public class MsgCommentActivity extends BaseActivity {

    private RecyclerView com_recy;
    private ImageView com_left;

    @Override
    protected void initView(Bundle savedInstanceState) {
        com_recy = findViewById(R.id.msg_comment_recy);
        com_left = findViewById(R.id.msg_comment_left);

        com_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MsgCommentActivity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_msg_comment;
    }
}
