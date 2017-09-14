package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddVisitManage {
    interface View{
        void  addVisit(String result);
        void  editVisit(String result);
        void  getListForVisit(HttpBean<InfoAddSpendBean> httpBean);
    }
    interface Model{
        void addVisit(VisitBean visitBean, String userId, Callback<HttpBean<VisitBean>> callback);
        void getListForVisit(Callback<HttpBean<InfoAddSpendBean>> callback);
        void editRelation(VisitBean visitBean,String userId,Callback<HttpBean<VisitBean>> callback);
    }
    interface Presenter{
        void addVisit(VisitBean visitBean, String userId);
        void editVisit(VisitBean visitBean,String userId);
        void getListForVisit();
    }

}
