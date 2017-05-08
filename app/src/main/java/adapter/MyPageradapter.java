package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import fragment.NewsFragment;


public class MyPageradapter extends FragmentPagerAdapter {
    //传过来的标题数组
    private String[]title;
    //传过来的frament数组
    private List<NewsFragment> fragmentList;
    //构造器

    public MyPageradapter(FragmentManager fm, String[] title, List<NewsFragment> fragmentList) {
        super(fm);
        this.title = title;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }
     //CharSequence ：字符序列 ；用来设置当前选中标题
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
