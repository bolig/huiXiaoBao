package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductTypeManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SetTypeAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SwipeItemLayout;
import com.dhitoshi.xfrs.huixiaobao.presenter.ProductTypePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductType extends BaseView implements ProductTypeManage.View{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private ProductTypePresenter productTypePresenter;
    private SetTypeAdapter adapter;
    private SwipeItemLayout.OnSwipeItemTouchListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_type);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("产品类型");
        setRightIcon(R.mipmap.add);
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setEnableLoadmore(false);
        productTypePresenter=new ProductTypePresenter(this,this);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                productTypePresenter.getItemType(smartRefreshLayout);
            }
        });
    }
    @OnClick(R.id.right_icon)
    public void onViewClicked() {
    }
    @Override
    public void getItemType(HttpBean<PageBean<BaseBean>> httpBean) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SetTypeAdapter(httpBean.getData().getList(),this);
        recyclerView.setAdapter(adapter);
        listener = new SwipeItemLayout.OnSwipeItemTouchListener(this);
        recyclerView.addOnItemTouchListener(listener);
    }
}
