package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ExpiredManage;
import com.dhitoshi.xfrs.huixiaobao.model.ExpiredModel;

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
        model.getMeetForPast(token, page, smartRefreshLayout, new Callback<HttpBean<HttpPageBean<ApplyMeetBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<ApplyMeetBean>> httpBean) {
                view.getMeetForPast(httpBean);
            }
        });
    }
}
