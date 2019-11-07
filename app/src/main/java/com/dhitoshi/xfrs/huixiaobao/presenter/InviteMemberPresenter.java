package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeMemberBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.InviteMemberManage;
import com.dhitoshi.xfrs.huixiaobao.model.InviteMemberModel;

/**
 * Created by dxs on 2017/9/6.
 */
public class InviteMemberPresenter implements InviteMemberManage.Presenter{
    private InviteMemberManage.View view;
    private InviteMemberModel inviteMemberModel;
    public InviteMemberPresenter(InviteMemberManage.View view, Context context) {
        this.view = view;
        inviteMemberModel=new InviteMemberModel(context);
    }
    @Override
    public void getUserList(String token, String area_id, String page, SmartRefreshLayout refreshLayout) {
        inviteMemberModel.getUserList(token,area_id,page,refreshLayout, new Callback<HttpPageBeanTwo<TribeMemberBean>>() {
            @Override
            public void get(HttpPageBeanTwo<TribeMemberBean> httpPageBeanTwo ) {
                view.getUserList(httpPageBeanTwo);
            }
        });
    }
}
