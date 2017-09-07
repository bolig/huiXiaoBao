package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.VisitManage;
import com.dhitoshi.xfrs.huixiaobao.model.VisitModel;
/**
 * Created by dxs on 2017/9/6.
 */
public class VisitPresenter implements VisitManage.Prsenter{
    private VisitManage.View view;
    private VisitModel visitModel;
    public VisitPresenter(VisitManage.View view) {
        this.view = view;
        visitModel=new VisitModel();
    }
    @Override
    public void getFeedbackLists(String userid, String page) {
        visitModel.getFeedbackLists(userid, page, new Callback<HttpBean<PageBean<VisitBean>>>() {
            @Override
            public void get(HttpBean<PageBean<VisitBean>> httpBean) {
                view.getFeedbackLists(httpBean.getData());
            }
        });
    }
}
