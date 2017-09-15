package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddRelationManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/15.
 */
public class AddRelationModel implements AddRelationManage.Model{
    @Override
    public void addRelation(RelationBean relationBean, String userId, final Callback<HttpBean<RelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addRelation(relationBean,userId),new CommonObserver(new HttpResult<HttpBean<RelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<RelationBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));

    }
    @Override
    public void getListForRelation(Callback<HttpBean<InfoAddSpendBean>> callback) {

    }
    @Override
    public void editRelation(RelationBean relationBean, String userId, final Callback<HttpBean<RelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editRelation(relationBean,userId),new CommonObserver(new HttpResult<HttpBean<RelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<RelationBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }

}
