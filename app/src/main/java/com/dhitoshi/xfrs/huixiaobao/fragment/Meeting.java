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
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Event.MeetingEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.VisitEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetingManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.MeetingAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.MeetPresenter;
import com.dhitoshi.xfrs.huixiaobao.view.AddMeeting;
import com.dhitoshi.xfrs.huixiaobao.view.AddRelation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//meet
public class Meeting extends BaseFragment implements MeetingManage.View {
    private static final String ARG_ID = "id";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder;
    private int id;
    private int page=1;
    private MeetPresenter meetPresenter;
    public Meeting() {
    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
    }
    public static Meeting newInstance(int id) {
        Meeting fragment = new Meeting();
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
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView();
        return view;
    }

    private void initView() {
        meetPresenter = new MeetPresenter(this,getContext());
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                meetPresenter.getMeetingLists(String.valueOf(id), String.valueOf(page),smartRefreshLayout);
            }
        });
    }
    @Override
    public void getMeetingLists(PageBean<MeetBean> pageBean) {
        MeetingAdapter adapter=new MeetingAdapter(pageBean.getList(),getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<MeetBean>() {
            @Override
            public void onItemClick(View view, MeetBean meetBean, int position) {
                startActivity(new Intent(getContext(), AddMeeting.class)
                        .putExtra("meet",meetBean).putExtra("id",id));
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MeetingEvent event) {
        switch (event.getState()){
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
