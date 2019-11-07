package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ResourceBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.ResourceManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
/**
 * Created by dxs on 2017/9/9.
 */
public class ResourceModel implements ResourceManage.Model {
    private Context context;
    public ResourceModel(Context context) {
        this.context = context;
    }
    @Override
    public void getMyResource(String token, final SmartRefreshLayout refreshLayout, final Callback<HttpBean<ResourceBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getMyResource(token),new CommonObserver(new HttpResult<HttpBean<ResourceBean>>() {
            @Override
            public void OnSuccess(HttpBean<ResourceBean> httpBean) {
                refreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getMyResource(token,refreshLayout,callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                refreshLayout.finishRefresh();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
