package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
/**
 * Created by dxs on 2017/9/9.
 */

public class AddClientModel implements AddClientManage.Model {
    @Override
    public void addClient(ClientBean clientBean, final Callback<HttpBean<ClientBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addClient(clientBean),new CommonObserver(new HttpResult<HttpBean<ClientBean>>() {

            @Override
            public void OnSuccess(HttpBean<ClientBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
    @Override
    public void getInfoForAdd(final Callback<HttpBean<InfoAddClientBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getInfoForAdd(),new CommonObserver(new HttpResult<HttpBean<InfoAddClientBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddClientBean> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }

    @Override
    public void checkRepeat(String area, String phone, final Callback<HttpBean<Object>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().checkRepeat(area, phone),new CommonObserver(new HttpResult<HttpBean<Object>>() {
            @Override
            public void OnSuccess(HttpBean<Object> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
