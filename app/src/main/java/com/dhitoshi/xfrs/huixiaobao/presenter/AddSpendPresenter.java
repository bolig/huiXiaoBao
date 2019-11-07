package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddSpendManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddSpendModel;

import java.util.Map;

/**
 * Created by dxs on 2017/9/14.
 */

public class AddSpendPresenter implements AddSpendManage.Presenter {
    private AddSpendManage.View view;
    private AddSpendModel model;
    public AddSpendPresenter(AddSpendManage.View view, Context context) {
        this.view = view;
        model=new AddSpendModel(context);
    }
    @Override
    public void addSpend(Map<String,String> map, LoadingDialog dialog) {
        model.addSpend(map,dialog, new Callback<HttpBean<SpendBean>>() {
            @Override
            public void get(HttpBean<SpendBean> httpBean) {
                view.addSpend(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void editSpend(Map<String,String> map,LoadingDialog dialog) {
        model.editSpend(map,dialog, new Callback<HttpBean<SpendBean>>() {
            @Override
            public void get(HttpBean<SpendBean> httpBean) {
                view.editSpend(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void getListForSpending(String token) {
        model.getListForSpending(token,new Callback<HttpBean<InfoAddSpendBean>>() {
            @Override
            public void get(HttpBean<InfoAddSpendBean> httpBean) {
                view.getListForSpending(httpBean);
            }
        });
    }
}
