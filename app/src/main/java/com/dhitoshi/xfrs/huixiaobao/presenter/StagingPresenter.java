package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.StagingManage;
import com.dhitoshi.xfrs.huixiaobao.model.StagingModel;
/**
 * Created by dxs on 2017/9/16.
 */

public class StagingPresenter implements StagingManage.Presenter{
    private StagingManage.View view;
    private StagingModel model;
    public StagingPresenter(StagingManage.View view, Context context) {
        this.view = view;
        model=new StagingModel(context);
    }
    @Override
    public void getListForNow(String token, String page, SmartRefreshLayout smartRefreshLayout) {
        model.getListForNow(token, page, smartRefreshLayout, new Callback<HttpBean<PageBean<OwnMeetBean>>>() {
            @Override
            public void get(HttpBean<PageBean<OwnMeetBean>> httpBean) {
                view.getListForNow(httpBean);
            }
        });
    }
}
