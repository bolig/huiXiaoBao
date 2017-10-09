package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.HasHeldManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.OnGoingManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

/**
 * Created by dxs on 2017/9/24.
 */

public class OnGoingModel implements OnGoingManage.Model {
    private Context context;
    public OnGoingModel(Context context) {
        this.context = context;
    }
    @Override
    public void getMeetForNow(String token, final String page, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<PageBean<ApplyMeetBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getMeetForNow(token, page),new CommonObserver(new HttpResult<HttpBean<PageBean<ApplyMeetBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<ApplyMeetBean>> httpBean) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getMeetForNow(token,page,smartRefreshLayout,callback);
                        }
                    });
                }
                else{
                    Toast.makeText(context,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadmore();
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}