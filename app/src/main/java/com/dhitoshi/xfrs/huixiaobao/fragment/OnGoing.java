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
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.OnGoingManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ApplyAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.OnGoingPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.ApplyMeetingInfo;
import com.dhitoshi.xfrs.huixiaobao.view.EditApplyMeet;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OnGoing extends BaseFragment implements OnGoingManage.View {

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
    private OnGoingPresenter onGoingPresenter;
    private List<ApplyMeetBean> applyMeetBeens;
    private ApplyAdapter adapter;
    public OnGoing() {

    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
    }
    public static OnGoing newInstance() {
        OnGoing fragment = new OnGoing();
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
        onGoingPresenter=new OnGoingPresenter(this,getContext());
        applyMeetBeens=new ArrayList<>();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                applyMeetBeens.removeAll(applyMeetBeens);
                page=1;
                onGoingPresenter.getMeetForNow(SharedPreferencesUtil.Obtain(getContext(),"token","").toString(),String.valueOf(page),smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                onGoingPresenter.getMeetForNow(SharedPreferencesUtil.Obtain(getContext(),"token","").toString(),String.valueOf(page),smartRefreshLayout);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getMeetForNow(HttpBean<PageBean<ApplyMeetBean>> httpBean) {
        applyMeetBeens.addAll(httpBean.getData().getList());
        int size = applyMeetBeens.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        smartRefreshLayout.setVisibility(size==0?View.GONE:View.VISIBLE);
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == adapter) {
            adapter = new ApplyAdapter(applyMeetBeens, getContext());
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<ApplyMeetBean>() {
                @Override
                public void onItemClick(View view, ApplyMeetBean applyMeetBean, int position) {
                    startActivity(new Intent(getContext(), ApplyMeetingInfo.class).putExtra("apply",applyMeetBean).putExtra("type",1));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
