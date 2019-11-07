package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.fragment.Welcom;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Welcome extends AppCompatActivity {
    @BindView(R.id.welcome_vp)
    ViewPager welcomeVp;
    private List<Fragment> fragments;
    private ViewPagerAdapter<Fragment> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        getFragments();
        adapter = new ViewPagerAdapter<>(getSupportFragmentManager(), fragments);
        welcomeVp.setAdapter(adapter);
    }
    public void getFragments() {
        fragments=new ArrayList<>();
        fragments.add(Welcom.newInstance(0));
        fragments.add(Welcom.newInstance(1));
        fragments.add(Welcom.newInstance(2));
    }
}
