package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.dhitoshi.xfrs.huixiaobao.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.alibaba.tcms.client.ClientRegInfo.APP_KEY;

//消息页面
public class News extends Fragment {
    private  YWIMKit mIMKit;
    Unbinder unbinder;
    public News() {
    }
    public static News newInstance() {
        News fragment = new News();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }
    private void init() {
        final String userid = "testpro1";
        mIMKit = YWAPI.getIMKitInstance(userid, APP_KEY);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.login, R.id.jump})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.jump:
                jump();
                break;
        }
    }

    private void jump() {
        final String target = "testpro2"; //消息接收者ID
        final String appkey = "23015524"; //消息接收者appKey
        Intent intent = mIMKit.getChattingActivityIntent(target, appkey);
        startActivity(intent);
    }

    private void login() {
        //开始登录
        String userid = "testpro1";
        String password = "taobao1234";
        IYWLoginService loginService = mIMKit.getLoginService();
        YWLoginParam loginParam = YWLoginParam.createLoginParam(userid, password);
        loginService.login(loginParam, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                Log.e("TAG","登录成功:");
            }

            @Override
            public void onProgress(int arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onError(int errCode, String description) {
                //如果登录失败，errCode为错误码,description是错误的具体描述信息
                Log.e("TAG","登录错误码:"+errCode);
                Log.e("TAG","登录失败:"+description);
            }
        });
    }
}
