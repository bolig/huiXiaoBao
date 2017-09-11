package com.dhitoshi.xfrs.huixiaobao.view;
import android.Manifest;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.dhitoshi.ImmersionBar.ImmersionBar;
import com.dhitoshi.bottombar.BottomBar;
import com.dhitoshi.bottombar.OnTabSelectListener;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.NoSlidingViewPager;
import com.dhitoshi.xfrs.huixiaobao.fragment.Client;
import com.dhitoshi.xfrs.huixiaobao.fragment.News;
import com.dhitoshi.xfrs.huixiaobao.fragment.Personal;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

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
        ThemePermissionsDispatcher.callWithCheck(this);
    }
    //初始化页面控件
    private void initViews() {
        getThemeFragments();
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), themeFragments);
        themeViewpager.setAdapter(adapter);
        ImmersionBar.with(this).navigationBarEnable(false)
                .barColor(R.color.colorPrimary).init();
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
        themeFragments.add(Personal.newInstance());
        return themeFragments;
    }
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void call(){

    }
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void ShowRationaleFoCall(PermissionRequest request){
        request.proceed();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ThemePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
