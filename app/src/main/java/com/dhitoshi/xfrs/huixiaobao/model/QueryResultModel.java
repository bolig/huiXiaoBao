package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

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
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryResultManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.Map;

/**
 * Created by dxs on 2017/9/26.
 */
public class QueryResultModel implements QueryResultManage.Model{
    private Context context;
    public QueryResultModel(Context context) {
        this.context = context;
    }
    @Override
    public void getSearchOne(final Map<String, String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<HttpPageBean<QueryResultBean<ClientBean>>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchOne(map),new CommonObserver(new HttpResult<HttpBean<HttpPageBean<QueryResultBean<ClientBean>>>>() {
            @Override
            public void OnSuccess(HttpBean<HttpPageBean<QueryResultBean<ClientBean>>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                           getSearchOne(map, smartRefreshLayout, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchTwo(final Map<String, String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<HttpPageBean<QueryResultBean<SpendBean>>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchTwo(map),new CommonObserver(new HttpResult<HttpBean<HttpPageBean<QueryResultBean<SpendBean>>>>() {
            @Override
            public void OnSuccess(HttpBean<HttpPageBean<QueryResultBean<SpendBean>>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchTwo(map, smartRefreshLayout, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchThree(final Map<String, String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<HttpPageBean<QueryResultBean<VisitBean>>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchThree(map),new CommonObserver(new HttpResult<HttpBean<HttpPageBean<QueryResultBean<VisitBean>>>>() {
            @Override
            public void OnSuccess(HttpBean<HttpPageBean<QueryResultBean<VisitBean>>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchThree(map, smartRefreshLayout, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchFour(final Map<String, String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<HttpPageBean<QueryResultBean<RelationBean>>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchFour(map),new CommonObserver(new HttpResult<HttpBean<HttpPageBean<QueryResultBean<RelationBean>>>>() {
            @Override
            public void OnSuccess(HttpBean<HttpPageBean<QueryResultBean<RelationBean>>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchFour(map, smartRefreshLayout, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchFive(final Map<String, String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<HttpPageBean<QueryResultBean<GiftBean>>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchFive(map),new CommonObserver(new HttpResult<HttpBean<HttpPageBean<QueryResultBean<GiftBean>>>>() {
            @Override
            public void OnSuccess(HttpBean<HttpPageBean<QueryResultBean<GiftBean>>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchFive(map, smartRefreshLayout, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchSix(final Map<String, String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<HttpPageBean<QueryResultBean<MeetBean>>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchSix(map),new CommonObserver(new HttpResult<HttpBean<HttpPageBean<QueryResultBean<MeetBean>>>>() {
            @Override
            public void OnSuccess(HttpBean<HttpPageBean<QueryResultBean<MeetBean>>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchSix(map, smartRefreshLayout, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
