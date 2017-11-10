package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ToHeldManage;
import com.dhitoshi.xfrs.huixiaobao.model.ToHeldModel;
/**
 * Created by dxs on 2017/9/16.
 */

public class ToHeldPresenter implements ToHeldManage.Presenter{
    private ToHeldManage.View view;
    private ToHeldModel model;
    public ToHeldPresenter(ToHeldManage.View view, Context context) {
        this.view = view;
        model=new ToHeldModel(context);
    }
    @Override
    public void getListForFuture(String token, String page, SmartRefreshLayout smartRefreshLayout) {
        model.getListForFuture(token, page, smartRefreshLayout, new Callback<HttpBean<HttpPageBean<OwnMeetBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<OwnMeetBean>> httpBean) {
                view.getListForFuture(httpBean);
            }
        });
    }
}
