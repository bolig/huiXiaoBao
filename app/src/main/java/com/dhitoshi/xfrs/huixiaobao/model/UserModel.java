package com.dhitoshi.xfrs.huixiaobao.model;

import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.UserManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

import java.util.List;

/**
 * Created by dxs on 2017/9/16.
 */

public class UserModel implements UserManage.Model{
    private Context context;
    public UserModel(Context context) {
        this.context = context;
    }
    @Override
    public void getUserList(String page, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<PageBean<UserBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getUserList(page),new CommonObserver(new HttpResult<HttpBean<PageBean<UserBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<UserBean>> httpBean) {
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
