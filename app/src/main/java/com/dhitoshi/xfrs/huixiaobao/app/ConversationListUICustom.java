package com.dhitoshi.xfrs.huixiaobao.app;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListUI;
import com.alibaba.mobileim.conversation.YWConversation;
import com.dhitoshi.xfrs.huixiaobao.R;
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
        View view= LayoutInflater.from(context).inflate(R.layout.empty,null);
        return view;
    }
    @Override
    public int getTribeConversationHead(Fragment fragment, YWConversation conversation) {
        return R.mipmap.head;
    }
}
