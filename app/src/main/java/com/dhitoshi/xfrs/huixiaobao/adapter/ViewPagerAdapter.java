package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by dxs on 2017/8/23.
 */
public class ViewPagerAdapter<T> extends FragmentStatePagerAdapter {
    private List<T> fragments;
    public ViewPagerAdapter(FragmentManager fm, List<T> fragments) {
        super(fm);
        this.fragments = fragments;
    }
    @Override
    public Fragment getItem(int position) {
        return (Fragment) fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
}
