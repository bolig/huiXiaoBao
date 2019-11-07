package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddAreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AddAreaModel;
import java.util.List;
import java.util.Map;
/**
 * Created by dxs on 2017/9/6.
 */
public class AddAreaPresenter implements AddAreaManage.Presenter{
    private AddAreaManage.View view;
    private AddAreaModel addAreaModel;
    public AddAreaPresenter(AddAreaManage.View view, Context context) {
        this.view = view;
        addAreaModel=new AddAreaModel(context);
    }
    @Override
    public void addArea(Map<String,String> map, LoadingDialog dialog) {
        addAreaModel.addArea(map, dialog, new Callback<HttpBean<KidBean>>() {
            @Override
            public void get(HttpBean<KidBean> httpBean) {
                view.addArea(httpBean);
            }
        });
    }
    @Override
    public void editArea(Map<String,String> map, LoadingDialog dialog) {
        addAreaModel.editArea(map, dialog, new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.editArea(httpBean.getStatus().getMsg());
            }
        });
    }
}
