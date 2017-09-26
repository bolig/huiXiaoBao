package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.AddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

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
        void addVisit(String token,AddVisitBean addVisitBean, LoadingDialog dialog, Callback<HttpBean<VisitBean>> callback);
        void getListForVisit(String token,Callback<HttpBean<InfoAddVisitBean>> callback);
        void editVisit(String token,AddVisitBean addVisitBean,LoadingDialog dialog,Callback<HttpBean<VisitBean>> callback);
    }
    interface Presenter{
        void addVisit(String token,AddVisitBean addVisitBean,LoadingDialog dialog);
        void editVisit(String token,AddVisitBean addVisitBean,LoadingDialog dialog);
        void getListForVisit(String token);
    }

}
