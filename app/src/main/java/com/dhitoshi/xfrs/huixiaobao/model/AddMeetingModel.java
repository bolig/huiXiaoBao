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
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
/**
 * Created by dxs on 2017/9/15.
 */

public class AddMeetingModel implements AddMeetingManage.Model{
    private Context context;
    public AddMeetingModel(Context context) {
        this.context = context;
    }
    @Override
    public void addMeeting(AddMeetBean addMeetBean,final LoadingDialog dialog,  final Callback<HttpBean<MeetBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addMeet(addMeetBean),new CommonObserver(new HttpResult<HttpBean<MeetBean>>() {
            @Override
            public void OnSuccess(HttpBean<MeetBean> httpBean) {
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
    public void getListForMeeting(final Callback<HttpBean<InfoAddMeetBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForMeeting(),new CommonObserver(new HttpResult<HttpBean<InfoAddMeetBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddMeetBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
    @Override
    public void editMeeting(AddMeetBean addMeetBean, final LoadingDialog dialog, final Callback<HttpBean<MeetBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editMeet(addMeetBean),new CommonObserver(new HttpResult<HttpBean<MeetBean>>() {
            @Override
            public void OnSuccess(HttpBean<MeetBean> httpBean) {
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
