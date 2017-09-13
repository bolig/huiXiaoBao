package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//relation_select
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.RelationManage;
import com.dhitoshi.xfrs.huixiaobao.R;

import io.reactivex.disposables.Disposable;

public class Relation extends BaseFragment implements RelationManage.View{
    private static final String ARG_ID = "id";
    private int id;
    public Relation() {
    }

    @Override
    public void loadData() {

    }

    public static Relation newInstance(int id) {
        Relation fragment = new Relation();
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
        return inflater.inflate(R.layout.fragment_relation, container, false);
    }



    @Override
    public void getRelationLists(PageBean<RelationBean> pageBean) {

    }
}
