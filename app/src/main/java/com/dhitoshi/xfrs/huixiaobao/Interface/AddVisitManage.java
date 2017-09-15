package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddVisitManage {
    interface View{
        void  addVisit(String result);
        void  editVisit(String result);
        void  getListForVisit(HttpBean<InfoAddVisitBean> httpBean);
    }
    interface Model{
        void addVisit(AddVisitBean addVisitBean, Callback<HttpBean<VisitBean>> callback);
        void getListForVisit(Callback<HttpBean<InfoAddVisitBean>> callback);
        void editVisit(AddVisitBean addVisitBean,Callback<HttpBean<VisitBean>> callback);
    }
    interface Presenter{
        void addVisit(AddVisitBean addVisitBean);
        void editVisit(AddVisitBean addVisitBean);
        void getListForVisit();
    }

}
