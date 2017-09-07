package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SpendManage;
import com.dhitoshi.xfrs.huixiaobao.model.SpendModel;


/**
 * Created by dxs on 2017/9/6.
 */
public class SpendPresenter implements SpendManage.Prsenter{
    private SpendManage.View view;
    private SpendModel spendModel;
    public SpendPresenter(SpendManage.View view) {
        this.view = view;
        spendModel=new SpendModel();
    }
    @Override
    public void getSpendingLists(String userid, String page) {
        spendModel.getSpendingLists(userid, page, new Callback<HttpBean<PageBean<SpendBean>>>() {
            @Override
            public void get(HttpBean<PageBean<SpendBean>> httpBean) {
                view.getSpendingLists(httpBean.getData());
            }
        });
    }
}
