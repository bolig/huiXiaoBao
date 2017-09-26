package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddGiftBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddGiftManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddGiftModel implements AddGiftManage.Model{
    private Context context;
    public AddGiftModel(Context context) {
        this.context = context;
    }
    @Override
    public void addGift(String token, final AddGiftBean addGiftBean, final LoadingDialog dialog, final Callback<HttpBean<GiftBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addGift(token,addGiftBean),new CommonObserver(new HttpResult<HttpBean<GiftBean>>() {
            @Override
            public void OnSuccess(HttpBean<GiftBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            addGift(token,addGiftBean,dialog,callback);
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
    public void getListForGift(String token,final Callback<HttpBean<InfoAddGiftBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForGift(token),new CommonObserver(new HttpResult<HttpBean<InfoAddGiftBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddGiftBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                           getListForGift(token,callback);
                        }
                    });
                }
            }

            @Override
            public void OnFail(String msg) {
                Log.e("TAG","----->>>"+msg);
            }
        }));
    }

    @Override
    public void editGift(String token, final AddGiftBean addGiftBean, final LoadingDialog dialog, final Callback<HttpBean<GiftBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editGift(token,addGiftBean),new CommonObserver(new HttpResult<HttpBean<GiftBean>>() {
            @Override
            public void OnSuccess(HttpBean<GiftBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                           editGift(token,addGiftBean,dialog,callback);
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
