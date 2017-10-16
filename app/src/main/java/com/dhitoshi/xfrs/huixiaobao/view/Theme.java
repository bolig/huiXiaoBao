package com.dhitoshi.xfrs.huixiaobao.view;
import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.dhitoshi.bottombar.BottomBar;
import com.dhitoshi.bottombar.OnTabSelectListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VersionBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.UpdateDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.UpdateClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ViewPagerAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.common.NewVersion;
import com.dhitoshi.xfrs.huixiaobao.common.NoSlidingViewPager;
import com.dhitoshi.xfrs.huixiaobao.fragment.Client;
import com.dhitoshi.xfrs.huixiaobao.fragment.News;
import com.dhitoshi.xfrs.huixiaobao.fragment.Personal;
import com.dhitoshi.xfrs.huixiaobao.fragment.StateMent;
import com.dhitoshi.xfrs.huixiaobao.fragment.Work;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;

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
    private static Handler handler=new Handler();
    private Runnable runnable;
    private YWIMKit mIMKit;
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
        getVersion();
    }
    private void getVersion() {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getVersion(),new CommonObserver(new HttpResult<HttpBean<VersionBean>>() {
            @Override
            public void OnSuccess(final HttpBean<VersionBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    if(httpBean.getData().getCode()>getLocalVersionCode(Theme.this)){
                        runnable=new Runnable() {
                            @Override
                            public void run() {
                                String activityName=getTopActivityName(Theme.this);
                                activityName=activityName.substring(activityName.lastIndexOf(".")+1,activityName.length());
                                Activity localActivity= ActivityManagerUtil.getCurrentActivity(activityName);
                                UpdateDialog dialog=new UpdateDialog(localActivity);
                                dialog.setUpdateTitle("慧销宝"+httpBean.getData().getName()+"更新啦");
                                dialog.setContent(httpBean.getData().getDescription());
                                dialog.setVersionName(httpBean.getData().getName());
                                dialog.setUrl(httpBean.getData().getUrl());
                                dialog.show();
                                dialog.addUpdateClick(new UpdateClick() {
                                    @Override
                                    public void update(String apkUri, String versionName) {
                                        ThemePermissionsDispatcher.updateWithCheck(Theme.this,apkUri,versionName);
                                    }
                                });
                            }
                        };
                        handler.postDelayed(runnable,3000);
                    }
                }else {
                    Toast.makeText(Theme.this,httpBean.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                Toast.makeText(Theme.this,msg, Toast.LENGTH_SHORT).show();
            }
        }));
    }
    public static int getLocalVersionCode(Context context) {
        int localVsionCode = 1;
        PackageInfo pi;
        try {
            pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            localVsionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVsionCode;
    }
    //获取系统处于前台的activity的名字
    public static String getTopActivityName(Context context) {
        String topActivityClassName = null;
        android.app.ActivityManager activityManager = (android.app.ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activityManager
                .getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            topActivityClassName = f.getClassName();
        }
        return topActivityClassName;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("Theme");
        handler.removeCallbacks(runnable);
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
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        themeBottomBar.getTabWithId(R.id.tab_news).setBadgeCount(mIMKit.getConversationService().getAllUnreadCount());
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
   @NeedsPermission({Manifest.permission.CALL_PHONE})
    void call(){

    }
    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void update(String apkUri, String versionName){
        new NewVersion(this).downLoadNewApk(apkUri,versionName);
    }
    @OnShowRationale({Manifest.permission.CALL_PHONE})
    void ShowRationaleFoCall(PermissionRequest request){
        request.proceed();
    }
    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void ShowRationaleFoUpdate(PermissionRequest request){
        request.proceed();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ThemePermissionsDispatcher.onRequestPermissionsResult(this,requestCode,grantResults);
    }
}
