package adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.a1.myheadlinenews.R;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import bean.MyBean;
import bean.News;

public class MyAdapter extends BaseAdapter {
    private List<MyBean.ResultBean.DataBean> mDatalist;
    private Context context;
    private PopupWindow popupWindow;
    ArrayList<News> newslist;
    public MyAdapter(List<MyBean.ResultBean.DataBean> mDatalist, Context context) {
        this.mDatalist = mDatalist;
        this.context = context;
        initPopWindow();
    }

    @Override
    public int getCount() {
        return mDatalist.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHodler hodler;
        if (convertView==null){
            convertView =View.inflate(context, R.layout.xlist_item,null);
            hodler = new ViewHodler();
            hodler.mImageView = (ImageView) convertView.findViewById(R.id.image);
            hodler.text1 = (TextView) convertView.findViewById(R.id.text);
            hodler.text2 = (TextView) convertView.findViewById(R.id.text2);
            hodler.button_showpop = (ImageView) convertView.findViewById(R.id.button_showpop);
            convertView.setTag(hodler);
        }else {
            hodler = (ViewHodler) convertView.getTag();
        }
            hodler.text1.setText(mDatalist.get(position).getTitle());
            hodler.text2.setText(mDatalist.get(position).getAuthor_name());
        x.image().bind(hodler.mImageView,mDatalist.get(position).getThumbnail_pic_s());
        hodler.button_showpop .setOnClickListener(new popAction(position));
        return convertView;
    }
    class ViewHodler{
        ImageView mImageView;
        TextView text1,text2;
        ImageView button_showpop;
    }

    /**
     * 初始化popWindow
     * */
    private void initPopWindow() {
        View popView = View.inflate(context,R.layout.listview_pop, null);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopMenuAnimation);
        btn_pop_close = (ImageView) popView.findViewById(R.id.btn_pop_close);
    }

    /** popWindow 关闭按钮 */
    private ImageView btn_pop_close;

    /**
     * 显示popWindow
     * */
    public void showPop(View parent, int x, int y,int postion) {
        //设置popwindow显示位置
        popupWindow.showAtLocation(parent, 0, x, y);
        //获取popwindow焦点
        popupWindow.setFocusable(true);
        //设置popwindow如果点击外面区域，便关闭。
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        if (popupWindow.isShowing()) {

        }
        btn_pop_close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramView) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 每个ITEM中more按钮对应的点击动作
     * */
    public class popAction implements View.OnClickListener {
        int position;
        public popAction(int position){
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            int[] arrayOfInt = new int[2];
            //获取点击按钮的坐标
            v.getLocationOnScreen(arrayOfInt);
            int x = arrayOfInt[0];
            int y = arrayOfInt[1];
            showPop(v, x , y, position);
        }
    }
}
