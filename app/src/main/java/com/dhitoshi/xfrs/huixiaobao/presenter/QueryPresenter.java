package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoQuery;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryManage;
import com.dhitoshi.xfrs.huixiaobao.model.QueryModel;

/**
 * Created by dxs on 2017/9/26.
 */

public class QueryPresenter implements QueryManage.Presenter{
    private QueryManage.View view;
    private QueryModel model;

    public QueryPresenter(QueryManage.View view, Context context) {
        this.view = view;
        model=new QueryModel(context);
    }

    @Override
    public void getListForSearch(String token) {
        model.getListForSearch(token,new Callback<HttpBean<InfoQuery>>() {
            @Override
            public void get(HttpBean<InfoQuery> httpBean) {
                view.getListForSearch(httpBean);
            }
        });
    }

}
