package com.example.a1.myheadlinenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.Night_styleutils;

public class Iphone_yanzheng extends AppCompatActivity {
    private int theme = 0;
    @BindView(R.id.yz_back)
    ImageView mYzBack;
    @BindView(R.id.yz_text_pwd)
    TextView mYzTextPwd;
    @BindView(R.id.yz_viewno)
    View mYzViewno;
    @BindView(R.id.yanzheng_tel)
    TextView mYanzhengTel;
    @BindView(R.id.yanzheng_send)
    Button mYanzhengSend;
    @BindView(R.id.wachengzhuce_login)
    Button mWachengzhuceLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphone_yanzheng);

        ButterKnife.bind(this);
        //得到输入的手机号
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        mYanzhengTel.setText(phoneNumber);

    }

    @OnClick({R.id.yz_back, R.id.yz_text_pwd, R.id.yz_viewno, R.id.yanzheng_tel, R.id.yanzheng_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yz_back:
                finish();
                break;
            case R.id.yz_text_pwd:
                break;
            case R.id.yz_viewno:
                break;
            case R.id.yanzheng_tel:
                break;
            case R.id.yanzheng_send:
                break;
        }
    }
    //注册完成后登录点击事件
    @OnClick(R.id.wachengzhuce_login)
    public void onViewClicked() {
        Intent intent_wancheng = new Intent(Iphone_yanzheng.this,Yanzheng_Login.class);
        startActivity(intent_wancheng);
    }
}
