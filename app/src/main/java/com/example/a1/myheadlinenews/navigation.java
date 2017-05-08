package com.example.a1.myheadlinenews;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class navigation extends AppCompatActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){
                Intent intent = new Intent(navigation.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    private int theme = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   //去除title
         requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_navigation);*/
        //存储状态
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        boolean first = sharedPreferences.getBoolean("first", true);
        if(first){
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("frist",false);
            edit.commit();
            handler.sendEmptyMessageDelayed(0,2000);
        }else {
            Intent intent = new Intent(navigation.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
