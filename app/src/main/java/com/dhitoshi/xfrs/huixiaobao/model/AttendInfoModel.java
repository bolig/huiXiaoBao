package com.dhitoshi.xfrs.huixiaobao.model;

import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.AttendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.AttendInfoManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.List;

/**
 * Created by dxs on 2017/9/9.
 */

public class AttendInfoModel implements AttendInfoManage.Model {
    private Context context;
    public AttendInfoModel(Context context) {
        this.context = context;
    }

    @Override
    public void GetaAttendList(final String token, final String meetingid, final SmartRefreshLayout smartRefreshLayout, final Callback<HttpBean<AttendBean<Integer>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().GetaAttendList(token,meetingid),new CommonObserver(new HttpResult<HttpBean<AttendBean<Integer>>>() {
            @Override
            public void OnSuccess(HttpBean<AttendBean<Integer>> attendBean) {
                smartRefreshLayout.finishRefresh();
                if (attendBean.getStatus().getCode()==200){
                    callback.get(attendBean);
                }else if(attendBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            GetaAttendList(token, meetingid, smartRefreshLayout, callback);
                        }
                    });
                } else{
                    Toast.makeText(context,attendBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
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
