package com.example.a1.myheadlinenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.Night_styleutils;

public class Iphone_zhuceActivity extends AppCompatActivity {
    private int theme = 0;
    @BindView(R.id.login_back)
    ImageView mLoginBack;
    @BindView(R.id.login_viewno)
    View mLoginViewno;
    @BindView(R.id.login_text_zhuce)
    TextView mLoginTextZhuce;
    @BindView(R.id.login_text_wangji)
    TextView mLoginTextWangji;
    @BindView(R.id.activity_iphone_zhuce)
    RelativeLayout mActivityIphoneZhuce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphone_zhuce);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_back, R.id.login_viewno, R.id.login_text_zhuce, R.id.login_text_wangji, R.id.activity_iphone_zhuce})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.login_viewno:
                break;
            case R.id.login_text_zhuce:
                Intent intent = new Intent(Iphone_zhuceActivity.this,Iphone_zhuce.class);
                startActivity(intent);
                break;
            case R.id.login_text_wangji:
                break;
            case R.id.activity_iphone_zhuce:
                break;
        }
    }
}
