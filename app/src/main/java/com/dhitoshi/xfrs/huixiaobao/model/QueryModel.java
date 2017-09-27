package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoQuery;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

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
}
