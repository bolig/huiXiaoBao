package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.HasHeldManage;
import com.dhitoshi.xfrs.huixiaobao.model.HasHeldModel;

/**
 * Created by dxs on 2017/9/16.
 */

public class HasHeldPresenter implements HasHeldManage.Presenter{
    private HasHeldManage.View view;
    private HasHeldModel model;
    public HasHeldPresenter(HasHeldManage.View view, Context context) {
        this.view = view;
        model=new HasHeldModel(context);
    }
    @Override
    public void getListForPast(String token, String page, SmartRefreshLayout smartRefreshLayout) {
        model.getListForPast(token, page, smartRefreshLayout, new Callback<HttpBean<PageBean<OwnMeetBean>>>() {
            @Override
            public void get(HttpBean<PageBean<OwnMeetBean>> httpBean) {
                view.getListForPast(httpBean);
            }
        });
    }
}
