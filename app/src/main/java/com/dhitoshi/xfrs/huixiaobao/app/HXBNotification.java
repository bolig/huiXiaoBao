package com.dhitoshi.xfrs.huixiaobao.app;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMNotification;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWMessage;
import com.alibaba.mobileim.conversation.YWP2PConversationBody;
import com.alibaba.mobileim.conversation.YWTribeConversationBody;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.Chat;
import com.dhitoshi.xfrs.huixiaobao.view.TribeChat;
/**
 * Created by dxs on 2017/10/17.
 */
public class HXBNotification extends IMNotification{
    public HXBNotification(Pointcut pointcut) {
        super(pointcut);
    }
    @Override
    public int getNotificationIconResID() {
        return R.mipmap.logo;
    }
    @Override
    public Intent getCustomNotificationIntent(YWConversation conversation, YWMessage message, int totalUnReadCount) {
        YWConversationType type = conversation.getConversationType();
        Intent  intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (type == YWConversationType.P2P){
            //TODO 单聊会话点击事件
            IYWContact listener = ((YWP2PConversationBody)conversation.getConversationBody()).getContact();
            if(TextUtils.isEmpty(listener.getShowName())){
                String userId = SharedPreferencesUtil.Obtain(MyApplication.getContext(), "account", "").toString().split("@")[0];
                YWIMKit mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
                IYWContact contact = mIMKit.getContactService().getContactProfileInfo(listener.getUserId(), listener.getAppKey());
                intent.putExtra("name",contact.getShowName());
            }else{
                intent.putExtra("name",listener.getUserId());
            }
            intent.putExtra("target",listener.getUserId());
            intent.setClass(MyApplication.getContext(), Chat.class);
        } else if (type == YWConversationType.Tribe){
            //TODO 群会话点击事件
            if (conversation.getConversationBody() instanceof YWTribeConversationBody) {
                YWTribe tribe = ((YWTribeConversationBody) conversation.getConversationBody()).getTribe();
                intent.putExtra("target",tribe.getTribeId());
                intent.putExtra("name",tribe.getTribeName());
                intent.setClass(MyApplication.getContext(), TribeChat.class);
            }
        }
        return intent;
    }
}
