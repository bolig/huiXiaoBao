package com.dhitoshi.xfrs.huixiaobao.presenter;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddVisitManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddVisitModel;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddVisitPresenter implements AddVisitManage.Presenter{
    private AddVisitManage.View view;
    private AddVisitModel model;

    public AddVisitPresenter(AddVisitManage.View view) {
        this.view = view;
        model=new AddVisitModel();
    }

    @Override
    public void addVisit(AddVisitBean addVisitBean) {
        model.addVisit(addVisitBean, new Callback<HttpBean<VisitBean>>() {
            @Override
            public void get(HttpBean<VisitBean> httpBean) {
                view.addVisit(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editVisit(AddVisitBean addVisitBean) {
        model.editVisit(addVisitBean, new Callback<HttpBean<VisitBean>>() {
            @Override
            public void get(HttpBean<VisitBean> httpBean) {
                view.editVisit(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void getListForVisit() {
        model.getListForVisit(new Callback<HttpBean<InfoAddVisitBean>>() {
            @Override
            public void get(HttpBean<InfoAddVisitBean> httpBean) {
                view.getListForVisit(httpBean);
            }
        });
    }
}
