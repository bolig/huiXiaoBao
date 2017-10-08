package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddMeetingClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddMeetingClientModel;

import java.util.Map;

/**
 * Created by dxs on 2017/9/15.
 */
public class AddMeetingClientPresenter implements AddMeetingClientManage.Presenter{
    private AddMeetingClientManage.View view;
    private AddMeetingClientModel model;
    public AddMeetingClientPresenter(AddMeetingClientManage.View view, Context context) {
        this.view = view;
        model=new AddMeetingClientModel(context);
    }
    @Override
    public void addCustomer(Map<String, String> map, LoadingDialog dialog) {
        model.addCustomer(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.addCustomer(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editCustomer(Map<String, String> map, LoadingDialog dialog) {
        model.editCustomer(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.editCustomer(httpBean.getStatus().getMsg());
            }
        });
    }
}
