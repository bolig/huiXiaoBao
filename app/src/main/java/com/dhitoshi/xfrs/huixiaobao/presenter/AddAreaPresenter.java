package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddAreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddAreaModel;
import com.dhitoshi.xfrs.huixiaobao.model.AreaModel;

import java.util.List;

/**
 * Created by dxs on 2017/9/6.
 */
public class AddAreaPresenter implements AddAreaManage.Presenter{
    private AddAreaManage.View view;
    private AddAreaModel addAreaModel;
    public AddAreaPresenter(AddAreaManage.View view, Context context) {
        this.view = view;
        addAreaModel=new AddAreaModel(context);
    }

    @Override
    public void getAreaLists(SmartRefreshLayout smartRefreshLayout) {
        addAreaModel.getAreaLists(smartRefreshLayout,new Callback<HttpBean<List<AreaBean>>>() {
            @Override
            public void get(HttpBean<List<AreaBean>> httpBean) {
                view.getAreaLists(httpBean);
            }
        });
    }
}
