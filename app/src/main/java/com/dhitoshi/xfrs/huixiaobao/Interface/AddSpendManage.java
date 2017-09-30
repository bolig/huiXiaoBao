package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;

import java.util.Map;

/**
 * Created by dxs on 2017/9/8.
 */

public interface AddSpendManage {
    interface View{
        void  addSpend(String result);
        void  editSpend(String result);
        void getListForSpending(HttpBean<InfoAddSpendBean> httpBean);
    }
    interface Model{
        void addSpend(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<SpendBean>> callback);
        void getListForSpending(String token,Callback<HttpBean<InfoAddSpendBean>> callback);
        void editSpend(Map<String,String> map,LoadingDialog dialog,Callback<HttpBean<SpendBean>> callback);
    }
    interface Presenter{
        void addSpend(Map<String,String> map,LoadingDialog dialog);
        void editSpend(Map<String,String> map,LoadingDialog dialog);
        void getListForSpending(String token);
    }

}
