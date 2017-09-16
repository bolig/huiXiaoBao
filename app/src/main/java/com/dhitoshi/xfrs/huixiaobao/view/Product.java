package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SetProductAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.ProductPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Product extends BaseView implements ProductManage.View {
    @BindView(R.id.right_icon)
    AppCompatImageView rightIcon;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private ProductPresenter productPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        setTitle("产品");
        setRightIcon(R.mipmap.add);
        productPresenter = new ProductPresenter(this);
        productPresenter.getItem();
    }

    @Override
    public void getItem(HttpBean<List<ProductBean>> httpBean) {
        SetProductAdapter adapter = new SetProductAdapter(httpBean.getData(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this,AddProduct.class));
    }
}
