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
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Event.VisitEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.VisitManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.VisitAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.VisitPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.AddVisit;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//回访
public class Visit extends BaseFragment implements VisitManage.View {
    private static final String ARG_ID = "id";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.error)
    RelativeLayout error;
    private int id;
    private int page = 1;
    private VisitPresenter visitPresenter;
    private List<VisitBean> visits;
    private VisitAdapter adapter;

    public Visit() {
    }

    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
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
        View view = inflater.inflate(R.layout.fragment_visit, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initViews();
        return view;
    }

    private void initViews() {
        visits = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        visitPresenter = new VisitPresenter(this, getContext());
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                visits.removeAll(visits);
                page = 1;
                String token= SharedPreferencesUtil.Obtain(getContext(),"token","").toString();
                visitPresenter.getFeedbackLists(token,String.valueOf(id), String.valueOf(page), smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                String token= SharedPreferencesUtil.Obtain(getContext(),"token","").toString();
                visitPresenter.getFeedbackLists(token,String.valueOf(id), String.valueOf(page), smartRefreshLayout);
            }
        });
    }

    @Override
    public void getFeedbackLists(PageBean<VisitBean> pageBean) {
        visits.addAll(pageBean.getList());
        int size = visits.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        smartRefreshLayout.setVisibility(size==0?View.GONE:View.VISIBLE);
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (adapter == null) {
            adapter = new VisitAdapter(visits, getContext());
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<VisitBean>() {
                @Override
                public void onItemClick(View view, VisitBean visitBean, int position) {
                    startActivity(new Intent(getContext(), AddVisit.class)
                            .putExtra("visit", visitBean).putExtra("id", id));
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
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(VisitEvent event) {
        switch (event.getState()) {
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
