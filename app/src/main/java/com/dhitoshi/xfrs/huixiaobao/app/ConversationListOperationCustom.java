package com.dhitoshi.xfrs.huixiaobao.app;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.alibaba.mobileim.aop.Pointcut;
import com.alibaba.mobileim.aop.custom.IMConversationListOperation;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWP2PConversationBody;
import com.dhitoshi.xfrs.huixiaobao.view.Chat;
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
            Log.e("'TAG","target---->>>"+listener.getUserId());
            Log.e("'TAG","name---->>>"+listener.getShowName()+"*****"+listener.getAvatarPath());
            intent.putExtra("target",listener.getUserId());
            intent.putExtra("name",listener.getShowName());
            fragment.getContext().startActivity(intent);
            return true;
        } else if (type == YWConversationType.Tribe){
            //TODO 群会话点击事件
            return true;
        }
        return false;
    }
}
