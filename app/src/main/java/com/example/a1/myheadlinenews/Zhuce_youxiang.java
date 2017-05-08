package com.example.a1.myheadlinenews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import utils.UrL;
import utils.VolleyUtils;

public class Zhuce_youxiang extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdit_name;
    private EditText mEdit_password;
    private EditText mEdit_password_true;
    private EditText mEdit_email;
    private Button mButton_zhuce;
    private EditText mEdit_login_name;
    private EditText mEdit_login_password;
    private Button mMButton_login;
    private SharedPreferences sp_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce_youxiang);
        initview();//初始化控件
        initListener();
    }
    private void initListener() {
        mButton_zhuce.setOnClickListener(this);
        mMButton_login.setOnClickListener(this);
    }

    private void initview() {
        sp_user = getSharedPreferences("user_ziliao", Context.MODE_PRIVATE);
        mEdit_name = (EditText) findViewById(R.id.user_name);
        mEdit_password = (EditText) findViewById(R.id.user_password);
        mEdit_password_true = (EditText) findViewById(R.id.user_password_true);
        mEdit_email = (EditText) findViewById(R.id.user_email);
        mButton_zhuce = (Button) findViewById(R.id.zhuce);
        mEdit_login_name = (EditText) findViewById(R.id.user_login_name);
        mEdit_login_password = (EditText) findViewById(R.id.user_login_password);
        mMButton_login = (Button) findViewById(R.id.login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.zhuce:
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", mEdit_name.getText().toString());
                hashMap.put("password", mEdit_password.getText().toString());
                hashMap.put("password_confirm", mEdit_password_true.getText().toString());
                hashMap.put("client", UrL.SYSTEM_TYPE);
                hashMap.put("email", mEdit_email.getText().toString());
                VolleyUtils.Send(this, UrL.LINK_MOBILE_REG, hashMap);
                break;
            case R.id.login:
                HashMap<String,String>hashMap_dl = new HashMap<>();
                hashMap_dl.put("username",mEdit_name.getText().toString());
                hashMap_dl.put("password",mEdit_password.getText().toString());
                hashMap_dl.put("client",UrL.SYSTEM_TYPE);
                VolleyUtils.logins(this,UrL.LINK_MOBILE_LOGIN,hashMap_dl);
                break;
        }
    }
}
