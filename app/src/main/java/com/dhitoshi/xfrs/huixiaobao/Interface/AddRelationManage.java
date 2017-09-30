package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.Map;

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
        void addRelation(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<RelationBean>> callback);
        void getListForRelation(String token,Callback<HttpBean<InfoAddRelationBean>> callback);
        void editRelation(Map<String,String> map, LoadingDialog dialog,Callback<HttpBean<RelationBean>> callback);
    }
    interface Presenter{
        void addRelation(Map<String,String> map, LoadingDialog dialog);
        void editRelation(Map<String,String> map, LoadingDialog dialog);
        void getListForRelation(String token);
    }

}
