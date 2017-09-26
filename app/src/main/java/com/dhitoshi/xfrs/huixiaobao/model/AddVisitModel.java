package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddVisitManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddVisitModel implements AddVisitManage.Model{
    private Context context;

    public AddVisitModel(Context context) {
        this.context = context;
    }

    @Override
    public void addVisit(String token,AddVisitBean addVisitBean, final LoadingDialog dialog, final Callback<HttpBean<VisitBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addVisit(token,addVisitBean),new CommonObserver(new HttpResult<HttpBean<VisitBean>>() {
            @Override
            public void OnSuccess(HttpBean<VisitBean> httpBean) {
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

    @Override
    public void getListForVisit(String token,final Callback<HttpBean<InfoAddVisitBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForVisit(token),new CommonObserver(new HttpResult<HttpBean<InfoAddVisitBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddVisitBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }

    @Override
    public void editVisit(String token,AddVisitBean addVisitBean, final LoadingDialog dialog, final Callback<HttpBean<VisitBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editVisit(token,addVisitBean),new CommonObserver(new HttpResult<HttpBean<VisitBean>>() {
            @Override
            public void OnSuccess(HttpBean<VisitBean> httpBean) {
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
