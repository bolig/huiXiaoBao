package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddRelationManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/15.
 */
public class AddRelationModel implements AddRelationManage.Model{
    private Context context;

    public AddRelationModel(Context context) {
        this.context = context;
    }

    @Override
    public void addRelation(AddRelationBean addRelationBean, final LoadingDialog dialog, final Callback<HttpBean<RelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addRelation(addRelationBean),new CommonObserver(new HttpResult<HttpBean<RelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<RelationBean> httpBean) {
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
    public void getListForRelation(final Callback<HttpBean<InfoAddRelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForRelation(),new CommonObserver(new HttpResult<HttpBean<InfoAddRelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddRelationBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
    @Override
    public void editRelation(AddRelationBean addRelationBean, final LoadingDialog dialog, final Callback<HttpBean<RelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editRelation(addRelationBean),new CommonObserver(new HttpResult<HttpBean<RelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<RelationBean> httpBean) {
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
