package utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.a1.myheadlinenews.Details;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import adapter.MyAdapter;
import bean.MyBean;
import xlistview.bawei.com.xlistviewlibrary.XListView;

public class MyXutils {
    private MyBean mBean;
    private Context context;
    private XListView mXlv;
    private List<MyBean.ResultBean.DataBean> mDatalist;
    public MyXutils(Context context, XListView xlv) {
        this.context = context;
        mXlv = xlv;
    }

    public  void  getXutil(String uri){
        final RequestParams params = new RequestParams(uri);//params.addQueryStringParameter("num","10");
        //System.out.println(params);
        Log.d("zzz",params.toString());
        x.http().get(params, new Callback.CommonCallback<String>() {



            public void onSuccess(String result) {

                Gson gson=new Gson();
                mBean = gson.fromJson(result, MyBean.class);
                mDatalist = mBean.getResult().getData();
                mXlv.setAdapter(new MyAdapter(mDatalist,context));
                //点击显示详情页
                mXlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(context, Details.class);
                        //得到
                        intent.putExtra("url",mDatalist.get(position).getUrl());
                        context.startActivity(intent);
                    }
                });
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
}
