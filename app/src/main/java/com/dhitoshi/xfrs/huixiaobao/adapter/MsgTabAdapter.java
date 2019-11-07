package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dxs on 2017/11/9.
 */

public class MsgTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;//fragment列表
    private List<String> titles= Arrays.asList("@我的","我@的");
    public MsgTabAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
