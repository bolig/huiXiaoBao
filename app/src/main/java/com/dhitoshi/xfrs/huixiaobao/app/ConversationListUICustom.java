package com.dhitoshi.xfrs.huixiaobao.app;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;
/**
 * Created by dxs on 2017/10/11.
 */

class ConversationListUICustom extends IMConversationListUI {
    public ConversationListUICustom(Pointcut pointcut) {
        super(pointcut);
    }

    @Override
    public boolean needHideTitleView(Fragment fragment) {
        return true;
    }
    @Override
    public View getCustomEmptyViewInConversationUI(Context context) {
        TextView textView = new TextView(context);
        textView.setText("还没有会话哦，快去找人聊聊吧!");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(18);
        return textView;
    }

}
