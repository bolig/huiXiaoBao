package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.Map;
/**
 * Created by dxs on 2017/9/26.
 */

public class QueryModel implements QueryManage.Model{
    private Context context;

    public QueryModel(Context context) {
        this.context = context;
    }

    @Override
    public void getListForSearch(String token,final Callback<HttpBean<Object>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForSearch(token),new CommonObserver(new HttpResult<HttpBean<Object>>() {
            @Override
            public void OnSuccess(HttpBean<Object> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getListForSearch(token,callback);
                        }
                    });
                }
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }

    @Override
    public void getSearchOne(Map<String, String> map, LoadingDialog dialog, Callback<HttpBean<PageBean<ClientBean>>> callback) {

    }

    @Override
    public void getSearchTwo(Map<String, String> map, LoadingDialog dialog, Callback<HttpBean<PageBean<SpendBean>>> callback) {

    }

    @Override
    public void getSearchThree(Map<String, String> map, LoadingDialog dialog, Callback<HttpBean<PageBean<VisitBean>>> callback) {

    }

    @Override
    public void getSearchFour(Map<String, String> map, LoadingDialog dialog, Callback<HttpBean<PageBean<RelationBean>>> callback) {

    }

    @Override
    public void getSearchFive(Map<String, String> map, LoadingDialog dialog, Callback<HttpBean<PageBean<GiftBean>>> callback) {

    }

    @Override
    public void getSearchSix(Map<String, String> map, LoadingDialog dialog, Callback<HttpBean<PageBean<MeetBean>>> callback) {

    }
}
