package com.dhitoshi.xfrs.huixiaobao.model;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

import java.util.Map;

/**
 * Created by dxs on 2017/9/12.
 */

public class LoginModel implements LoginManage.Model {
    @Override
    public void login(Map<String, String> map, final Callback<HttpBean<UserBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().login(map),new CommonObserver(new HttpResult<HttpBean<UserBean>>() {
            @Override
            public void OnSuccess(HttpBean<UserBean> httpBean) {
                    callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
