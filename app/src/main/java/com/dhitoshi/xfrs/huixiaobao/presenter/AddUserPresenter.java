package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddUserManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddUserModel;
import java.util.List;
import java.util.Map;
/**
 * Created by dxs on 2017/9/16.
 */

public class AddUserPresenter implements AddUserManage.Presenter{
    private AddUserManage.View view;
    private AddUserModel model;
    public AddUserPresenter(AddUserManage.View view, Context context) {
        this.view = view;
        model=new AddUserModel(context);
    }

    @Override
    public void signUp(Map<String, String> map, LoadingDialog dialog) {
        model.signUp(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.signUp(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void editUser(Map<String, String> map, LoadingDialog dialog) {
        model.editUser(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.editUser(httpBean.getStatus().getMsg());
            }
        });
    }

    @Override
    public void getGroupLists(String token) {
        model.getGroupLists(token, new Callback<HttpBean<List<UserRole>>>() {
            @Override
            public void get(HttpBean<List<UserRole>> httpBean) {
                view.getGroupLists(httpBean);
            }
        });
    }
}
