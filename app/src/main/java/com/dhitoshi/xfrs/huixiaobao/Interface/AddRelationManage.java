package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import retrofit2.http.Body;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddRelationManage {
    interface View{
        void  addRelation(String result);
        void  editRelation(String result);
        void  getListForRelation(HttpBean<InfoAddRelationBean> httpBean);
    }
    interface Model{
        void addRelation(AddRelationBean addRelationBean, LoadingDialog dialog, Callback<HttpBean<RelationBean>> callback);
        void getListForRelation(Callback<HttpBean<InfoAddRelationBean>> callback);
        void editRelation(AddRelationBean addRelationBean, LoadingDialog dialog,Callback<HttpBean<RelationBean>> callback);
    }
    interface Presenter{
        void addRelation(AddRelationBean addRelationBean, LoadingDialog dialog);
        void editRelation(AddRelationBean addRelationBean, LoadingDialog dialog);
        void getListForRelation();
    }

}
