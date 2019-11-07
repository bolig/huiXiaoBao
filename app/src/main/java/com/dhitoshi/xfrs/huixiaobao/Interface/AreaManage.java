package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import java.util.List;
/**
 * Created by dxs on 2017/9/8.
 */

public interface AreaManage {
    interface View{
        void getAreaLists(HttpBean<List<AreaBean>> httpBean);
    }
    interface Model{
        void getAreaLists(String token,Callback<HttpBean<List<AreaBean>>> callback);
    }
    interface Presenter{
        void getAreaLists(String token);
    }

}
