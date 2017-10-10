package com.dhitoshi.xfrs.huixiaobao.view;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VersionBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.UpdateDialog;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.List;
public class Launch extends AppCompatActivity {
    private Handler updateHandler=new Handler();
    private boolean isFirst=true;//是否第一次使用此应用
    private boolean isRemeber=false;//是否记住密码
    private static Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch);
        initViews();
    }
    private void initViews() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        isFirst= (boolean) SharedPreferencesUtil.Obtain(this,"isFirst",true);
        isRemeber= (boolean) SharedPreferencesUtil.Obtain(this,"isRemeber",false);
        if(isFirst){
            ToNext(Welcome.class,1000);
        } else{
            getVersion();
            Class c=isRemeber?Theme.class:Login.class;
            ToNext(c,1000);
        }
    }
    private void getVersion() {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getVersion(),new CommonObserver(new HttpResult<HttpBean<VersionBean>>() {
            @Override
            public void OnSuccess(final HttpBean<VersionBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    if(httpBean.getData().getCode()>getLocalVersionCode(Launch.this)){
                        updateHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String activityName=getTopActivityName(Launch.this);
                                activityName=activityName.substring(activityName.lastIndexOf(".")+1,activityName.length());
                                Activity localActivity= ActivityManagerUtil.getCurrentActivity(activityName);
                                UpdateDialog dialog=new UpdateDialog(localActivity);
                                dialog.setUpdateTitle("慧销宝"+httpBean.getData().getName()+"更新啦");
                                dialog.setContent(httpBean.getData().getDescription());
                                dialog.setVersionName(httpBean.getData().getName());
                                dialog.setUrl(httpBean.getData().getUrl());
                                dialog.show();
                            }
                        }, 3000);
                    }
                }else {
                    Toast.makeText(Launch.this,httpBean.getStatus().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                Toast.makeText(Launch.this,msg, Toast.LENGTH_SHORT).show();
            }
        }));
    }
    private void ToNext(final Class c, long time) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Launch.this, c));
                finish();
            }
        }, time);
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
}
