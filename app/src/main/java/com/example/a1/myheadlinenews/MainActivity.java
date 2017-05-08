package com.example.a1.myheadlinenews;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.MyPageradapter;
import de.hdodenhof.circleimageview.CircleImageView;
import fragment.NewsFragment;
import utils.Night_styleutils;
import utils.UiUtils;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private int theme = 0;
    private TabLayout mTabLayout;
    private ViewPager mVp;
    //定义要展示的内容
    private String[] titleArray = new String[]{"推荐","热点","视频","北京","社会",
            "图片","科技","汽车","体育","头条"};
    //把URL的值写入集合
    private String[]typeArray =new String[]{"gj","ss","cj","kj",
            "js","ty","yl","gn","shehui","tt"};
    private List<NewsFragment>fragmentList = new ArrayList<NewsFragment>();
    private CircleImageView mImage_user;
    private ImageView mImage_seek;
    private SlidingMenu mMenu;
    private ImageView mImage_gridview;
    private Button mButton_gengduo;
    private ImageView mImage_iphone;
    private ImageView mImageView_qq;
    private static final String TAG = "MainActivity";
    private static final String APP_ID = "1105602574";//官方获取的APPID
    private Tencent mTencent;
    private BaseUiListener mIUiListener;
    private UserInfo mUserInfo;
    private CircleImageView mQq_tou;
    private TextView mQq_name;
    private LinearLayout mLine_title;
    private LinearLayout mLine_title_huan;
    private static boolean isExit = false;
    private LinearLayout mLinear_rijian;
    private LinearLayout Shezhi;
    private LinearLayout mLiner_shaocheng;
    private Button mViewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Night_styleutils.changeStyle(this, theme, savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         //设置侧滑
        setSideslip();
        initview();//找控件
        setListener();//设置监听
        //传入参数APPID和全局Context上下文
        mTencent = Tencent.createInstance(APP_ID,MainActivity.this.getApplicationContext());

    }
    // 定义一个变量，来标识是否退出
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    private void setListener() {
        //得到Fragment
        for(int x=0; x<typeArray.length ; x++){
            NewsFragment fragment = NewsFragment.getInstance(typeArray[x]);
            fragmentList.add(fragment);
        }
        //实例化适配器
        MyPageradapter adapter = new MyPageradapter(getSupportFragmentManager(),titleArray,fragmentList);
        //给viewpager设置适配器
        mVp.setAdapter(adapter);
        //设置TabLayout的模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //联动，设置tablayout与viewpager联动
        mTabLayout.setupWithViewPager(mVp);
        //设置点击事件
        mImage_user.setOnClickListener(this);
        mImage_gridview.setOnClickListener(this);
        mButton_gengduo.setOnClickListener(this);
        mImage_iphone.setOnClickListener(this);
        mImageView_qq.setOnClickListener(this);
        mLinear_rijian.setOnClickListener(this);
        Shezhi.setOnClickListener(this);
        mLiner_shaocheng.setOnClickListener(this);
        mViewById.setOnClickListener(this);
    }
    //初始化控间
    private void initview() {
        mTabLayout = (TabLayout)  findViewById(R.id.tablayout);
        mVp = (ViewPager) findViewById(R.id.Fragment_pager);
        mImage_user = (CircleImageView) findViewById(R.id.image_user);
        mImage_seek = (ImageView) findViewById(R.id.image_seek);
        mImage_gridview = (ImageView) findViewById(R.id.image_gridview);
        mButton_gengduo = (Button) mMenu.findViewById(R.id.button_gengduo);
        mImage_iphone = (ImageView) mMenu.findViewById(R.id.image_iphone);
        mImageView_qq = (ImageView) mMenu.findViewById(R.id.qq);
        mQq_tou = (CircleImageView) mMenu.findViewById(R.id.qq_tou);
        mQq_name = (TextView) mMenu.findViewById(R.id.qq_name);
        mLine_title = (LinearLayout) mMenu.findViewById(R.id.line_title);
        mLine_title_huan = (LinearLayout) mMenu.findViewById(R.id.line_title_huan);
        mLinear_rijian = (LinearLayout) mMenu.findViewById(R.id.linerijian);
        Shezhi = (LinearLayout) mMenu.findViewById(R.id.lineshezhi);
        mLiner_shaocheng = (LinearLayout) mMenu.findViewById(R.id.Liner_shaocheng);
        mViewById = (Button) mMenu.findViewById(R.id.button_city);
    }
    private void setSideslip() {
        // configure the SlidingMenu
        mMenu = new SlidingMenu(this);
        //设置侧滑模式为向左滑动
        mMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        mMenu.setShadowWidthRes(R.dimen.shadow_width);
        mMenu.setShadowDrawable(R.color.colorAccent);

        // 设置滑动菜单视图的宽度
        mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        mMenu.setFadeDegree(0.35f);
        /**
         * SLIDING_WINDOW will include the Title/ActionBar in the content
         * section of the SlidingMenu, while SLIDING_CONTENT does not.
         */
        mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        mMenu.setMenu(R.layout.left_menu);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_user:
                if(mMenu.isMenuShowing()){
                    mMenu.showContent();
                }else{
                    mMenu.showMenu();
                }
                break;
            case R.id.image_gridview:
                Intent intent = new Intent(this,Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.button_gengduo:
                Intent intentgd = new Intent(this,Main3Activitygengduo.class);
                startActivity(intentgd);
                break;
            case R.id.image_iphone:
                Intent intent_iphone_zhuce = new Intent(this,Iphone_zhuceActivity.class);
                startActivity(intent_iphone_zhuce);
                break;
            case R.id.qq:
                mIUiListener = new BaseUiListener();
                //all表示获取所有权限
                mTencent.login(MainActivity.this,"all", mIUiListener);
                break;
            case R.id.linerijian:
                UiUtils.switchAppTheme(MainActivity.this);
                MainActivity.this.reload();

                break;
            case R.id.lineshezhi:
                Intent intent_sz = new Intent(this,SettingActivity.class);
                startActivity(intent_sz);
                break;
            case R.id.Liner_shaocheng:
                Intent intent_sc = new Intent(this,Shop_Activity.class);
                startActivity(intent_sc);
                break;
            case R.id.button_city:
                Intent intent_city = new Intent(this,City_Activity.class);
                startActivity(intent_city);
                break;
        }
    }
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
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
                        //显示视图
                        mLine_title.setVisibility(View.GONE);
                        //隐藏view
                        mLine_title_huan.setVisibility(View.VISIBLE);
                        //侧拉视图设置
                        Glide.with(MainActivity.this).load(figureurl_qq_1).error(R.mipmap.ic_launcher).into(mQq_tou);
                        mQq_name.setText(nickName);
                      //得到回传过来的qq头像
                        Glide.with(MainActivity.this).load(figureurl_qq_1).error(R.mipmap.ic_launcher).into(mImage_user);
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
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();

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
    //主题切换方法
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);//进入动画
        finish();
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        startActivity(intent);
    }
}
