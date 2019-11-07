package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RemindBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.RemindManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

/**
 * Created by dxs on 2017/9/9.
 */
public class RemindModel implements RemindManage.Model {
    private Context context;
    public RemindModel(Context context) {
        this.context = context;
    }
    @Override
    public void getRemind(String token, final SmartRefreshLayout refreshLayout, final Callback<HttpBean<RemindBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getRemind(token),new CommonObserver(new HttpResult<HttpBean<RemindBean>>() {
            @Override
            public void OnSuccess(HttpBean<RemindBean> httpBean) {
                refreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getRemind(token,refreshLayout,callback);
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
