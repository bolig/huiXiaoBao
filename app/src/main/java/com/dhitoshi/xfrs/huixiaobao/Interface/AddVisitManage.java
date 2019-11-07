package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.Map;

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
        void addVisit(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<VisitBean>> callback);
        void getListForVisit(String token,Callback<HttpBean<InfoAddVisitBean>> callback);
        void editVisit(Map<String,String> map,LoadingDialog dialog,Callback<HttpBean<VisitBean>> callback);
    }
    interface Presenter{
        void addVisit(Map<String,String> map,LoadingDialog dialog);
        void editVisit(Map<String,String> map,LoadingDialog dialog);
        void getListForVisit(String token);
    }

}
