package com.dhitoshi.xfrs.huixiaobao.presenter;

import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ResourceBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ResourceManage;
import com.dhitoshi.xfrs.huixiaobao.model.ResourceModel;


/**
 * Created by dxs on 2017/9/6.
 */
public class ResourcePresenter implements ResourceManage.Presenter{
    private ResourceManage.View view;
    private ResourceModel resourceModel;
    public ResourcePresenter(ResourceManage.View view, Context context) {
        this.view = view;
        resourceModel=new ResourceModel(context);
    }

    @Override
    public void getMyResource(String token, SmartRefreshLayout refreshLayout) {
        resourceModel.getMyResource(token,refreshLayout, new Callback<HttpBean<ResourceBean>>() {
            @Override
            public void get(HttpBean<ResourceBean> httpBean) {
                view.getMyResource(httpBean);
            }
        });
    }
}
