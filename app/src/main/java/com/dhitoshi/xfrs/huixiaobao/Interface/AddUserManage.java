package com.dhitoshi.xfrs.huixiaobao.Interface;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import java.util.List;
import java.util.Map;
/**
 * Created by dxs on 2017/9/22.
 */

public interface AddUserManage {
    interface View{
        void signUp(String result);
        void editUser(String result);
        void getGroupLists(HttpBean<List<UserRole>> httpBean);
    }
    interface Model{
        void signUp(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
        void editUser(Map<String,String> map, LoadingDialog dialog, Callback<HttpBean<Object>> callback);
        void getGroupLists(String token, Callback<HttpBean<List<UserRole>>> callback);
    }
    interface Presenter{
        void signUp(Map<String,String> map, LoadingDialog dialog);
        void editUser(Map<String,String> map, LoadingDialog dialog);
        void getGroupLists(String token);
    }
}
