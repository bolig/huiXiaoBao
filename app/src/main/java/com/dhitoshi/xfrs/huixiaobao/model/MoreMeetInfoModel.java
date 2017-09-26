package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MoreMeetInfoBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.MoreMeetInfoManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import retrofit2.http.Query;
/**
 * Created by dxs on 2017/9/26.
 */

public class MoreMeetInfoModel implements MoreMeetInfoManage.Model {
    private Context context;

    public MoreMeetInfoModel(Context context) {
        this.context = context;
    }
    @Override
    public void getArticleBody(@Query("aid") String aid, @Query("id") String id, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<MoreMeetInfoBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getArticleBody(aid, id),new CommonObserver(new HttpResult<HttpBean<MoreMeetInfoBean>>() {
            @Override
            public void OnSuccess(HttpBean<MoreMeetInfoBean> httpBean) {
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {

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
                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
}
