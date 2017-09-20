package com.dhitoshi.xfrs.huixiaobao.model;

import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddAreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by dxs on 2017/9/9.
 */

public class AddAreaModel implements AddAreaManage.Model {
    private Context context;
    public AddAreaModel(Context context) {
        this.context = context;
    }


    @Override
    public void addArea(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<KidBean>> callback) {
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addArea(map),new CommonObserver(new HttpResult<HttpBean<KidBean>>() {
            @Override
            public void OnSuccess(HttpBean<KidBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            addArea(map,dialog,callback);
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
    public void editArea(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<Object>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editArea(map),new CommonObserver(new HttpResult<HttpBean<Object>>() {
            @Override
            public void OnSuccess(HttpBean<Object> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            editArea(map,dialog,callback);
                        }
                    });
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
