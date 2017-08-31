package com.dhitoshi.xfrs.huixiaobao.app;
import android.app.Application;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.wxlib.util.SysUtil;
/**
 * Created by dxs on 2017/8/31.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        final String APP_KEY = "23015524";
        SysUtil.setApplication(this);
        if(SysUtil.isTCMSServiceProcess(this)){
            return;
        }
        if(SysUtil.isMainProcess()){
            YWAPI.init(this, APP_KEY);
        }
    }
}
