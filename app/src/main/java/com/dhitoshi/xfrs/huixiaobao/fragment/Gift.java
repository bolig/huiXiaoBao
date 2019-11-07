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
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Event.GiftEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.GiftManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.GiftAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.GiftPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.AddGift;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//赠品
public class Gift extends BaseFragment implements GiftManage.View {
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
    private GiftPresenter giftPresenter;
    private List<GiftBean> gifts;
    private GiftAdapter adapter;

    public Gift() {

    }

    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
    }

    public static Gift newInstance(int id) {
        Gift fragment = new Gift();
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
        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initViews();
        return view;
    }

    private void initViews() {
        gifts = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        giftPresenter = new GiftPresenter(this, getContext());
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                gifts.removeAll(gifts);
                page = 1;
                String token= SharedPreferencesUtil.Obtain(getContext(),"token","").toString();
                giftPresenter.getGiftLists(token,String.valueOf(id), String.valueOf(page), smartRefreshLayout);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                String token= SharedPreferencesUtil.Obtain(getContext(),"token","").toString();
                giftPresenter.getGiftLists(token,String.valueOf(id), String.valueOf(page), smartRefreshLayout);
            }
        });
    }

    @Override
    public void getGiftLists(HttpPageBean<GiftBean> httpPageBean) {
        gifts.addAll(httpPageBean.getList());
        int size = gifts.size();
        empty.setVisibility(gifts.size()==0?View.VISIBLE:View.GONE);
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        if (adapter == null) {
            adapter = new GiftAdapter(gifts, getContext());
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<GiftBean>() {
                @Override
                public void onItemClick(View view, GiftBean giftBean, int position) {
                    startActivity(new Intent(getContext(), AddGift.class)
                            .putExtra("gift", giftBean).putExtra("id", id));
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
    public void onEventMainThread(GiftEvent event) {
        switch (event.getState()) {
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
