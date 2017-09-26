package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.model.AreaModel;
import java.util.List;
/**
 * Created by dxs on 2017/9/6.
 */
public class AreaPresenter implements AreaManage.Presenter{
    private AreaManage.View view;
    private AreaModel areaModel;
    public AreaPresenter(AreaManage.View view, Context context) {
        this.view = view;
        areaModel=new AreaModel(context);
    }
    @Override
    public void getAreaLists(String token) {
        areaModel.getAreaLists(token,new Callback<HttpBean<List<AreaBean>>>() {
            @Override
            public void get(HttpBean<List<AreaBean>> httpBean) {
                view.getAreaLists(httpBean);
            }
        });
    }
}
