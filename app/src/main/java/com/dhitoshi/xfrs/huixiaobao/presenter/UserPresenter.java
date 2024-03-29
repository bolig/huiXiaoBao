package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.UserManage;
import com.dhitoshi.xfrs.huixiaobao.model.UserModel;

/**
 * Created by dxs on 2017/9/16.
 */
public class UserPresenter implements UserManage.Presenter{
    private UserManage.View view;
    private UserModel model;
    public UserPresenter(UserManage.View view, Context context) {
        this.view = view;
        model=new UserModel(context);
    }
    @Override
    public void getUserList(String token,final String page, final SmartRefreshLayout smartRefreshLayout) {
        model.getUserList(token,page, smartRefreshLayout, new Callback<HttpBean<HttpPageBean<UserBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<UserBean>> httpBean) {
                view.getUserList(httpBean);
            }
        });
    }
}
