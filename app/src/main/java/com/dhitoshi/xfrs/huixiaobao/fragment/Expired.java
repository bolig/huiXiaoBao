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
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ExpiredManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.StagingManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ApplyAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.MeetingAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.ExpiredPresenter;
import com.dhitoshi.xfrs.huixiaobao.presenter.StagingPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.AddMeeting;
import com.dhitoshi.xfrs.huixiaobao.view.ApplyMeetingInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Expired extends BaseFragment implements ExpiredManage.View {

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
    private ExpiredPresenter expiredPresenter;
    private List<ApplyMeetBean> applyMeetBeens;
    private ApplyAdapter adapter;
    public Expired() {

    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
    }
    public static Expired newInstance() {
        Expired fragment = new Expired();
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
        expiredPresenter=new ExpiredPresenter(this,getContext());
        applyMeetBeens=new ArrayList<>();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                applyMeetBeens.removeAll(applyMeetBeens);
                page=1;
                expiredPresenter.getMeetForPast(SharedPreferencesUtil.Obtain(getContext(),"token","").toString(),String.valueOf(page),smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                expiredPresenter.getMeetForPast(SharedPreferencesUtil.Obtain(getContext(),"token","").toString(),String.valueOf(page),smartRefreshLayout);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getMeetForPast(HttpBean<PageBean<ApplyMeetBean>> httpBean) {
        applyMeetBeens.addAll(httpBean.getData().getList());
        int size = applyMeetBeens.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == adapter) {
            adapter = new ApplyAdapter(applyMeetBeens, getContext());
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<ApplyMeetBean>() {
                @Override
                public void onItemClick(View view, ApplyMeetBean applyMeetBean, int position) {
                    startActivity(new Intent(getContext(), ApplyMeetingInfo.class).putExtra("apply",applyMeetBean).putExtra("type",2));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
