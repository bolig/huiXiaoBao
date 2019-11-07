package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.PlayCountBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VideoBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingVideoManage;
import com.dhitoshi.xfrs.huixiaobao.model.MeetingVideoModel;

import java.util.Map;

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
    public void getVideoList(Map<String, String> map, SmartRefreshLayout refreshLayout) {
        documentModel.getVideoList(map,refreshLayout, new Callback<HttpPageBeanTwo<VideoBean>>() {
            @Override
            public void get(HttpPageBeanTwo<VideoBean> httpPageBeanTwo ) {
                view.getVideoList(httpPageBeanTwo);
            }
        });
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

    @Override
    public void setPlayCount(String token, String video_id) {
        documentModel.setPlayCount(token, video_id, new Callback<HttpBean<PlayCountBean>>() {
            @Override
            public void get(HttpBean<PlayCountBean> httpBean) {
                view.setPlayCount(httpBean.getData());
            }
        });
    }

    @Override
    public void getPlayCount(String token, String video_id) {
        documentModel.getPlayCount(token, video_id, new Callback<HttpBean<PlayCountBean>>() {
            @Override
            public void get(HttpBean<PlayCountBean> httpBean) {
                view.getPlayCount(httpBean.getData());
            }
        });
    }
}
