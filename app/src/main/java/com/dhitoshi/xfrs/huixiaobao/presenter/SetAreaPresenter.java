package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SetAreaManage;
import com.dhitoshi.xfrs.huixiaobao.model.AreaModel;
import com.dhitoshi.xfrs.huixiaobao.model.SetAreaModel;

import java.util.List;
/**
 * Created by dxs on 2017/9/6.
 */
public class SetAreaPresenter implements SetAreaManage.Presenter{
    private SetAreaManage.View view;
    private SetAreaModel setAreaModel;
    public SetAreaPresenter(SetAreaManage.View view, Context context) {
        this.view = view;
        setAreaModel=new SetAreaModel(context);
    }
    @Override
    public void getAreaLists(SmartRefreshLayout smartRefreshLayout) {
        setAreaModel.getAreaLists(smartRefreshLayout, new Callback<HttpBean<List<AreaBean>>>() {
            @Override
            public void get(HttpBean<List<AreaBean>> httpBean) {
                view.getAreaLists(httpBean);
            }
        });
    }
    @Override
    public void deleteArea(String id, String token, LoadingDialog dialog) {
        setAreaModel.deleteArea(id, token, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.deleteArea(httpBean.getStatus().getMsg());
            }
        });
    }
}
