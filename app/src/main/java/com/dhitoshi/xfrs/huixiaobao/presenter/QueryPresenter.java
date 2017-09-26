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

    @Override
    public void getSearchOne(Map<String, String> map, LoadingDialog dialog) {
        model.getSearchOne(map, dialog, new Callback<HttpBean<PageBean<ClientBean>>>() {
            @Override
            public void get(HttpBean<PageBean<ClientBean>> httpBean) {
                view.getSearchOne(httpBean);
            }
        });
    }

    @Override
    public void getSearchTwo(Map<String, String> map, LoadingDialog dialog) {
        model.getSearchTwo(map, dialog, new Callback<HttpBean<PageBean<SpendBean>>>() {
            @Override
            public void get(HttpBean<PageBean<SpendBean>> httpBean) {
                view.getSearchTwo(httpBean);
            }
        });
    }

    @Override
    public void getSearchThree(Map<String, String> map, LoadingDialog dialog) {
        model.getSearchThree(map, dialog, new Callback<HttpBean<PageBean<VisitBean>>>() {
            @Override
            public void get(HttpBean<PageBean<VisitBean>> httpBean) {
                view.getSearchThree(httpBean);
            }
        });
    }

    @Override
    public void getSearchFour(Map<String, String> map, LoadingDialog dialog) {
        model.getSearchFour(map, dialog, new Callback<HttpBean<PageBean<RelationBean>>>() {
            @Override
            public void get(HttpBean<PageBean<RelationBean>> httpBean) {
                view.getSearchFour(httpBean);
            }
        });
    }

    @Override
    public void getSearchFive(Map<String, String> map, LoadingDialog dialog) {
        model.getSearchFive(map, dialog, new Callback<HttpBean<PageBean<GiftBean>>>() {
            @Override
            public void get(HttpBean<PageBean<GiftBean>> httpBean) {
                view.getSearchFive(httpBean);
            }
        });
    }

    @Override
    public void getSearchSix(Map<String, String> map, LoadingDialog dialog) {
        model.getSearchSix(map, dialog, new Callback<HttpBean<PageBean<MeetBean>>>() {
            @Override
            public void get(HttpBean<PageBean<MeetBean>> httpBean) {
                view.getSearchSix(httpBean);
            }
        });
    }
}
