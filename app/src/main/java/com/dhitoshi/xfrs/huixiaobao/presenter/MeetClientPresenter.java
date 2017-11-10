package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetClientManage;
import com.dhitoshi.xfrs.huixiaobao.model.MeetClientModel;
import java.util.Map;

/**
 * Created by dxs on 2017/9/26.
 */

public class MeetClientPresenter implements MeetClientManage.Presenter{
    private MeetClientManage.View view;
    private MeetClientModel model;

    public MeetClientPresenter(MeetClientManage.View view, Context context) {
        this.view = view;
        model=new MeetClientModel(context);
    }

    @Override
    public void getCustomerList(Map<String, String> map, SmartRefreshLayout smartRefreshLayout) {
        model.getCustomerList(map, smartRefreshLayout, new Callback<HttpBean<HttpPageBean<MeetClientBean>>>() {
            @Override
            public void get(HttpBean<HttpPageBean<MeetClientBean>> httpBean) {
                view.getCustomerList(httpBean);
            }
        });
    }
}
