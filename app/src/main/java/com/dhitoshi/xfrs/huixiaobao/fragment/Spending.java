package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Event.SpendEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.SpendManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SpendAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.SpendPresenter;
import com.dhitoshi.xfrs.huixiaobao.view.AddSpend;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

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
    private SpendPresenter spendPresenter;
    private int page=1;
    private List<SpendBean> spends;
    private SpendAdapter adapter;
    public Spending() {
    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
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
        EventBus.getDefault().register(this);
        initViews();
        return view;
    }
    private void initViews() {
        spends=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        spendPresenter = new SpendPresenter(this,getContext());
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                spends.removeAll(spends);
                page=1;
                spendPresenter.getSpendingLists(String.valueOf(id), String.valueOf(page),smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                spendPresenter.getSpendingLists(String.valueOf(id), String.valueOf(page),smartRefreshLayout);
            }
        });
    }
    @Override
    public void getSpendingLists(PageBean<SpendBean> pageBean) {
        spends.addAll(pageBean.getList());
        int size=spends.size();
        if(size>=10&&size%10==0){
            smartRefreshLayout.setEnableLoadmore(true);
        }else{
            smartRefreshLayout.setEnableLoadmore(false);
        }
        if(adapter==null){
            adapter = new SpendAdapter(spends, getContext());
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<SpendBean>() {
                @Override
                public void onItemClick(View view, SpendBean spendBean, int position) {
                    startActivity(new Intent(getContext(), AddSpend.class)
                            .putExtra("spend",spendBean).putExtra("id",id));
                }
            });
        }else{
            adapter.notifyDataSetChanged();
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(SpendEvent event) {
        switch (event.getState()){
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
