package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.model.ClientModel;
import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/6.
 */
public class ClientPresenter implements ClientManage.Prsenter{
    private ClientManage.View view;
    private ClientModel clientModel;
    public ClientPresenter(ClientManage.View view) {
        this.view = view;
        clientModel=new ClientModel();
    }
    @Override
    public void getClientList(Map<String,String> map) {
        clientModel.getClientList(map, new Callback<HttpBean<PageBean<List<ClientBean>>>>() {
            @Override
            public void get(HttpBean<PageBean<List<ClientBean>>> httpBean) {
                view.getClientList(httpBean.getData());
            }
        });
    }

    @Override
    public void getClientList() {
        clientModel.getClientList(new Callback<HttpBean<PageBean<List<ClientBean>>>>() {
            @Override
            public void get(HttpBean<PageBean<List<ClientBean>>> httpBean) {
                view.getClientList(httpBean.getData());
            }
        });
    }


    @Override
    public void getSelectCustomer() {
        clientModel.getSelectCustomer(new Callback<HttpBean<ScreenBean>>() {
            @Override
            public void get(HttpBean<ScreenBean> httpBean) {
                view.getSelectCustomer(httpBean.getData());
            }
        });
    }
}
