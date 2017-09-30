package com.dhitoshi.xfrs.huixiaobao.model;
import android.content.Context;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddMeetingManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;

import java.util.Map;

/**
 * Created by dxs on 2017/9/15.
 */

public class AddMeetingModel implements AddMeetingManage.Model{
    private Context context;
    public AddMeetingModel(Context context) {
        this.context = context;
    }
    @Override
    public void addMeeting(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<MeetBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addMeet(map),new CommonObserver(new HttpResult<HttpBean<MeetBean>>() {
            @Override
            public void OnSuccess(HttpBean<MeetBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            addMeeting(map, dialog, callback);
                        }
                    });
                }
                else{
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
    public void getListForMeeting(final String token, final Callback<HttpBean<InfoAddMeetBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForMeeting(token),new CommonObserver(new HttpResult<HttpBean<InfoAddMeetBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddMeetBean> httpBean) {
                if(httpBean.getStatus().getCode()==200) {
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                           getListForMeeting(token, callback);
                        }
                    });
                }
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
    @Override
    public void editMeeting(final Map<String,String> map, final LoadingDialog dialog, final Callback<HttpBean<MeetBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editMeet(map),new CommonObserver(new HttpResult<HttpBean<MeetBean>>() {
            @Override
            public void OnSuccess(HttpBean<MeetBean> httpBean) {
                dialog.dismiss();
                if(httpBean.getStatus().getCode()==200){
                    callback.get(httpBean);
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(context, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                           editMeeting(map, dialog, callback);
                        }
                    });
                }
                else{
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
