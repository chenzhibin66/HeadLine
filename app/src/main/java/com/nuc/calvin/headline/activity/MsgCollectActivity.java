package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nuc.calvin.headline.R;

public class MsgCollectActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ImageView left;

    @Override
    protected void initView(Bundle savedInstanceState) {
        recyclerView = findViewById(R.id.msg_fans_recy);
        left = findViewById(R.id.msg_fans_left);


        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MsgCollectActivity.this, MainActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_msg_collect;
    }
}
