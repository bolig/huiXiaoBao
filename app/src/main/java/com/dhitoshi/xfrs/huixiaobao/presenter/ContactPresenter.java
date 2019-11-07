package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddUserManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ContactManage;
import com.dhitoshi.xfrs.huixiaobao.model.AddUserModel;
import com.dhitoshi.xfrs.huixiaobao.model.ContactModel;

import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/16.
 */

public class ContactPresenter implements ContactManage.Presenter{
    private ContactManage.View view;
    private ContactModel model;
    public ContactPresenter(ContactManage.View view, Context context) {
        this.view = view;
        model=new ContactModel(context);
    }


    @Override
    public void addFast(Map<String, String> map, LoadingDialog dialog) {
        model.addFast(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.addFast(httpBean.getStatus().getMsg());
            }
        });
    }

}
