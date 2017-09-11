package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddClientModel;

import java.util.List;


/**
 * Created by dxs on 2017/9/6.
 */
public class AddClientPresenter implements AddClientManage.Presenter{
    private AddClientManage.View view;
    private AddClientModel addClientModel;
    public AddClientPresenter(AddClientManage.View view) {
        this.view = view;
        addClientModel=new AddClientModel();
    }
    @Override
    public void addClient(ClientBean clientBean) {
        addClientModel.addClient(clientBean, new Callback<HttpBean<ClientBean>>() {
            @Override
            public void get(HttpBean<ClientBean> httpBean) {
                view.addClient(httpBean.getStatus().getMsg());
            }
        });
    }
    @Override
    public void getInfoForAdd() {
        addClientModel.getInfoForAdd(new Callback<HttpBean<InfoAddClientBean>>() {
            @Override
            public void get(HttpBean<InfoAddClientBean> httpBean) {
                view.getInfoForAdd(httpBean);
            }
        });
    }
    @Override
    public void checkRepeat(String area, String phone) {
        addClientModel.checkRepeat(area, phone, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.checkRepeat(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void getAreaLists() {
        addClientModel.getAreaLists(new Callback<HttpBean<List<AreaBean>>>() {
            @Override
            public void get(HttpBean<List<AreaBean>> httpBean) {
                view.getAreaLists(httpBean);
            }
        });
    }
}
