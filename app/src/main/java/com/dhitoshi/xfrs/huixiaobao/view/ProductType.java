package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductTypeBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.ProductEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.ProductTypeEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ProductTypeManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SetTypeAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SwipeItemLayout;
import com.dhitoshi.xfrs.huixiaobao.presenter.ProductTypePresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

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
    private int page=1;
    private int deletePosition=-1;
    private List<BaseBean> productTypes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_type);
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
        setTitle("产品类型");
        setRightIcon(R.mipmap.add);
        productTypes=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listener = new SwipeItemLayout.OnSwipeItemTouchListener(this);
        recyclerView.addOnItemTouchListener(listener);
        smartRefreshLayout.autoRefresh();
        productTypePresenter=new ProductTypePresenter(this,this);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                productTypes.removeAll(productTypes);
                page=1;
                productTypePresenter.getItemType(String.valueOf(page),smartRefreshLayout);
            }
        });
        int size=productTypes.size();
        if(size>=10&&size%10==0){
            smartRefreshLayout.setEnableLoadmore(true);
        }else{
            smartRefreshLayout.setEnableLoadmore(false);
        }
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                productTypePresenter.getItemType(String.valueOf(page),smartRefreshLayout);
            }
        });
    }
    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this,AddProductType.class));
    }
    @Override
    public void getItemType(HttpBean<PageBean<BaseBean>> httpBean) {
        productTypes.addAll(httpBean.getData().getList());
        if(adapter==null){
            adapter=new SetTypeAdapter(productTypes,this);
            recyclerView.setAdapter(adapter);
            adapter.addDeleteCallback(new DeleteCallback() {
                @Override
                public void delete(int id,int position) {
                    deletePosition=position;
                    String token= SharedPreferencesUtil.Obtain(ProductType.this,"token","").toString();
                    LoadingDialog dialog = LoadingDialog.build(ProductType.this).setLoadingTitle("删除中");
                    dialog.show();
                    productTypePresenter.deleteItemType(token,String.valueOf(id),dialog);
                }
            });
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteItemType(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        productTypes.remove(deletePosition);
        adapter.notifyDataSetChanged();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ProductTypeEvent event) {
        switch (event.getState()){
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
