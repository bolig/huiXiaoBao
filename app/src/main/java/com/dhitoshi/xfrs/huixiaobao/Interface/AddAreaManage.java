package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.List;
/**
 * Created by dxs on 2017/9/8.
 */
public interface AddAreaManage {
    interface View{
        void getAreaLists(HttpBean<List<AreaBean>> httpBean);
        void addArea(String result);
        void editArea(String result);
    }
    interface Model{
        void getAreaLists(SmartRefreshLayout smartRefreshLayout,Callback<HttpBean<List<AreaBean>>> callback);
        void addArea(KidBean kidBean, String token, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
        void editArea(KidBean kidBean, String token, LoadingDialog dialog,Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void getAreaLists(SmartRefreshLayout smartRefreshLayout);
        void addArea(KidBean kidBean, String token, LoadingDialog dialog);
        void editArea(KidBean kidBean, String token, LoadingDialog dialog);
    }

}
