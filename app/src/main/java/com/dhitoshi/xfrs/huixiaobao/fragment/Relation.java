package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.RelationManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.RelationAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.RelationPresenter;
import com.dhitoshi.xfrs.huixiaobao.view.AddRelation;
import com.dhitoshi.xfrs.huixiaobao.view.AddSpend;

//relation_select
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Relation extends BaseFragment implements RelationManage.View {
    private static final String ARG_ID = "id";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder;
    private int id;

    public Relation() {
    }
    @Override
    public void loadData() {
        RelationPresenter relationPresenter = new RelationPresenter(this);
        relationPresenter.getRelationLists(String.valueOf(id), "1");
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
        //smartRefreshLayout.setOnRefreshListener()
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relation, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void getRelationLists(PageBean<RelationBean> pageBean) {
        RelationAdapter adapter=new RelationAdapter(pageBean.getList(),getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<RelationBean>() {
            @Override
            public void onItemClick(View view, RelationBean relationBean, int position) {
                startActivity(new Intent(getContext(), AddRelation.class)
                        .putExtra("relation",relationBean).putExtra("id",id));
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
