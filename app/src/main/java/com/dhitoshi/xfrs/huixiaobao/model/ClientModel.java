package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import java.util.List;
/**
 * Created by dxs on 2017/9/6.
 */
public class ClientModel implements ClientManage.Model{
    @Override
    public void getClientList(String type, String area, String order, String page, final Callback<List<ClientBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getClientList(type, area, order, page),new CommonObserver(new HttpResult<List<ClientBean>>() {
            @Override
            public void OnSuccess(List<ClientBean> clientBeens) {
                 callback.get(clientBeens);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
