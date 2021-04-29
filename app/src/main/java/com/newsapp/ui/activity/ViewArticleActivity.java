package com.newsapp.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.newsapp.R;
import com.newsapp.databinding.ActivityViewArticleBinding;

public class ViewArticleActivity extends AppCompatActivity {

    private ActivityViewArticleBinding binding;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_article);
        init();
    }

    private void init() {
        if (getIntent().hasExtra("url"))
            url = getIntent().getStringExtra("url");
        setWebView();
    }

    private void setWebView() {
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.loadUrl(url);
    }
}