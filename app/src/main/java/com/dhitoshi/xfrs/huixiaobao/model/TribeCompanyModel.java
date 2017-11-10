package com.dhitoshi.xfrs.huixiaobao.model;

import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.TribeCompanyManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;


/**
 * Created by dxs on 2017/9/9.
 */

public class TribeCompanyModel implements TribeCompanyManage.Model {
    private Context context;
    public TribeCompanyModel(Context context) {
        this.context = context;
    }
    @Override
    public void getCompanyList(String token, final String page, final SmartRefreshLayout refreshLayout, final Callback<HttpPageBeanTwo<BaseBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getCompanyList(token, page),new CommonObserver(new HttpResult<HttpPageBeanTwo<BaseBean>>() {
            @Override
            public void OnSuccess(HttpPageBeanTwo<BaseBean> httpPageBeanTwo) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                if(httpPageBeanTwo.getStatus().getCode()==200){
                    callback.get(httpPageBeanTwo);
                }else if(httpPageBeanTwo.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getCompanyList(token,page,refreshLayout,callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpPageBeanTwo.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
