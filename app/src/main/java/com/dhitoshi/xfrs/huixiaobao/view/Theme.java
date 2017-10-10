package com.dhitoshi.xfrs.huixiaobao.view;
import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.dhitoshi.bottombar.BottomBar;
import com.dhitoshi.bottombar.OnTabSelectListener;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.NoSlidingViewPager;
import com.dhitoshi.xfrs.huixiaobao.fragment.Client;
import com.dhitoshi.xfrs.huixiaobao.fragment.News;
import com.dhitoshi.xfrs.huixiaobao.fragment.Personal;
import com.dhitoshi.xfrs.huixiaobao.fragment.StateMent;
import com.dhitoshi.xfrs.huixiaobao.fragment.Work;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static android.os.Build.VERSION_CODES.M;

@RuntimePermissions
public class Theme extends AppCompatActivity {
    @BindView(R.id.theme_viewpager)
    NoSlidingViewPager themeViewpager;
    @BindView(R.id.theme_bottomBar)
    BottomBar themeBottomBar;
    private List<Fragment> themeFragments;
    private ViewPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initEvents();
        ActivityManagerUtil.addDestoryActivity(this,"Theme");
        ThemePermissionsDispatcher.callWithCheck(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("Theme");
    }

    //初始化页面控件
    private void initViews() {
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintResource(R.color.colorPrimary);
        getThemeFragments();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), themeFragments);
        themeViewpager.setAdapter(adapter);
        themeViewpager.setOffscreenPageLimit(5);
    }
    //初始化数据
    private void initDatas() {

    }
    //初始化页面事件
    private void initEvents() {
        themeBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId, int position) {
                themeViewpager.setCurrentItem(position,false);
            }
        });

    }
    private List<Fragment> getThemeFragments() {
        themeFragments = new ArrayList<>();
        themeFragments.add(News.newInstance());
        themeFragments.add(Client.newInstance());
        themeFragments.add(Work.newInstance());
        themeFragments.add(StateMent.newInstance());
        themeFragments.add(Personal.newInstance());

        return themeFragments;
    }
   @NeedsPermission({Manifest.permission.CALL_PHONE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void call(){

    }
    @OnShowRationale({Manifest.permission.CALL_PHONE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void ShowRationaleFoCall(PermissionRequest request){
        request.proceed();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ThemePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
