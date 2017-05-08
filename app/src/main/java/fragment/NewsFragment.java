package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1.myheadlinenews.R;

import utils.MyXutils;
import xlistview.bawei.com.xlistviewlibrary.XListView;

public class NewsFragment extends Fragment {
    String path ="http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573?uri=";
    private View mView;
    private XListView mXlv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment, null);
        initview();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        //获取传过来的数据
        String type = getArguments().getString("title");
        String url = path + type;
        new MyXutils(getActivity(),mXlv).getXutil(url);

    }

    private void initview() {
        mXlv = (XListView) mView.findViewById(R.id.listview);
    }
    //定义静态方法用来传值
    public static NewsFragment getInstance(String title){
     NewsFragment newsFragment = new NewsFragment();
        //实例化bundle，用bundle传值
        Bundle bundle= new Bundle();
        //绑定数据
        bundle.putString("title",title);
        //设置内容，参数是bundle，
        newsFragment.setArguments(bundle);
        return newsFragment;
    }
}
