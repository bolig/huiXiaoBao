package com.dhitoshi.xfrs.huixiaobao.presenter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.Callback;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryManage;
import com.dhitoshi.xfrs.huixiaobao.model.QueryModel;
import java.util.Map;

/**
 * Created by dxs on 2017/9/26.
 */

public class QueryPresenter implements QueryManage.Presenter{
    private QueryManage.View view;
    private QueryModel model;

    public QueryPresenter(QueryManage.View view, Context context) {
        this.view = view;
        model=new QueryModel(context);
    }

    @Override
    public void getListForSearch(String token) {
        model.getListForSearch(token,new Callback<HttpBean<Object>>() {
            @Override
            public void get(HttpBean<Object> httpBean) {
                view.getListForSearch(httpBean);
            }
        });
    }

    @Override
    public void getSearchOne(Map<String, String> map, LoadingDialog dialog) {

    }

    @Override
    public void getSearchTwo(Map<String, String> map, LoadingDialog dialog) {

    }

    @Override
    public void getSearchThree(Map<String, String> map, LoadingDialog dialog) {

    }

    @Override
    public void getSearchFour(Map<String, String> map, LoadingDialog dialog) {

    }

    @Override
    public void getSearchFive(Map<String, String> map, LoadingDialog dialog) {

    }

    @Override
    public void getSearchSix(Map<String, String> map, LoadingDialog dialog) {

    }
}
