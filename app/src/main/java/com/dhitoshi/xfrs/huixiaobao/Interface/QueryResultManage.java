package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.QueryResultBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;

import java.util.Map;

/**
 * Created by dxs on 2017/9/26.
 */
public interface QueryResultManage {
    interface View{
        void getSearchOne(HttpBean<HttpPageBean<QueryResultBean<ClientBean>>> httpBean);
        void getSearchTwo(HttpBean<HttpPageBean<QueryResultBean<SpendBean>>> httpBean);
        void getSearchThree(HttpBean<HttpPageBean<QueryResultBean<VisitBean>>> httpBean);
        void getSearchFour(HttpBean<HttpPageBean<QueryResultBean<RelationBean>>> httpBean);
        void getSearchFive(HttpBean<HttpPageBean<QueryResultBean<GiftBean>>> httpBean);
        void getSearchSix(HttpBean<HttpPageBean<QueryResultBean<MeetBean>>> httpBean);
    }
    interface Model{
        void getSearchOne(Map<String, String> map, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<QueryResultBean<ClientBean>>>> callback);
        void getSearchTwo(Map<String, String> map, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<QueryResultBean<SpendBean>>>> callback);
        void getSearchThree(Map<String, String> map, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<QueryResultBean<VisitBean>>>> callback);
        void getSearchFour(Map<String, String> map,SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<QueryResultBean<RelationBean>>>> callback);
        void getSearchFive(Map<String, String> map, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<QueryResultBean<GiftBean>>>> callback);
        void getSearchSix(Map<String, String> map, SmartRefreshLayout smartRefreshLayout, Callback<HttpBean<HttpPageBean<QueryResultBean<MeetBean>>>> callback);
    }
    interface Presenter{
        void getSearchOne(Map<String, String> map, SmartRefreshLayout smartRefreshLayout);
        void getSearchTwo(Map<String, String> map, SmartRefreshLayout smartRefreshLayout);
        void getSearchThree(Map<String, String> map,SmartRefreshLayout smartRefreshLayout);
        void getSearchFour(Map<String, String> map,SmartRefreshLayout smartRefreshLayout);
        void getSearchFive(Map<String, String> map,SmartRefreshLayout smartRefreshLayout);
        void getSearchSix(Map<String, String> map, SmartRefreshLayout smartRefreshLayout);
    }
}
