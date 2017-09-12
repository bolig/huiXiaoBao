package com.dhitoshi.xfrs.huixiaobao.model;
import android.util.Log;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/6.
 */
public class ClientModel implements ClientManage.Model{
    @Override
    public void getClientList(Map<String,String> map, final Callback<HttpBean<PageBean<List<ClientBean>>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getClientList(map),new CommonObserver(new HttpResult<HttpBean<PageBean<List<ClientBean>>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<List<ClientBean>>> httpBean) {
                 callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {
                Log.e("TAG","错误信息:"+msg);
            }
        }));
    }

    @Override
    public void getClientList(final Callback<HttpBean<PageBean<List<ClientBean>>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getClientList(),new CommonObserver(new HttpResult<HttpBean<PageBean<List<ClientBean>>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<List<ClientBean>>> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {
                Log.e("TAG","错误信息:"+msg);
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
