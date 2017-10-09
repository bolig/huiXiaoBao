package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.ProductEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SetProductAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SwipeItemLayout;
import com.dhitoshi.xfrs.huixiaobao.presenter.ProductPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.error)
    RelativeLayout error;
    private ProductPresenter productPresenter;
    private SetProductAdapter adapter;
    private SwipeItemLayout.OnSwipeItemTouchListener listener;
    private int deletePosition = -1;
    private List<ProductBean> products;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"Product");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityManagerUtil.destoryActivity("Product");
    }

    private void initViews() {
        initBaseViews();
        setTitle("产品");
        setRightIcon(R.mipmap.add);
        products = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listener = new SwipeItemLayout.OnSwipeItemTouchListener(this);
        recyclerView.addOnItemTouchListener(listener);
        smartRefreshLayout.autoRefresh();
        productPresenter = new ProductPresenter(this, this);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                products.removeAll(products);
                page = 1;
                String token= SharedPreferencesUtil.Obtain(Product.this,"token","").toString();
                productPresenter.getItem(token,String.valueOf(page), smartRefreshLayout);
            }
        });

        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                String token= SharedPreferencesUtil.Obtain(Product.this,"token","").toString();
                productPresenter.getItem(token,String.valueOf(page), smartRefreshLayout);
            }
        });
    }

    @Override
    public void getItem(HttpBean<PageBean<ProductBean>> httpBean) {
        products.addAll(httpBean.getData().getList());
        int size = products.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0? View.VISIBLE:View.GONE);
        if (adapter == null) {
            adapter = new SetProductAdapter(products, this);
            recyclerView.setAdapter(adapter);
            adapter.addDeleteCallback(new DeleteCallback() {
                @Override
                public void delete(int id, int position) {
                    deletePosition = position;
                    String token = SharedPreferencesUtil.Obtain(Product.this, "token", "").toString();
                    LoadingDialog dialog = LoadingDialog.build(Product.this).setLoadingTitle("删除中");
                    dialog.show();
                    productPresenter.deleteItem(token, String.valueOf(id), dialog);
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteItem(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        products.remove(deletePosition);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this, AddProduct.class));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ProductEvent event) {
        switch (event.getState()) {
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
