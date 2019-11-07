package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.ApplyMeetingManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.ApplyMeetingModel;

import java.util.Map;

/**
 * Created by dxs on 2017/9/25.
 */

public class ApplyMeetingPresenter implements ApplyMeetingManage.Prsenter {
    private ApplyMeetingManage.View view;
    private ApplyMeetingModel model;

    public ApplyMeetingPresenter(ApplyMeetingManage.View view, Context context) {
        this.view = view;
        model=new ApplyMeetingModel(context);
    }
    @Override
    public void applyMeeting(Map<String, String> map, LoadingDialog dialog) {
        model.applyMeeting(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.applyMeeting(httpBean.getStatus().getMsg());
            }
        });
    }
}
