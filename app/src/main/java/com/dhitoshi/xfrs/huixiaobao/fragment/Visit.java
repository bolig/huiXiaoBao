package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.VisitManage;
import com.dhitoshi.xfrs.huixiaobao.R;

import io.reactivex.disposables.Disposable;

//回访
public class Visit extends BaseFragment implements VisitManage.View{
    private static final String ARG_ID = "id";
    private int id;
    public Visit() {
    }

    @Override
    public void loadData() {

    }

    public static Visit newInstance(int id) {
        Visit fragment = new Visit();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visit, container, false);
    }
    @Override
    public void getFeedbackLists(PageBean<VisitBean> pageBean) {

    }
}
