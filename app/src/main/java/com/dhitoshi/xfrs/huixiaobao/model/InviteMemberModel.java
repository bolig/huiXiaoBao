package com.dhitoshi.xfrs.huixiaobao.model;

import android.content.Context;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeMemberBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.InviteMemberManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
/**
 * Created by dxs on 2017/9/9.
 */
public class InviteMemberModel implements InviteMemberManage.Model {
    private Context context;
    public InviteMemberModel(Context context) {
        this.context = context;
    }
    @Override
    public void getUserList(String token, final String area_id, final String page, final SmartRefreshLayout refreshLayout, final Callback<HttpPageBeanTwo<TribeMemberBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().userList(token,area_id, page),new CommonObserver(new HttpResult<HttpPageBeanTwo<TribeMemberBean>>() {
            @Override
            public void OnSuccess(HttpPageBeanTwo<TribeMemberBean> httpPageBeanTwo) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                if(httpPageBeanTwo.getStatus().getCode()==200){
                    callback.get(httpPageBeanTwo);
                }else if(httpPageBeanTwo.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            getUserList(token,area_id,page,refreshLayout,callback);
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
