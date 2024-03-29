package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddGiftManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddGiftModel;

import java.util.Map;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddGiftPresenter implements AddGiftManage.Presenter{
    private AddGiftManage.View view;
    private AddGiftModel model;

    public AddGiftPresenter(AddGiftManage.View view, Context context) {
        this.view = view;
        model=new AddGiftModel(context);
    }

    @Override
    public void addGift(Map<String,String> map, LoadingDialog dialog) {
        model.addGift(map,dialog, new Callback<HttpBean<GiftBean>>() {
            @Override
            public void get(HttpBean<GiftBean> httpBean) {
                view.addGift(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editGift(Map<String,String> map, LoadingDialog dialog) {
        model.editGift(map,dialog, new Callback<HttpBean<GiftBean>>() {
            @Override
            public void get(HttpBean<GiftBean> httpBean) {
                view.editGift(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void getListForGift(String token) {
        model.getListForGift(token,new Callback<HttpBean<InfoAddGiftBean>>() {
            @Override
            public void get(HttpBean<InfoAddGiftBean> httpBean) {
                view.getListForGift(httpBean);
            }
        });
    }
}
