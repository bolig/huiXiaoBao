package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.Map;
/**
 * Created by dxs on 2017/9/8.
 */

public interface AddMeetingClientManage {
    interface View{
        void addCustomer(String result);
    }
    interface Model{
        void addCustomer(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
    }
    interface Presenter{
        void addCustomer(Map<String,String> map, LoadingDialog dialog);
    }

}
