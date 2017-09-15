package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddRelationManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddRelationModel;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddRelationPresenter implements AddRelationManage.Presenter{
    private AddRelationManage.View view;
    private AddRelationModel model;

    public AddRelationPresenter(AddRelationManage.View view) {
        this.view = view;
        model=new AddRelationModel();
    }

    @Override
    public void addRelation(AddRelationBean addRelationBean) {
        model.addRelation(addRelationBean, new Callback<HttpBean<RelationBean>>() {
            @Override
            public void get(HttpBean<RelationBean> httpBean) {
                view.addRelation(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editRelation(AddRelationBean addRelationBean) {
        model.editRelation(addRelationBean, new Callback<HttpBean<RelationBean>>() {
            @Override
            public void get(HttpBean<RelationBean> httpBean) {
                view.editRelation(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void getListForRelation() {
        model.getListForRelation(new Callback<HttpBean<InfoAddRelationBean>>() {
            @Override
            public void get(HttpBean<InfoAddRelationBean> httpBean) {
                view.getListForRelation(httpBean);
            }
        });
    }
}
