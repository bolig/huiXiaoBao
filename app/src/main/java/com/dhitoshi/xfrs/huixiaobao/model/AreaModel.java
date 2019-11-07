package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.List;

/**
 * Created by dxs on 2017/9/9.
 */

public class AreaModel implements AreaManage.Model {
    private Context context;
    public AreaModel(Context context) {
        this.context = context;
    }
    @Override
    public void getAreaLists(final String token, final Callback<HttpBean<List<AreaBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getAreaLists(token),new CommonObserver(new HttpResult<HttpBean<List<AreaBean>>>() {
            @Override
            public void OnSuccess(HttpBean<List<AreaBean>> httpBean) {
                if (httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                           getAreaLists(token, callback);
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
