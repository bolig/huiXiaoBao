package com.dhitoshi.xfrs.huixiaobao.app;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListOperation;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWP2PConversationBody;
import com.alibaba.mobileim.conversation.YWTribeConversationBody;
import com.alibaba.mobileim.gingko.model.tribe.YWTribe;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.Chat;
import com.dhitoshi.xfrs.huixiaobao.view.TribeChat;

/**
 * Created by dxs on 2017/10/11.
 */
class ConversationListOperationCustom extends IMConversationListOperation {
    public ConversationListOperationCustom(Pointcut pointcut) {
        super(pointcut);
    }
    @Override
    public boolean onItemClick(Fragment fragment, YWConversation conversation) {
        YWConversationType type = conversation.getConversationType();
        if (type == YWConversationType.P2P){
            //TODO 单聊会话点击事件
            Intent intent = new Intent(fragment.getContext(), Chat.class);
            IYWContact listener = ((YWP2PConversationBody)conversation.getConversationBody()).getContact();
            if(TextUtils.isEmpty(listener.getShowName())){
                String userId = SharedPreferencesUtil.Obtain(fragment.getContext(), "account", "").toString().split("@")[0];
                YWIMKit mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
                IYWContact contact = mIMKit.getContactService().getContactProfileInfo(listener.getUserId(), listener.getAppKey());
                intent.putExtra("name",contact.getShowName());
            }else{
                intent.putExtra("name",listener.getUserId());
            }
            intent.putExtra("target",listener.getUserId());
            fragment.getContext().startActivity(intent);
            return true;
        } else if (type == YWConversationType.Tribe){
            //TODO 群会话点击事件
            if (conversation.getConversationBody() instanceof YWTribeConversationBody) {
                YWTribe tribe = ((YWTribeConversationBody) conversation.getConversationBody()).getTribe();
                Intent intent = new Intent(fragment.getContext(), TribeChat.class);
                intent.putExtra("target",tribe.getTribeId());
                intent.putExtra("name",tribe.getTribeName());
                fragment.getContext().startActivity(intent);
            }
            return true;
        }
        return false;
    }

}
