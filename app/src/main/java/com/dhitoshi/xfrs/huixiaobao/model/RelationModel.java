package com.dhitoshi.xfrs.huixiaobao.model;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.RelationManage;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;

/**
 * Created by dxs on 2017/9/7.
 */

public class RelationModel implements RelationManage.Model{

    @Override
    public void getRelationLists(String userid, String page, final Callback<HttpBean<PageBean<RelationBean>>> callback) {
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getRelationLists(userid, page),new CommonObserver(new HttpResult<HttpBean<PageBean<RelationBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<RelationBean>> httpBean) {
                callback.get(httpBean);
            }
            @Override
            public void OnFail(String msg) {

            }
        }));
    }
}
