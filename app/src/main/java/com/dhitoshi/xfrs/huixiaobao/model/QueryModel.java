package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

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
    public void getListForSearch(String token,final Callback<HttpBean<InfoQuery>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForSearch(token),new CommonObserver(new HttpResult<HttpBean<InfoQuery>>() {
            @Override
            public void OnSuccess(HttpBean<InfoQuery> httpBean) {
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
    public void getSearchOne(final Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<PageBean<ClientBean>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchOne(map),new CommonObserver(new HttpResult<HttpBean<PageBean<ClientBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<ClientBean>> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                           getSearchOne(map, dialog, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchTwo(final Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<PageBean<SpendBean>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchTwo(map),new CommonObserver(new HttpResult<HttpBean<PageBean<SpendBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<SpendBean>> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchTwo(map, dialog, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchThree(final Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<PageBean<VisitBean>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchThree(map),new CommonObserver(new HttpResult<HttpBean<PageBean<VisitBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<VisitBean>> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchThree(map, dialog, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchFour(final Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<PageBean<RelationBean>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchFour(map),new CommonObserver(new HttpResult<HttpBean<PageBean<RelationBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<RelationBean>> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchFour(map, dialog, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchFive(final Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<PageBean<GiftBean>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchFive(map),new CommonObserver(new HttpResult<HttpBean<PageBean<GiftBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<GiftBean>> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchFive(map, dialog, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSearchSix(final Map<String, String> map, final LoadingDialog dialog, final Callback<HttpBean<PageBean<MeetBean>>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSearchSix(map),new CommonObserver(new HttpResult<HttpBean<PageBean<MeetBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<MeetBean>> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            getSearchSix(map, dialog, callback);
                        }
                    });
                }else {
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
