package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.UpdateManage;
import com.dhitoshi.xfrs.huixiaobao.model.UpdateModel;
import java.util.Map;
/**
 * Created by dxs on 2017/9/16.
 */
public class UpdatePresenter implements UpdateManage.Presenter{
    private UpdateManage.View view;
    private UpdateModel model;
    public UpdatePresenter(UpdateManage.View view, Context context) {
        this.view = view;
        model=new UpdateModel(context);
    }
    @Override
    public void resetPassword(Map<String, String> map, LoadingDialog dialog) {
        model.resetPassword(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.resetPassword(httpBean);
            }
        });
    }
}
