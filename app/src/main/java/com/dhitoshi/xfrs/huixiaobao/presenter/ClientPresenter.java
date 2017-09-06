package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.model.ClientModel;
import java.util.List;
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
    public void getClientList(String type, String area, String order, String page) {
        clientModel.getClientList(type, area, order, page, new Callback<List<ClientBean>>() {
            @Override
            public void get(List<ClientBean> clientBeans) {
                view.getClientList(clientBeans);
            }
        });
    }
}
