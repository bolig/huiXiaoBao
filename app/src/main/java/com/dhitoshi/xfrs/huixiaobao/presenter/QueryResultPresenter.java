package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.QueryResultBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryResultManage;
import com.dhitoshi.xfrs.huixiaobao.model.QueryResultModel;

import java.util.Map;

/**
 * Created by dxs on 2017/9/26.
 */

public class QueryResultPresenter implements QueryResultManage.Presenter{
    private QueryResultManage.View view;
    private QueryResultModel model;

    public QueryResultPresenter(QueryResultManage.View view, Context context) {
        this.view = view;
        model=new QueryResultModel(context);
    }
    @Override
    public void getSearchOne(Map<String, String> map, SmartRefreshLayout smartRefreshLayout) {
        model.getSearchOne(map, smartRefreshLayout, new Callback<HttpBean<PageBean<QueryResultBean<ClientBean>>>>() {
            @Override
            public void get(HttpBean<PageBean<QueryResultBean<ClientBean>>> httpBean) {
                view.getSearchOne(httpBean);
            }
        });
    }

    @Override
    public void getSearchTwo(Map<String, String> map,SmartRefreshLayout smartRefreshLayout) {
        model.getSearchTwo(map, smartRefreshLayout, new Callback<HttpBean<PageBean<QueryResultBean<SpendBean>>>>() {
            @Override
            public void get(HttpBean<PageBean<QueryResultBean<SpendBean>>> httpBean) {
                view.getSearchTwo(httpBean);
            }
        });
    }

    @Override
    public void getSearchThree(Map<String, String> map,SmartRefreshLayout smartRefreshLayout) {
        model.getSearchThree(map, smartRefreshLayout, new Callback<HttpBean<PageBean<QueryResultBean<VisitBean>>>>() {
            @Override
            public void get(HttpBean<PageBean<QueryResultBean<VisitBean>>> httpBean) {
                view.getSearchThree(httpBean);
            }
        });
    }

    @Override
    public void getSearchFour(Map<String, String> map, SmartRefreshLayout smartRefreshLayout) {
        model.getSearchFour(map, smartRefreshLayout, new Callback<HttpBean<PageBean<QueryResultBean<RelationBean>>>>() {
            @Override
            public void get(HttpBean<PageBean<QueryResultBean<RelationBean>>> httpBean) {
                view.getSearchFour(httpBean);
            }
        });
    }

    @Override
    public void getSearchFive(Map<String, String> map, SmartRefreshLayout smartRefreshLayout) {
        model.getSearchFive(map, smartRefreshLayout, new Callback<HttpBean<PageBean<QueryResultBean<GiftBean>>>>() {
            @Override
            public void get(HttpBean<PageBean<QueryResultBean<GiftBean>>> httpBean) {
                view.getSearchFive(httpBean);
            }
        });
    }

    @Override
    public void getSearchSix(Map<String, String> map, SmartRefreshLayout smartRefreshLayout) {
        model.getSearchSix(map, smartRefreshLayout, new Callback<HttpBean<PageBean<QueryResultBean<MeetBean>>>>() {
            @Override
            public void get(HttpBean<PageBean<QueryResultBean<MeetBean>>> httpBean) {
                view.getSearchSix(httpBean);
            }
        });
    }
}
