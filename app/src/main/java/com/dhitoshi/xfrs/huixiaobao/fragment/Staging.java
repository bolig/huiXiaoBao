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
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.StagingManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.OwnMeetAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.StagingPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.EditApplyMeet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Staging extends BaseFragment implements StagingManage.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.error)
    RelativeLayout error;
    Unbinder unbinder;
    private int page=1;
    private StagingPresenter stagingPresenter;
    private List<OwnMeetBean> ownMeetBeens;
    private OwnMeetAdapter adapter;
    public Staging() {

    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
    }
    public static Staging newInstance() {
        Staging fragment = new Staging();
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
        stagingPresenter=new StagingPresenter(this,getContext());
        ownMeetBeens=new ArrayList<>();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                ownMeetBeens.removeAll(ownMeetBeens);
                page=1;
                stagingPresenter.getListForNow(SharedPreferencesUtil.Obtain(getContext(),"token","").toString(),String.valueOf(page),smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                stagingPresenter.getListForNow(SharedPreferencesUtil.Obtain(getContext(),"token","").toString(),String.valueOf(page),smartRefreshLayout);
            }
        });
    }

    @Override
    public void getListForNow(HttpBean<HttpPageBean<OwnMeetBean>> httpBean) {
        ownMeetBeens.addAll(httpBean.getData().getList());
        int size = ownMeetBeens.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == adapter) {
            adapter = new OwnMeetAdapter(ownMeetBeens, getContext());
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<OwnMeetBean>() {
                @Override
                public void onItemClick(View view, OwnMeetBean ownMeetBean, int position) {
                    startActivity(new Intent(getContext(), EditApplyMeet.class).putExtra("own",ownMeetBean).putExtra("type",1));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
