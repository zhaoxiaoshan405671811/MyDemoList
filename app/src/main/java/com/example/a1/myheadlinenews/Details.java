package com.example.a1.myheadlinenews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import utils.Night_styleutils;

public class Details extends AppCompatActivity implements View.OnClickListener {
    private int theme = 0;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private ImageView mImageView_back;
    private ImageView mImageView_fontSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initview();//找控件

        initdata();//初始化数据

    }
    private void initdata() {
        //得到传过来的值
        String url = getIntent().getStringExtra("url");
        //获取设置
        WebSettings settings = mWebView.getSettings();
        //支持js
        settings.setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        //在自己应用中跳转页面
        mWebView.setWebViewClient(new WebViewClient());
        //辅助webview处理进度条
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                //显示
                mProgressBar.setVisibility(View.VISIBLE);
                //设置
                mProgressBar.setProgress(newProgress);
                if(newProgress==100){
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
        mImageView_back.setOnClickListener(this);
    }


    private void initview() {
        mWebView = (WebView) findViewById(R.id.webview);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mImageView_back = (ImageView) findViewById(R.id.De_imagview_back);
        mImageView_fontSize = (ImageView) findViewById(R.id.De_show_fontSize);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.De_imagview_back:
                finish();
                break;
        }
    }
}
