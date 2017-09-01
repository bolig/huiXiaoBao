package com.dhitoshi.xfrs.huixiaobao.app;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMChattingPageUI;
import com.alibaba.mobileim.conversation.YWConversation;
import com.dhitoshi.xfrs.huixiaobao.R;
/**
 * Created by dxs on 2017/9/1.
 */
public class ChattingCustomAdviceSample extends IMChattingPageUI {

    public ChattingCustomAdviceSample(Pointcut pointcut) {
        super(pointcut);
    }
    public View getCustomTitleView(final Fragment fragment, final Context context, LayoutInflater inflater, YWConversation conversation) {
        View view = inflater.inflate(R.layout.toolbar, null);
        return view;
    }
}
