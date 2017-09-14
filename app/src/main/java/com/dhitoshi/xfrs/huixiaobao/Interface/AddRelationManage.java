package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
/**
 * Created by dxs on 2017/9/8.
 */

public interface AddRelationManage {
    interface View{
        void  addRelation(String result);
        void  editRelation(String result);
        void  getListForRelation(HttpBean<InfoAddSpendBean> httpBean);
    }
    interface Model{
        void addRelation(RelationBean relationBean, String userId, Callback<HttpBean<RelationBean>> callback);
        void getListForRelation(Callback<HttpBean<InfoAddSpendBean>> callback);
        void editRelation(RelationBean relationBean,String userId,Callback<HttpBean<RelationBean>> callback);
    }
    interface Presenter{
        void addRelation(RelationBean relationBean,String userId);
        void editRelation(RelationBean relationBean,String userId);
        void getListForRelation();
    }

}
