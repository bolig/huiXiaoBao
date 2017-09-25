package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ExpiredManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.OnGoingManage;
import com.dhitoshi.xfrs.huixiaobao.model.ExpiredModel;
import com.dhitoshi.xfrs.huixiaobao.model.OnGoingModel;

/**
 * Created by dxs on 2017/9/16.
 */

public class ExpiredPresenter implements ExpiredManage.Presenter{
    private ExpiredManage.View view;
    private ExpiredModel model;
    public ExpiredPresenter(ExpiredManage.View view, Context context) {
        this.view = view;
        model=new ExpiredModel(context);
    }
    @Override
    public void getMeetForPast(String token, String page, SmartRefreshLayout smartRefreshLayout) {
        model.getMeetForPast(token, page, smartRefreshLayout, new Callback<HttpBean<PageBean<ApplyMeetBean>>>() {
            @Override
            public void get(HttpBean<PageBean<ApplyMeetBean>> httpBean) {
                view.getMeetForPast(httpBean);
            }
        });
    }
}
