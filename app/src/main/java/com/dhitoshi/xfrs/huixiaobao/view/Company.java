package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.SetAreaManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.CompanyAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.SwipeItemLayout;
import com.dhitoshi.xfrs.huixiaobao.presenter.SetAreaPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Company extends BaseView implements SetAreaManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private CompanyAdapter adapter;
    private SetAreaPresenter setAreaPresenter;
    private SwipeItemLayout.OnSwipeItemTouchListener listener;
    private int deletePosition=-1;
    private List<AreaBean> areaBeens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_areaother);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"Company");
    }
    private void initViews() {
        initBaseViews();
        setTitle("部门");
        areaBeens=new ArrayList<>();
        smartRefreshLayout.autoRefresh();
        setAreaPresenter=new SetAreaPresenter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        listener = new SwipeItemLayout.OnSwipeItemTouchListener(this);
        recyclerView.addOnItemTouchListener(listener);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                areaBeens.removeAll(areaBeens);
                String token= SharedPreferencesUtil.Obtain(Company.this,"token","").toString();
                setAreaPresenter.getAreaLists(token,smartRefreshLayout);
            }
        });

    }
    @Override
    public void getAreaLists(HttpBean<List<AreaBean>> httpBean) {
        areaBeens.addAll(httpBean.getData());
        if(adapter==null){
            adapter=new CompanyAdapter(areaBeens,this);
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<AreaBean>() {
                @Override
                public void onItemClick(View view, AreaBean areaBean, int position) {
                    startActivity(new Intent(Company.this,Department.class)
                            .putExtra("title",areaBean.getName())
                            .putExtra("parent_id",areaBean.getId())
                            .putExtra("kid",(ArrayList)areaBean.getKid()));
                }
            });
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteArea(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        areaBeens.remove(deletePosition);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("Company");
    }

}
