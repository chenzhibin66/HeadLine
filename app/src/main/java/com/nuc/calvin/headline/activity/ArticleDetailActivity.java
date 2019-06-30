package com.nuc.calvin.headline.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.git.navmenu.NavMenuLayout;
import com.nuc.calvin.headline.R;

public class ArticleDetailActivity extends BaseActivity {

    private TextView detailTitle;
    private TextView author_name;
    private WebView webView;
    private ImageView iv_left;
    private ImageView iv_like;
    private ImageView iv_comment;
    private ImageView iv_collect;

    @Override
    protected void initView(Bundle savedInstanceState) {
        iv_left = findViewById(R.id.iv_detail_left);
        iv_like = findViewById(R.id.iv_detail_like);
        iv_comment = findViewById(R.id.iv_detail_comment);
        iv_collect = findViewById(R.id.iv_detail_collect);
        detailTitle = findViewById(R.id.detail_title);
        author_name = findViewById(R.id.name_author);
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String url = i.getStringExtra("url");
        String authorname = i.getStringExtra("authorName");
        detailTitle.setText(title);
        author_name.setText(authorname);
        webView.loadUrl(url);

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArticleDetailActivity.this, MainActivity.class);
                intent.putExtra("id", 0);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.article_detail;
    }
}
