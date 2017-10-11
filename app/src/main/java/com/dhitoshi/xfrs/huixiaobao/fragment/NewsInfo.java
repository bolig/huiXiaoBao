package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.conversation.IYWConversationListener;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.YWConversation;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NewsInfo extends Fragment {
    Unbinder unbinder;
    public NewsInfo() {

    }
    public static NewsInfo newInstance() {
        NewsInfo fragment = new NewsInfo();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }
    private void initViews() {
        String userId = SharedPreferencesUtil.Obtain(getContext(), "account", "").toString().split("@")[0];
        YWIMCore imCore = YWAPI.createIMCore(userId, "24607089");
        // 获取会话管理类
        IYWConversationService conversationService = imCore.getConversationService();
        // 添加会话列表变更监听
        conversationService.addConversationListener(new IYWConversationListener() {
            @Override
            public void onItemUpdated() {

            }
        });
        //获取最近会话列表
        List<YWConversation> conversations = conversationService.getConversationList();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
