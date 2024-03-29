package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginManage;
import com.dhitoshi.xfrs.huixiaobao.model.LoginModel;
import java.util.Map;
/**
 * Created by dxs on 2017/9/12.
 */

public class LoginPresenter implements LoginManage.Presenter {
    private LoginManage.View view;
    private LoginModel loginModel;
    public LoginPresenter(LoginManage.View view, Context context) {
        this.view = view;
        loginModel=new LoginModel(context);
    }
    @Override
    public void login(Map<String, String> map, LoadingDialog dialog) {
        loginModel.login(map,dialog, new Callback<HttpBean<UserBean>>() {
            @Override
            public void get(HttpBean<UserBean> httpBean) {
                view.login(httpBean.getData());
            }
        });
    }
}
