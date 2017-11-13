package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.VideoBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingVideoManage;
import com.dhitoshi.xfrs.huixiaobao.model.MeetingVideoModel;

/**
 * Created by dxs on 2017/9/6.
 */
public class MeetingVideoPresenter implements MeetingVideoManage.Presenter{
    private MeetingVideoManage.View view;
    private MeetingVideoModel documentModel;
    public MeetingVideoPresenter(MeetingVideoManage.View view, Context context) {
        this.view = view;
        documentModel=new MeetingVideoModel(context);
    }
    @Override
    public void getVideoList(String token, String meeting_id, String page, SmartRefreshLayout refreshLayout) {
        documentModel.getVideoList(token,meeting_id,page,refreshLayout, new Callback<HttpPageBeanTwo<VideoBean>>() {
            @Override
            public void get(HttpPageBeanTwo<VideoBean> httpPageBeanTwo ) {
                view.getVideoList(httpPageBeanTwo);
            }
        });
    }
}
