package com.example.a1.myheadlinenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.Night_styleutils;

public class Main3Activitygengduo extends AppCompatActivity {
    private int theme = 0;
    @BindView(R.id.gd_login_back)
    ImageView mGdLoginBack;
    @BindView(R.id.gd_login_viewno)
    View mGdLoginViewno;
    @BindView(R.id.gd_ig_pic)
    ImageView mGdIgPic;
    @BindView(R.id.gd_btn_phonelogin)
    Button mGdBtnPhonelogin;
    @BindView(R.id.gd_btn_zhucenew)
    Button mGdBtnZhucenew;
    @BindView(R.id.gd_textno)
    TextView mGdTextno;
    @BindView(R.id.gd_viewno1)
    View mGdViewno1;
    @BindView(R.id.gd_viewno2)
    View mGdViewno2;
    @BindView(R.id.activity_main3_activitygengduo)
    RelativeLayout mActivityMain3Activitygengduo;
    @BindView(R.id.qq_login)
    ImageView mQqLogin;
    @BindView(R.id.xinlang_login)
    ImageView mXinlangLogin;
    @BindView(R.id.wenxin_login)
    ImageView mWenxinLogin;
    @BindView(R.id.wenbo_denglu)
    ImageView mWenboDenglu;
    @BindView(R.id.renren_login)
    ImageView mRenrenLogin;
    private static final String TAG = "Main3Activitygengduo";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_activitygengduo);
        ButterKnife.bind(this);
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,Main3Activitygengduo.this.getApplicationContext());
    }

    @OnClick({R.id.gd_login_back, R.id.gd_login_viewno, R.id.gd_ig_pic, R.id.gd_btn_phonelogin, R.id.gd_btn_zhucenew, R.id.gd_textno, R.id.gd_viewno1, R.id.gd_viewno2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gd_login_back:
                finish();
                break;
            case R.id.gd_login_viewno:
                break;
            case R.id.gd_ig_pic:
                break;
            case R.id.gd_btn_phonelogin:
                startActivity(new Intent(this, Iphone_zhuceActivity.class));
                break;
            case R.id.gd_btn_zhucenew:
                Intent intent = new Intent(this, Iphone_zhuce.class);
                startActivity(intent);
                break;
            //跳转到有邮箱的注册页
            case R.id.gd_textno:
                Intent mintent = new Intent(this,Zhuce_youxiang.class);
                startActivity(mintent);
                break;
            case R.id.gd_viewno1:
                break;
            case R.id.gd_viewno2:
                break;
        }
    }

    @OnClick({R.id.qq_login, R.id.xinlang_login, R.id.wenxin_login, R.id.wenbo_denglu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qq_login:
                mIUiListener=new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(Main3Activitygengduo.this,"all", mIUiListener);
                break;
            case R.id.xinlang_login:
                break;
            case R.id.wenxin_login:
                break;
            case R.id.wenbo_denglu:
                break;
        }
    }
    /**
     * 自定义监听器实现IUiListener接口后，需要实现的3个方法
     * onComplete完成 onError错误 onCancel取消
     */
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(Main3Activitygengduo.this, "授权成功", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "response:" + response);
            JSONObject obj = (JSONObject) response;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken,expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Log.e(TAG,"登录成功"+response.toString());
                        JSONObject res= (JSONObject) response;
                        String nickName=res.optString("nickname");//获取昵称
                        String figureurl_qq_1=res.optString("figureurl_qq_1");//获取图片
                    }

                    @Override
                    public void onError(UiError uiError) {
                        Log.e(TAG,"登录失败"+uiError.toString());
                    }

                    @Override
                    public void onCancel() {
                        Log.e(TAG,"登录取消");

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(Main3Activitygengduo.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(Main3Activitygengduo.this, "授权取消", Toast.LENGTH_SHORT).show();

        }

    }

    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){
            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
