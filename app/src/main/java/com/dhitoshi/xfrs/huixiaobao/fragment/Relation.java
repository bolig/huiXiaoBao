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
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Event.RelationEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.RelationManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.RelationAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.RelationPresenter;
import com.dhitoshi.xfrs.huixiaobao.view.AddRelation;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Relation extends BaseFragment implements RelationManage.View {
    private static final String ARG_ID = "id";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder;
    private int id;
    private int page=1;
    private  RelationPresenter relationPresenter;
    public Relation() {
    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
    }
    public static Relation newInstance(int id) {
        Relation fragment = new Relation();
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
        View view = inflater.inflate(R.layout.fragment_relation, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        initView();
        return view;
    }

    private void initView() {
        relationPresenter = new RelationPresenter(this,getContext());
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                relationPresenter.getRelationLists(String.valueOf(id), String.valueOf(page),smartRefreshLayout);
            }
        });
    }
    @Override
    public void getRelationLists(PageBean<RelationBean> pageBean) {
        RelationAdapter adapter=new RelationAdapter(pageBean.getList(),getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<RelationBean>() {
            @Override
            public void onItemClick(View view, RelationBean relationBean, int position) {
                startActivity(new Intent(getContext(), AddRelation.class)
                        .putExtra("relation",relationBean).putExtra("id",id));
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
    public void onEventMainThread(RelationEvent event) {
        switch (event.getState()){
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
