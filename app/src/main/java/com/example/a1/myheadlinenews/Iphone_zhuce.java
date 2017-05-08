package com.example.a1.myheadlinenews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import utils.Night_styleutils;

import static com.example.a1.myheadlinenews.R.id.zhuce_edit_phone;
import static com.example.a1.myheadlinenews.R.id.zhuce_rb_yhxy;

public class Iphone_zhuce extends AppCompatActivity implements View.OnClickListener {
    private int theme = 0;
    @BindView(R.id.zhuce_back)
    ImageView mZhuceBack;
    @BindView(R.id.zhuce_text_zhuce)
    TextView mZhuceTextZhuce;
    @BindView(R.id.zhuce_viewno)
    View mZhuceViewno;
    @BindView(R.id.zhuce_text_login)
    TextView mZhuceTextLogin;
    @BindView(zhuce_edit_phone)
    EditText mZhuceEditPhone;
    @BindView(zhuce_rb_yhxy)
    RadioButton mZhuceRbYhxy;
    @BindView(R.id.zhuce_btn_next)
    Button mZhuceBtnNext;
    @BindView(R.id.activity_iphone_zhuce2)
    RelativeLayout mActivityIphoneZhuce2;
    private Handler handler;
    //回调函数
    EventHandler eh = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            if (result == SMSSDK.RESULT_COMPLETE) {
                handler.sendEmptyMessage(1);
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    handler.sendEmptyMessage(2);

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    handler.sendEmptyMessage(3);

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //返回支持发送验证码的国家列表
                    handler.sendEmptyMessage(4);
                }
            } else {
                ((Throwable) data).printStackTrace();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphone_zhuce2);
        ButterKnife.bind(this);
        SMSSDK.initSDK(this, "1d04349617895", "de68cfbd18fa953dd82d36b7793936dd");//初始化
        SMSSDK.registerEventHandler(eh); //注册短信回调
        ButterKnife.bind(this);
        mZhuceBack.setOnClickListener(this);
        mZhuceTextZhuce.setOnClickListener(this);
        mZhuceViewno.setOnClickListener(this);
        mZhuceTextLogin.setOnClickListener(this);
        mZhuceEditPhone.setOnClickListener(this);
        mZhuceRbYhxy.setOnClickListener(this);
        mZhuceBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.zhuce_back:
             finish();
             break;
         case R.id.zhuce_text_zhuce:
             break;
         case R.id.zhuce_viewno:
             break;
         case R.id.zhuce_text_login:
             finish();
             break;
         case zhuce_edit_phone:

             break;
         case zhuce_rb_yhxy:

             break;
         case R.id.zhuce_btn_next:
             handler = new Handler() {
                 @Override
                 public void handleMessage(Message msg) {
                     super.handleMessage(msg);
                     if (msg.what == 1)
                         Toast.makeText(Iphone_zhuce.this, "回调完成", Toast.LENGTH_SHORT).show();
                     else if (msg.what == 2)
                         Toast.makeText(Iphone_zhuce.this, "提交验证码成功", Toast.LENGTH_SHORT).show();
                     else if (msg.what == 3)
                         Toast.makeText(Iphone_zhuce.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                     else if (msg.what == 4)
                         Toast.makeText(Iphone_zhuce.this, "返回支持发送国家验证码", Toast.LENGTH_SHORT).show();
                 }
             };
             boolean isChecked = mZhuceRbYhxy.isChecked();
             //如果手机号为空而且协议未选中的话 就吐司请输入手机号
             if (TextUtils.isEmpty(mZhuceEditPhone.getText().toString().trim()) && isChecked==false){

                 Toast.makeText(Iphone_zhuce.this, "请输入手机号", Toast.LENGTH_SHORT).show();

                 //如果手机号为空而且协议已选中的话 也吐司请输入手机号
             }else if (TextUtils.isEmpty(mZhuceEditPhone.getText().toString().trim()) && isChecked){

                 Toast.makeText(Iphone_zhuce.this, "请输入手机号", Toast.LENGTH_SHORT).show();

                 //如果手机号不是11位数的话 就吐司手机号应为11位数字
             }else if (mZhuceEditPhone.getText().toString().trim().length()!=11){

                 Toast.makeText(Iphone_zhuce.this, "手机号应为11位数字", Toast.LENGTH_SHORT).show();

                 //如果手机号不为空而且协议未选中的话 就吐司请同意《今日头条用户协议》
             }else if (!TextUtils.isEmpty(mZhuceEditPhone.getText().toString().trim()) && isChecked==false){

                 Toast.makeText(Iphone_zhuce.this, "请同意《今日头条用户协议》", Toast.LENGTH_SHORT).show();

                 //如果手机号不为空而且协议已选中的话 就吐司进入短信验证码页面
             }else if (!TextUtils.isEmpty(mZhuceEditPhone.getText().toString().trim()) && isChecked){
                 SMSSDK.getVerificationCode("86", String.valueOf(mZhuceEditPhone.getText()));//请求获取短信验证码

                 Intent intent = new Intent(Iphone_zhuce.this, Iphone_yanzheng.class);
                 intent.putExtra("phoneNumber",mZhuceEditPhone.getText().toString() );
                 startActivity(intent);
             }
             break;
   }
     }
 }
