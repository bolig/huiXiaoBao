package com.dhitoshi.xfrs.huixiaobao.app;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.aop.AdviceBinder;
import com.alibaba.mobileim.aop.PointCutEnum;
import com.alibaba.wxlib.util.SysUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.CrashHandler;

/**
 * Created by dxs on 2017/8/31.
 */
public class MyApplication extends Application {
    private static Context context;
    //提交
    @Override
    public void onCreate() {
        super.onCreate();
        final String APP_KEY = "24607089";
        SysUtil.setApplication(this);
        if(SysUtil.isTCMSServiceProcess(this)){
            return;
        }
        if(SysUtil.isMainProcess()){
            YWAPI.init(this, APP_KEY);
        }
        AdviceBinder.bindAdvice(PointCutEnum.CHATTING_FRAGMENT_UI_POINTCUT,ChattingCustomAdviceSample.class);
        AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_UI_POINTCUT, ConversationListUICustom.class);
        AdviceBinder.bindAdvice(PointCutEnum.CONVERSATION_FRAGMENT_OPERATION_POINTCUT, ConversationListOperationCustom.class);
        AdviceBinder.bindAdvice(PointCutEnum.NOTIFICATION_POINTCUT, HXBNotification.class);
    }
    @Override
    protected void attachBaseContext(Context base) {
        context=base;
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext() {
        return context;
    }
}
