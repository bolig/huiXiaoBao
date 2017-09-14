package com.dhitoshi.xfrs.huixiaobao.Interface;

import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
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
        void addSpend(SpendBean spendBean,String userId,Callback<HttpBean<SpendBean>> callback);
        void getListForSpending(Callback<HttpBean<InfoAddSpendBean>> callback);
        void editSpend(SpendBean spendBean,String userId,Callback<HttpBean<SpendBean>> callback);
    }
    interface Presenter{
        void addSpend(SpendBean spendBean,String userId);
        void editSpend(SpendBean spendBean,String userId);
        void getListForSpending();
    }

}
