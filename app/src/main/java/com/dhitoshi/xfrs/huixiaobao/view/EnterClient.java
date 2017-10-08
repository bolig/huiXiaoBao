package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Event.MeetClientEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetClientManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.MeetClientAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.presenter.MeetClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterClient extends BaseView implements MeetClientManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.error)
    RelativeLayout error;
    @BindView(R.id.right_icon)
    AppCompatImageView rightIcon;
    private MeetClientPresenter meetClientPresenter;
    private String meetingid = "";
    private int page = 1;
    private Map<String, String> map;
    private List<MeetClientBean> meetClientBeens;
    private MeetClientAdapter adapter;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_client);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initViews();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initViews() {
        initBaseViews();
        setTitle("参会人员");
        type = getIntent().getIntExtra("type", 0);
        if (type != 3) {
            setRightIcon(R.mipmap.add);
        } else {
            rightIcon.setClickable(false);
        }
        meetingid = getIntent().getStringExtra("id");
        meetClientPresenter = new MeetClientPresenter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        smartRefreshLayout.autoRefresh();
        map = new HashMap<>();
        meetClientBeens = new ArrayList<>();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                meetClientBeens.removeAll(meetClientBeens);
                page = 1;
                map.put("meetingid", meetingid);
                map.put("token", SharedPreferencesUtil.Obtain(EnterClient.this, "token", "").toString());
                map.put("page", String.valueOf(page));
                meetClientPresenter.getCustomerList(map, smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                map.put("meetingid", meetingid);
                map.put("token", SharedPreferencesUtil.Obtain(EnterClient.this, "token", "").toString());
                map.put("page", String.valueOf(page));
                meetClientPresenter.getCustomerList(map, smartRefreshLayout);
            }
        });
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this, AddMeetClient.class).putExtra("id", meetingid));
    }

    @Override
    public void getCustomerList(HttpBean<PageBean<MeetClientBean>> httpBean) {
        meetClientBeens.addAll(httpBean.getData().getList());
        int size = meetClientBeens.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size == 0 ? View.VISIBLE : View.GONE);
        if (null == adapter) {
            adapter = new MeetClientAdapter(meetClientBeens, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(MeetClientEvent event) {
        switch (event.getState()) {
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
