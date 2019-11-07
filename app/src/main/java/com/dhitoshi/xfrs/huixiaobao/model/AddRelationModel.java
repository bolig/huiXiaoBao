package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddRelationManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.Map;

/**
 * Created by dxs on 2017/9/15.
 */
public class AddRelationModel implements AddRelationManage.Model{
    private Context context;

    public AddRelationModel(Context context) {
        this.context = context;
    }

    @Override
    public void addRelation(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<RelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addRelation(map),new CommonObserver(new HttpResult<HttpBean<RelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<RelationBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            addRelation(map, dialog, callback);
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
    public void getListForRelation(final String token, final Callback<HttpBean<InfoAddRelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForRelation(token),new CommonObserver(new HttpResult<HttpBean<InfoAddRelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddRelationBean> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                          getListForRelation(token, callback);
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
    public void editRelation(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<RelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editRelation(map),new CommonObserver(new HttpResult<HttpBean<RelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<RelationBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                           editRelation(map, dialog, callback);
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
