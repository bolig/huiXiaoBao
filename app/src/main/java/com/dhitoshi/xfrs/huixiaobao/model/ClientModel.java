package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ScreenBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.Map;

/**
 * Created by dxs on 2017/9/6.
 */
public class ClientModel implements ClientManage.Model{
    private Context context;
    public ClientModel(Context context) {
        this.context = context;
    }
    @Override
    public void getClientList(final Map<String,String> map, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<HttpPageBean<ClientBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getClientList(map),new CommonObserver(new HttpResult<HttpBean<HttpPageBean<ClientBean>>>() {
            @Override
            public void OnSuccess(HttpBean<HttpPageBean<ClientBean>> httpBean) {
                smartRefreshLayout.finishLoadmore();
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                           getClientList(map, smartRefreshLayout, callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishLoadmore();
                smartRefreshLayout.finishRefresh();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @Override
    public void getSelectCustomer(String token,final Callback<HttpBean<ScreenBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getSelectCustomer(token),new CommonObserver(new HttpResult<HttpBean<ScreenBean>>() {
            @Override
            public void OnSuccess(HttpBean<ScreenBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getSelectCustomer(token,callback);
                        }
                    });
                }
            }

            @Override
            public void OnFail(String msg) {
                Log.e("TAG","错误信息:"+msg);
            }
        }));
    }
}
