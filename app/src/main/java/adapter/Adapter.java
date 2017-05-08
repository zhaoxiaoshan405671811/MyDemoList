package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a1.myheadlinenews.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import bean.Mybean_shop;

/**
 * Created by 1 on 2017/4/25.
 */

public class Adapter extends BaseAdapter {
    private List<Mybean_shop.DataBean> mList;
    private Context context;

    public Adapter(List<Mybean_shop.DataBean> list, Context context) {
        mList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size() ;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler hodler;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.xlistview_item_shop, null);
            hodler = new ViewHodler();
            hodler.title = (TextView) convertView.findViewById(R.id.title_shop);
            hodler.wz = (TextView) convertView.findViewById(R.id.wang_shop);
            hodler.image = (ImageView) convertView.findViewById(R.id.image_shop);
            convertView.setTag(hodler);
        } else {
            hodler = (ViewHodler) convertView.getTag();
        }
        hodler.title.setText(mList.get(position).getTITLE());
        hodler.wz.setText(mList.get(position).getFROMNAME());
        //x.image().bind(hodler.image,mList.get(position).getIMAGEURL(),options);
        ImageLoader.getInstance().displayImage(mList.get(position).getIMAGEURL() + "", hodler.image);
        return convertView;
    }
    class ViewHodler{
        TextView title,wz;
        ImageView image;
    }
}
