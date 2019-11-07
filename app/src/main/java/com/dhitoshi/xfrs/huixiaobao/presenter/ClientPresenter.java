package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.model.ClientModel;

import java.util.Map;
/**
 * Created by dxs on 2017/9/6.
 */
public class ClientPresenter implements ClientManage.Prsenter{
    private ClientManage.View view;
    private ClientModel clientModel;
    public ClientPresenter(ClientManage.View view, Context context) {
        this.view = view;
        clientModel=new ClientModel(context);
    }
    @Override
    public void getClientList(Map<String,String> map, final SmartRefreshLayout smartRefreshLayout) {
        clientModel.getClientList(map,smartRefreshLayout, new Callback<HttpBean<HttpPageBean<ClientBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<ClientBean>> httpBean) {
                view.getClientList(httpBean.getData());
            }
        });
    }
    @Override
    public void getSelectCustomer(String token) {
        clientModel.getSelectCustomer(token,new Callback<HttpBean<ScreenBean>>() {
            @Override
            public void get(HttpBean<ScreenBean> httpBean) {
                view.getSelectCustomer(httpBean.getData());
            }
        });
    }
}
