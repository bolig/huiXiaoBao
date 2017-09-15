package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
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
    public void addRelation(AddRelationBean addRelationBean, final Callback<HttpBean<RelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().addRelation(addRelationBean),new CommonObserver(new HttpResult<HttpBean<RelationBean>>() {
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
    public void getListForRelation(final Callback<HttpBean<InfoAddRelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getListForRelation(),new CommonObserver(new HttpResult<HttpBean<InfoAddRelationBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddRelationBean> httpBean) {
                callback.get(httpBean);
            }

            @Override
            public void OnFail(String msg) {

            }
        }));
    }
    @Override
    public void editRelation(AddRelationBean addRelationBean, final Callback<HttpBean<RelationBean>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().editRelation(addRelationBean),new CommonObserver(new HttpResult<HttpBean<RelationBean>>() {
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
