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
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.SpendManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SpendAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.SpendPresenter;
import com.dhitoshi.xfrs.huixiaobao.view.AddSpend;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//消费
public class Spending extends BaseFragment implements SpendManage.View {
    private static final String ARG_ID = "id";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder;
    private int id;

    public Spending() {
    }

    @Override
    public void loadData() {
        SpendPresenter spendPresenter = new SpendPresenter(this);
        spendPresenter.getSpendingLists(String.valueOf(id), "1");
    }

    public static Spending newInstance(int id) {
        Spending fragment = new Spending();
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
        View view = inflater.inflate(R.layout.fragment_spend, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void getSpendingLists(PageBean<SpendBean> pageBean) {
        SpendAdapter adapter = new SpendAdapter(pageBean.getList(), getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<SpendBean>() {
            @Override
            public void onItemClick(View view, SpendBean spendBean, int position) {
                startActivity(new Intent(getContext(), AddSpend.class).putExtra("spend",spendBean));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
