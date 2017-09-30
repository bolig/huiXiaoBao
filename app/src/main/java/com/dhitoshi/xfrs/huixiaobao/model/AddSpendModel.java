package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddSpendManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.Map;

/**
 * Created by dxs on 2017/9/13.
 */
public class AddSpendModel implements AddSpendManage.Model{
    private Context context;
    public AddSpendModel(Context context) {
        this.context = context;
    }
    @Override
    public void addSpend(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<SpendBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addSpend(map),new CommonObserver(new HttpResult<HttpBean<SpendBean>>() {
            @Override
            public void OnSuccess(HttpBean<SpendBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                           addSpend(map, dialog, callback);
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
    public void getListForSpending(final String token, final Callback<HttpBean<InfoAddSpendBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForSpending(token),new CommonObserver(new HttpResult<HttpBean<InfoAddSpendBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddSpendBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                          getListForSpending(token, callback);
                        }
                    });
                }
            }
            @Override
            public void OnFail(String msg) {
                Log.e("TAG","msg---->>>"+msg);
            }
        }));
    }

    @Override
    public void editSpend(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<SpendBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editSpend(map),new CommonObserver(new HttpResult<HttpBean<SpendBean>>() {
            @Override
            public void OnSuccess(HttpBean<SpendBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            editSpend(map, dialog, callback);
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
}
