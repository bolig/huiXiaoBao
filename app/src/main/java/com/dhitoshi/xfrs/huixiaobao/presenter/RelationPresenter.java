package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.RelationManage;
import com.dhitoshi.xfrs.huixiaobao.model.RelationModel;
/**
 * Created by dxs on 2017/9/6.
 */
public class RelationPresenter implements RelationManage.Prsenter{
    private RelationManage.View view;
    private RelationModel relationModel;
    public RelationPresenter(RelationManage.View view, Context context) {
        this.view = view;
        relationModel=new RelationModel(context);
    }
    @Override
    public void getRelationLists(String token,String userid, String page, SmartRefreshLayout smartRefreshLayout) {
        relationModel.getRelationLists(token,userid, page,smartRefreshLayout, new Callback<HttpBean<HttpPageBean<RelationBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<RelationBean>> httpBean) {
                view.getRelationLists(httpBean.getData());
            }
        });
    }
}
