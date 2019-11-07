package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.TribeManage;
import com.dhitoshi.xfrs.huixiaobao.model.TribeModel;
import java.util.Map;
/**
 * Created by dxs on 2017/9/6.
 */
public class TribePresenter implements TribeManage.Presenter{
    private TribeManage.View view;
    private TribeModel tribeModel;
    public TribePresenter(TribeManage.View view, Context context) {
        this.view = view;
        tribeModel=new TribeModel(context);
    }
    @Override
    public void addTribe(Map<String, String> map, LoadingDialog dialog) {
        tribeModel.addTribe(map, dialog, new Callback<HttpBean<TribeBean>>() {
            @Override
            public void get(HttpBean<TribeBean> httpBean) {
                view.addTribe(httpBean);
            }
        });
    }
    @Override
    public void editTribe(Map<String, String> map, LoadingDialog dialog) {
        tribeModel.editTribe(map, dialog, new Callback<HttpBean<TribeBean>>() {
            @Override
            public void get(HttpBean<TribeBean> httpBean) {
                view.editTribe(httpBean);
            }
        });
    }
}
