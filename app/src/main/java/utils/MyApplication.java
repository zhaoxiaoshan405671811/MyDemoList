package utils;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

import cn.jpush.android.api.JPushInterface;
import util.Utils;
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).memoryCacheExtraOptions(480,800).build();

        ImageLoader.getInstance().init(config);
        x.Ext.init(this);
        x.Ext.setDebug(false);
        Utils.init(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

}
