package com.dhitoshi.xfrs.huixiaobao.app;
import android.support.v4.app.Fragment;
import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMChattingPageUI;
import com.alibaba.mobileim.conversation.YWConversation;

/**
 * Created by dxs on 2017/9/1.
 */
public class ChattingCustomAdviceSample extends IMChattingPageUI {

    public ChattingCustomAdviceSample(Pointcut pointcut) {
        super(pointcut);
    }
    @Override
    public boolean needHideTitleView(Fragment fragment, YWConversation conversation) {
        return true;
    }
}
