package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.QueryResultBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Event.ClientEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.QueryResultEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryResultManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.GiftAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.MeetingAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.QueryClientAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.RelationAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.SpendAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.VisitAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.SerializableMap;
import com.dhitoshi.xfrs.huixiaobao.presenter.QueryResultPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryResult extends BaseView implements QueryResultManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.error)
    RelativeLayout error;
    private String type;
    private int page = 1;
    private SerializableMap map;
    private QueryResultPresenter queryResultPresenter;
    private List<QueryResultBean<ClientBean>> clients;
    private QueryClientAdapter queryClientAdapter;
    private List<SpendBean> spends;
    private SpendAdapter spendAdapter;
    private List<VisitBean> visits;
    private VisitAdapter visitAdapter;
    private List<GiftBean> gifts;
    private GiftAdapter giftAdapter;
    private List<RelationBean> relations;
    private RelationAdapter relationAdapter;
    private List<MeetBean> meets;
    private MeetingAdapter meetingAdapter;
    private List<Integer> userIds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_result);
        ButterKnife.bind(this);
        initViews();
        EventBus.getDefault().register(this);
        userIds=new ArrayList<>();
        switch (type) {
            case "1":
                recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
                clients=new ArrayList<>();
                break;
            case "2":
               spends=new ArrayList<>();
                break;
            case "3":
                visits=new ArrayList<>();
                break;
            case "4":
                relations=new ArrayList<>();
                break;
            case "5":
                gifts=new ArrayList<>();
                break;
            case "6":
                meets=new ArrayList<>();
                break;
        }
        ActivityManagerUtil.addDestoryActivity(this,"QueryResult");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityManagerUtil.destoryActivity("QueryResult");
    }

    private void initViews() {
        initBaseViews();
        setTitle("高级查询");
        Bundle bundle = getIntent().getBundleExtra("bundle");
        type = bundle.getString("type");
        map = bundle.getParcelable("map");
        queryResultPresenter = new QueryResultPresenter(this, this);
        smartRefreshLayout.autoRefresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                loadData(1);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                loadData(2);
            }
        });
    }

    private void loadData(int state) {
        map.getMap().put("page", String.valueOf(page));
        switch (type) {
            case "1":
                if(state==1){
                    userIds.removeAll(userIds);
                    clients.removeAll(clients);
                }
                queryResultPresenter.getSearchOne(map.getMap(), smartRefreshLayout);
                break;
            case "2":
                if(state==1){
                    userIds.removeAll(userIds);
                    spends.removeAll(spends);
                }
                queryResultPresenter.getSearchTwo(map.getMap(), smartRefreshLayout);
                break;
            case "3":
                if(state==1){
                    userIds.removeAll(userIds);
                    visits.removeAll(visits);
                }
                queryResultPresenter.getSearchThree(map.getMap(), smartRefreshLayout);
                break;
            case "4":
                if(state==1){
                    userIds.removeAll(userIds);
                    relations.removeAll(relations);
                }
                queryResultPresenter.getSearchFour(map.getMap(), smartRefreshLayout);
                break;
            case "5":
                if(state==1){
                    userIds.removeAll(userIds);
                    gifts.removeAll(gifts);
                }
                queryResultPresenter.getSearchFive(map.getMap(), smartRefreshLayout);
                break;
            case "6":
                if(state==1){
                    userIds.removeAll(userIds);
                    meets.removeAll(meets);
                }
                queryResultPresenter.getSearchSix(map.getMap(), smartRefreshLayout);
                break;
        }
    }

    @Override
    public void getSearchOne(HttpBean<PageBean<QueryResultBean<ClientBean>>> httpBean) {
        clients.addAll(httpBean.getData().getList());
        int size = clients.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size == 0 ? View.VISIBLE : View.GONE);
        if (null == queryClientAdapter) {
            queryClientAdapter = new QueryClientAdapter(clients, this);
            recyclerView.setAdapter(queryClientAdapter);
            queryClientAdapter.addItemClickListener(new ItemClick<QueryResultBean<ClientBean>>() {
                @Override
                public void onItemClick(View view, QueryResultBean<ClientBean> queryResultBean, int position) {
                    ClientBean clientBean=new ClientBean();
                    clientBean.setPhone(queryResultBean.getPhone());
                    clientBean.setName(queryResultBean.getName());
                    clientBean.setEntryman(queryResultBean.getEntryman());
                    clientBean.setAddress(queryResultBean.getAddress());
                    clientBean.setArea(queryResultBean.getArea());
                    clientBean.setArea_id(queryResultBean.getArea_id());
                    clientBean.setBacktime(queryResultBean.getBacktime());
                    clientBean.setBirthday(queryResultBean.getBirthday());
                    clientBean.setBuytime(queryResultBean.getBuytime());
                    clientBean.setCompany(queryResultBean.getCompany());
                    clientBean.setCompany_address(queryResultBean.getCompany_address());
                    clientBean.setCompany_phone(queryResultBean.getCompany_phone());
                    clientBean.setCreatetime(queryResultBean.getCreatetime());
                    clientBean.setHead(queryResultBean.getHead());
                    clientBean.setHobby(queryResultBean.getHobby());
                    clientBean.setId(queryResultBean.getId());
                    clientBean.setIll(queryResultBean.getIll());
                    clientBean.setNotes(queryResultBean.getNotes());
                    clientBean.setEmail(queryResultBean.getEmail());
                    clientBean.setPosition(queryResultBean.getPosition());
                    clientBean.setSex(queryResultBean.getSex());
                    clientBean.setTelephone(queryResultBean.getTelephone());
                    clientBean.setType(queryResultBean.getType());
                    clientBean.setTotalnum(queryResultBean.getTotalnum());
                    clientBean.setVip_id(queryResultBean.getVip_id());
                    startActivity(new Intent(QueryResult.this, ClientInfo.class).putExtra("info", clientBean));
                }
            });
        } else {
            queryClientAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSearchTwo(final HttpBean<PageBean<QueryResultBean<SpendBean>>> httpBean) {
        int size=httpBean.getData().getList().size();
        for (int i = 0; i < size; i++) {
            spends.addAll(httpBean.getData().getList().get(i).getResult());
            int length=httpBean.getData().getList().get(i).getResult().size();
            for (int j= 0; j < length; j++) {
                userIds.add(httpBean.getData().getList().get(i).getId());
            }
        }
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (spendAdapter == null) {
            spendAdapter = new SpendAdapter(spends, this);
            recyclerView.setAdapter(spendAdapter);
            spendAdapter.addItemClickListener(new ItemClick<SpendBean>() {
                @Override
                public void onItemClick(View view, SpendBean spendBean, int position) {
                    startActivity(new Intent(QueryResult.this, AddSpend.class).putExtra("spend", spendBean)
                            .putExtra("id", userIds.get(position)).putExtra("type",1));
                }
            });
        } else {
            spendAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSearchThree(HttpBean<PageBean<QueryResultBean<VisitBean>>> httpBean) {
        int size=httpBean.getData().getList().size();
        for (int i = 0; i < size; i++) {
            visits.addAll(httpBean.getData().getList().get(i).getResult());
            int length=httpBean.getData().getList().get(i).getResult().size();
            for (int j= 0; j < length; j++) {
                userIds.add(httpBean.getData().getList().get(i).getId());
            }
        }
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (visitAdapter == null) {
            visitAdapter = new VisitAdapter(visits,this);
            recyclerView.setAdapter(visitAdapter);
            visitAdapter.addItemClickListener(new ItemClick<VisitBean>() {
                @Override
                public void onItemClick(View view, VisitBean visitBean, int position) {
                    startActivity(new Intent(QueryResult.this, AddVisit.class).putExtra("visit", visitBean)
                            .putExtra("id", userIds.get(position)).putExtra("type",1));
                }
            });
        } else {
            visitAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSearchFour(HttpBean<PageBean<QueryResultBean<RelationBean>>> httpBean) {
        int size=httpBean.getData().getList().size();
        for (int i = 0; i < size; i++) {
            relations.addAll(httpBean.getData().getList().get(i).getResult());
            int length=httpBean.getData().getList().get(i).getResult().size();
            for (int j= 0; j < length; j++) {
                userIds.add(httpBean.getData().getList().get(i).getId());
            }
        }
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == relationAdapter) {
            relationAdapter = new RelationAdapter(relations,this);
            recyclerView.setAdapter(relationAdapter);
            relationAdapter.addItemClickListener(new ItemClick<RelationBean>() {
                @Override
                public void onItemClick(View view, RelationBean relationBean, int position) {
                    startActivity(new Intent(QueryResult.this, AddRelation.class).putExtra("relation", relationBean)
                            .putExtra("id", userIds.get(position)).putExtra("type",1));
                }
            });
        } else {
            relationAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getSearchFive(HttpBean<PageBean<QueryResultBean<GiftBean>>> httpBean) {
        int size=httpBean.getData().getList().size();
        for (int i = 0; i < size; i++) {
            gifts.addAll(httpBean.getData().getList().get(i).getResult());
            int length=httpBean.getData().getList().get(i).getResult().size();
            for (int j= 0; j < length; j++) {
                userIds.add(httpBean.getData().getList().get(i).getId());
            }
        }
        empty.setVisibility(gifts.size()==0?View.VISIBLE:View.GONE);
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        if (giftAdapter == null) {
            giftAdapter = new GiftAdapter(gifts, this);
            recyclerView.setAdapter(giftAdapter);
            giftAdapter.addItemClickListener(new ItemClick<GiftBean>() {
                @Override
                public void onItemClick(View view, GiftBean giftBean, int position) {
                    startActivity(new Intent(QueryResult.this, AddGift.class).putExtra("gift", giftBean)
                            .putExtra("id", userIds.get(position)).putExtra("type",1));
                }
            });
        } else {
            giftAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSearchSix(HttpBean<PageBean<QueryResultBean<MeetBean>>> httpBean) {
        int size=httpBean.getData().getList().size();
        for (int i = 0; i < size; i++) {
            meets.addAll(httpBean.getData().getList().get(i).getResult());
            int length=httpBean.getData().getList().get(i).getResult().size();
            for (int j= 0; j < length; j++) {
                userIds.add(httpBean.getData().getList().get(i).getId());
            }
        }
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == meetingAdapter) {
            meetingAdapter = new MeetingAdapter(meets, this);
            recyclerView.setAdapter(meetingAdapter);
            meetingAdapter.addItemClickListener(new ItemClick<MeetBean>() {
                @Override
                public void onItemClick(View view, MeetBean meetBean, int position) {
                    startActivity(new Intent(QueryResult.this, AddMeeting.class).putExtra("meet", meetBean)
                            .putExtra("id", userIds.get(position)).putExtra("type",1));
                }
            });
        } else {
            meetingAdapter.notifyDataSetChanged();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(QueryResultEvent event) {
        switch (event.getState()) {
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
