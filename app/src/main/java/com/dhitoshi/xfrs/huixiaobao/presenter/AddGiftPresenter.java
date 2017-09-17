package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddGiftManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddGiftModel;

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
    public void addGift(AddGiftBean addGiftBean, LoadingDialog dialog) {
        model.addGift(addGiftBean,dialog, new Callback<HttpBean<GiftBean>>() {
            @Override
            public void get(HttpBean<GiftBean> httpBean) {
                view.addGift(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editGift(AddGiftBean addGiftBean, LoadingDialog dialog) {
        model.editGift(addGiftBean,dialog, new Callback<HttpBean<GiftBean>>() {
            @Override
            public void get(HttpBean<GiftBean> httpBean) {
                view.editGift(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void getListForGift() {
        model.getListForGift(new Callback<HttpBean<InfoAddGiftBean>>() {
            @Override
            public void get(HttpBean<InfoAddGiftBean> httpBean) {
                view.getListForGift(httpBean);
            }
        });
    }
}
