package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
public abstract class BaseFragment extends Fragment {
    private boolean isViewInitiated; //控件是否初始化完成
    private boolean isVisibleToUser; //页面是否可见
    public boolean isDataInitiated; //数据是否加载
    public BaseFragment() {

    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData(false);
    }
    private void prepareFetchData(boolean forceUpdate) {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated || forceUpdate)) {
            loadData();
            isDataInitiated = true;
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData(false);
    }
    public abstract void loadData();
}
