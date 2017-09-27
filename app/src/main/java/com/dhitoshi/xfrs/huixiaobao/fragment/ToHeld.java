package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.ToHeldManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.OwnMeetAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.ToHeldPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.ApplyMeetingInfo;
import com.dhitoshi.xfrs.huixiaobao.view.EditApplyMeet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ToHeld extends BaseFragment implements ToHeldManage.View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.error)
    RelativeLayout error;
    Unbinder unbinder;
    private int page = 1;
    private ToHeldPresenter toHeldPresenter;
    private List<OwnMeetBean> ownMeetBeens;
    private OwnMeetAdapter adapter;
    public ToHeld() {
    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
    }
    public static ToHeld newInstance() {
        ToHeld fragment = new ToHeld();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.staging, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        toHeldPresenter=new ToHeldPresenter(this,getContext());
        ownMeetBeens=new ArrayList<>();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                ownMeetBeens.removeAll(ownMeetBeens);
                page=1;
                toHeldPresenter.getListForFuture(SharedPreferencesUtil.Obtain(getContext(),"token","").toString(),String.valueOf(page),smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                toHeldPresenter.getListForFuture(SharedPreferencesUtil.Obtain(getContext(),"token","").toString(),String.valueOf(page),smartRefreshLayout);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getListForFuture(HttpBean<PageBean<OwnMeetBean>> httpBean) {
        ownMeetBeens=httpBean.getData().getList();
        int size = ownMeetBeens.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        smartRefreshLayout.setVisibility(size==0?View.GONE:View.VISIBLE);
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == adapter) {
            adapter = new OwnMeetAdapter(ownMeetBeens, getContext());
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<OwnMeetBean>() {
                @Override
                public void onItemClick(View view, OwnMeetBean ownMeetBean, int position) {
                    startActivity(new Intent(getContext(), EditApplyMeet.class).putExtra("own",ownMeetBean).putExtra("type",2));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
