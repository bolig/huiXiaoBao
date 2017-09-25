package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.HasHeldManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.OnGoingManage;
import com.dhitoshi.xfrs.huixiaobao.model.HasHeldModel;
import com.dhitoshi.xfrs.huixiaobao.model.OnGoingModel;

/**
 * Created by dxs on 2017/9/16.
 */

public class OnGoingPresenter implements OnGoingManage.Presenter{
    private OnGoingManage.View view;
    private OnGoingModel model;
    public OnGoingPresenter(OnGoingManage.View view, Context context) {
        this.view = view;
        model=new OnGoingModel(context);
    }
    @Override
    public void getMeetForNow(String token, String page, SmartRefreshLayout smartRefreshLayout) {
        model.getMeetForNow(token, page, smartRefreshLayout, new Callback<HttpBean<PageBean<ApplyMeetBean>>>() {
            @Override
            public void get(HttpBean<PageBean<ApplyMeetBean>> httpBean) {
                view.getMeetForNow(httpBean);
            }
        });
    }
}
