package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddVisitManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddVisitModel;

import java.util.Map;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddVisitPresenter implements AddVisitManage.Presenter{
    private AddVisitManage.View view;
    private AddVisitModel model;

    public AddVisitPresenter(AddVisitManage.View view, Context context) {
        this.view = view;
        model=new AddVisitModel(context);
    }

    @Override
    public void addVisit(Map<String,String> map, LoadingDialog dialog) {
        model.addVisit(map,dialog, new Callback<HttpBean<VisitBean>>() {
            @Override
            public void get(HttpBean<VisitBean> httpBean) {
                view.addVisit(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editVisit(Map<String,String> map,LoadingDialog dialog) {
        model.editVisit(map,dialog, new Callback<HttpBean<VisitBean>>() {
            @Override
            public void get(HttpBean<VisitBean> httpBean) {
                view.editVisit(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void getListForVisit(String token) {
        model.getListForVisit(token,new Callback<HttpBean<InfoAddVisitBean>>() {
            @Override
            public void get(HttpBean<InfoAddVisitBean> httpBean) {
                view.getListForVisit(httpBean);
            }
        });
    }
}
