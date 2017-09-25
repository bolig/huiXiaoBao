package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddUserManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.FastSignManage;
import com.dhitoshi.xfrs.huixiaobao.model.AddUserModel;
import com.dhitoshi.xfrs.huixiaobao.model.FastSignModel;

import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/16.
 */

public class FastSignPresenter implements FastSignManage.Presenter{
    private FastSignManage.View view;
    private FastSignModel model;
    public FastSignPresenter(FastSignManage.View view, Context context) {
        this.view = view;
        model=new FastSignModel(context);
    }

    @Override
    public void fastSign(Map<String, String> map, LoadingDialog dialog) {
        model.fastSign(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.fastSign(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void getGroupLists(String token) {
        model.getGroupLists(token, new Callback<HttpBean<List<UserRole>>>() {
            @Override
            public void get(HttpBean<List<UserRole>> httpBean) {
                view.getGroupLists(httpBean);
            }
        });
    }
}
