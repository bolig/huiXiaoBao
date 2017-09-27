package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoQuery;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryResultManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.SerializableMap;
import com.dhitoshi.xfrs.huixiaobao.presenter.QueryResultPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
public class QueryResult extends BaseView implements QueryResultManage.View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String type;
    private int page=1;
    private SerializableMap map;
    private QueryResultPresenter queryResultPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_result);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("高级查询");
        Bundle bundle=getIntent().getBundleExtra("bundle");
        type=bundle.getString("type");
        map=bundle.getParcelable("map");
        queryResultPresenter=new QueryResultPresenter(this,this);
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                loadData();
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                loadData();
            }
        });
    }

    private void loadData() {
        map.getMap().put("page",String.valueOf(page));
        switch (type){
            case "1":
                queryResultPresenter.getSearchOne(map.getMap(),smartRefreshLayout);
                break;
            case "2":
                queryResultPresenter.getSearchOne(map.getMap(),smartRefreshLayout);
                break;
            case "3":
                queryResultPresenter.getSearchOne(map.getMap(),smartRefreshLayout);
                break;
            case "4":
                queryResultPresenter.getSearchOne(map.getMap(),smartRefreshLayout);
                break;
            case "5":
                queryResultPresenter.getSearchOne(map.getMap(),smartRefreshLayout);
                break;
            case "6":
                queryResultPresenter.getSearchOne(map.getMap(),smartRefreshLayout);
                break;
        }
    }


    @Override
    public void getSearchOne(HttpBean<PageBean<ClientBean>> httpBean) {

    }

    @Override
    public void getSearchTwo(HttpBean<PageBean<SpendBean>> httpBean) {

    }

    @Override
    public void getSearchThree(HttpBean<PageBean<VisitBean>> httpBean) {

    }

    @Override
    public void getSearchFour(HttpBean<PageBean<RelationBean>> httpBean) {

    }

    @Override
    public void getSearchFive(HttpBean<PageBean<GiftBean>> httpBean) {

    }

    @Override
    public void getSearchSix(HttpBean<PageBean<MeetBean>> httpBean) {

    }
}
