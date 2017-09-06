package com.dhitoshi.xfrs.huixiaobao.model;
import android.util.Log;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
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
    public void getClientList(String type, String area, String order, String page, final Callback<HttpBean<List<ClientBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getClientList(type, area, order, page),new CommonObserver(new HttpResult<HttpBean<List<ClientBean>>>() {
            @Override
            public void OnSuccess(HttpBean<List<ClientBean>> httpBean) {
                 callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
    @Override
    public void getSelectCustomer(final Callback<HttpBean<ScreenBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSelectCustomer(),new CommonObserver(new HttpResult<HttpBean<ScreenBean>>() {
            @Override
            public void OnSuccess(HttpBean<ScreenBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {
                Log.e("TAG","错误信息:"+msg);
            }
        }));
    }
}
