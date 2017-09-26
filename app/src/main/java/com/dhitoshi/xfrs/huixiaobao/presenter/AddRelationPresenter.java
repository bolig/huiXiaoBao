package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddRelationManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddRelationModel;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddRelationPresenter implements AddRelationManage.Presenter{
    private AddRelationManage.View view;
    private AddRelationModel model;

    public AddRelationPresenter(AddRelationManage.View view, Context context) {
        this.view = view;
        model=new AddRelationModel(context);
    }

    @Override
    public void addRelation(String token,AddRelationBean addRelationBean, LoadingDialog dialog) {
        model.addRelation(token,addRelationBean,dialog, new Callback<HttpBean<RelationBean>>() {
            @Override
            public void get(HttpBean<RelationBean> httpBean) {
                view.addRelation(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editRelation(String token,AddRelationBean addRelationBean,LoadingDialog dialog) {
        model.editRelation(token,addRelationBean,dialog, new Callback<HttpBean<RelationBean>>() {
            @Override
            public void get(HttpBean<RelationBean> httpBean) {
                view.editRelation(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void getListForRelation(String token) {
        model.getListForRelation(token,new Callback<HttpBean<InfoAddRelationBean>>() {
            @Override
            public void get(HttpBean<InfoAddRelationBean> httpBean) {
                view.getListForRelation(httpBean);
            }
        });
    }
}
