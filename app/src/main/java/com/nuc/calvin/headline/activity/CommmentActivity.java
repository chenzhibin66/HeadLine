package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nuc.calvin.headline.R;

public class CommmentActivity extends BaseActivity {
    private ImageView back_left;
    private TextView article_title;
    private RecyclerView comment_recycler;
    private TextView postComment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        back_left = findViewById(R.id.comment_left);
        article_title = findViewById(R.id.comment_article_title);
        comment_recycler = findViewById(R.id.comment_recy);
        postComment = findViewById(R.id.post_your_comment);


        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommmentActivity.this, PostCommentActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_commment;
    }
}
