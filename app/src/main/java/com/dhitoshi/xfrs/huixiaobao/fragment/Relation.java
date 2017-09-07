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
public class Relation extends Fragment implements RelationManage.View{
    public Relation() {
    }
    public static Relation newInstance() {
        Relation fragment = new Relation();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_relation, container, false);
    }

    @Override
    public void getRelationLists(PageBean<RelationBean> pageBean) {

    }
}
