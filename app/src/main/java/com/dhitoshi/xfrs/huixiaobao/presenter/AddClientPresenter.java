package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddClientModel;

import java.util.List;
import java.util.Map;


/**
 * Created by dxs on 2017/9/6.
 */
public class AddClientPresenter implements AddClientManage.Presenter{
    private AddClientManage.View view;
    private AddClientModel addClientModel;
    public AddClientPresenter(AddClientManage.View view, Context context) {
        this.view = view;
        addClientModel=new AddClientModel(context);
    }
    @Override
    public void addClient(String token,AddClientBean addClientBean, LoadingDialog dialog) {
        addClientModel.addClient(token,addClientBean,dialog, new Callback<HttpBean<ClientBean>>() {
            @Override
            public void get(HttpBean<ClientBean> httpBean) {
                view.addClient(httpBean);
            }
        });
    }

    @Override
    public void editClient(String token,AddClientBean addClientBean,LoadingDialog dialog) {
        addClientModel.editClient(token,addClientBean,dialog, new Callback<HttpBean<ClientBean>>() {
            @Override
            public void get(HttpBean<ClientBean> httpBean) {
                view.editClient(httpBean);
            }
        });
    }

    @Override
    public void getInfoForAdd(String token) {
        addClientModel.getInfoForAdd(token,new Callback<HttpBean<InfoAddClientBean>>() {
            @Override
            public void get(HttpBean<InfoAddClientBean> httpBean) {
                view.getInfoForAdd(httpBean);
            }
        });
    }
    @Override
    public void checkRepeat(String token,LoadingDialog dialog,String area, String phone,String id) {
        addClientModel.checkRepeat(token,dialog,area, phone,id, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.checkRepeat(httpBean.getStatus().getMsg());
            }
        });
    }
}
