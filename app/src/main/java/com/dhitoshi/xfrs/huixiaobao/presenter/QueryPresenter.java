package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoQuery;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryManage;
import com.dhitoshi.xfrs.huixiaobao.model.QueryModel;
import java.util.Map;

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
