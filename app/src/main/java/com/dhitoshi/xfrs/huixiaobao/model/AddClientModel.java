package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddClientBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddClientManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.Map;

/**
 * Created by dxs on 2017/9/9.
 */

public class AddClientModel implements AddClientManage.Model {
    private Context context;

    public AddClientModel(Context context) {
        this.context = context;
    }

    @Override
    public void addClient(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<ClientBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addClient(map),new CommonObserver(new HttpResult<HttpBean<ClientBean>>() {

            @Override
            public void OnSuccess(HttpBean<ClientBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                           addClient(map,dialog,callback);
                        }
                    });
                }
                else{
                    dialog.dismiss();
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void editClient(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<ClientBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editClient(map),new CommonObserver(new HttpResult<HttpBean<ClientBean>>() {

            @Override
            public void OnSuccess(HttpBean<ClientBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            editClient(map,dialog,callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public void getInfoForAdd(final String token, final Callback<HttpBean<InfoAddClientBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getInfoForAdd(token),new CommonObserver(new HttpResult<HttpBean<InfoAddClientBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddClientBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                           getInfoForAdd(token, callback);
                        }
                    });
                }
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
    @Override
    public void checkRepeat(String token,final LoadingDialog dialog,String area, String phone,String id, final Callback<HttpBean<Object>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().checkRepeat(token,area, phone,id),new CommonObserver(new HttpResult<HttpBean<Object>>() {
            @Override
            public void OnSuccess(HttpBean<Object> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                dialog.dismiss();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
