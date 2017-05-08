package com.example.a1.myheadlinenews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import adapter.Adapter;
import bean.Mybean_shop;
import xlistview.bawei.com.xlistviewlibrary.XListView;

public class Shop_Activity extends AppCompatActivity {


    int channelId =0;
    int startNum = 0;
    private List<Mybean_shop.DataBean> mList;
    private XListView mXlsShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_);
        initdat();
        getData(channelId,startNum);
    }

    private void initdat() {
        mXlsShop = (XListView) findViewById(R.id.xls_shop);
        mXlsShop.setPullLoadEnable(true);
        mXlsShop.setPullRefreshEnable(true);
        mXlsShop.setXListViewListener(new XListView.IXListViewListener() {
            @Override//下拉刷新
            public void onRefresh() {
                if (mList!=null){
                    mList.clear();
                }
                startNum=0;
                getData(channelId, startNum);
                onLoad();
            }

            @Override//加载跟多
            public void onLoadMore() {
                startNum++;
                //得到网络数据i
                getData(channelId, startNum);

                onLoad();
            }
        });
    }


    private void getData(int channelId, int startNum) {
        RequestParams params = new RequestParams("http://www.93.gov.cn/93app/data.do");
        params.addQueryStringParameter("channelId", channelId + "");
        params.addQueryStringParameter("startNum", startNum + "");
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Mybean_shop bean = gson.fromJson(result, Mybean_shop.class);
                mList = bean.getData();
                Adapter myadapter = new Adapter(mList, Shop_Activity.this);
                mXlsShop.setAdapter(myadapter);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void onLoad() {
        // TODO Auto-generated method stub
        mXlsShop.stopRefresh();
        mXlsShop.stopLoadMore();
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前系统时间
        String nowTime = df.format(new Date(System.currentTimeMillis()));
        // 释放时提示正在刷新时的当前时间
        mXlsShop.setRefreshTime(nowTime);
    }
}