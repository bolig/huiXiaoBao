package com.dhitoshi.xfrs.huixiaobao.presenter;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.RelationManage;
import com.dhitoshi.xfrs.huixiaobao.model.RelationModel;
/**
 * Created by dxs on 2017/9/6.
 */
public class RelationPresenter implements RelationManage.Prsenter{
    private RelationManage.View view;
    private RelationModel relationModel;
    public RelationPresenter(RelationManage.View view) {
        this.view = view;
        relationModel=new RelationModel();
    }
    @Override
    public void getRelationLists(String userid, String page) {
        relationModel.getRelationLists(userid, page, new Callback<HttpBean<PageBean<RelationBean>>>() {
            @Override
            public void get(HttpBean<PageBean<RelationBean>> httpBean) {
                view.getRelationLists(httpBean.getData());
            }
        });
    }
}
