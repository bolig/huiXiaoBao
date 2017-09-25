package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.AddAreaOneEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SetAreaManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AddAreaOneAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.SwipeItemLayout;
import com.dhitoshi.xfrs.huixiaobao.presenter.SetAreaPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddAreaOne extends BaseView implements SetAreaManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private AddAreaOneAdapter adapter;
    private SetAreaPresenter setAreaPresenter;
    private SwipeItemLayout.OnSwipeItemTouchListener listener;
    private int deletePosition=-1;
    private List<AreaBean> areaBeens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_areaother);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("地区");
        setRightIcon(R.mipmap.add);
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
                setAreaPresenter.getAreaLists(smartRefreshLayout);
            }
        });

    }
    @Override
    public void getAreaLists(HttpBean<List<AreaBean>> httpBean) {
        areaBeens.addAll(httpBean.getData());
        if(adapter==null){
            adapter=new AddAreaOneAdapter(areaBeens,this);
            recyclerView.setAdapter(adapter);
            adapter.addDeleteCallback(new DeleteCallback() {
                @Override
                public void delete(int id, int position) {
                    deletePosition=position;
                    String token= SharedPreferencesUtil.Obtain(AddAreaOne.this,"token","").toString();
                    LoadingDialog dialog = LoadingDialog.build(AddAreaOne.this).setLoadingTitle("删除中");
                    dialog.show();
                    setAreaPresenter.deleteArea(String.valueOf(id),token,dialog);
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

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this,AddArea.class).putExtra("type",0).putExtra("parent_id","0"));
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddAreaOneEvent event) {
        switch (event.getState()){
            case 1:
                addAreaBean(event.getMap());
                break;
            case 2:
                updateAreaBean(event.getMap());
                break;
        }
    }

    private void updateAreaBean(Map<String, String> map) {
        int id=Integer.parseInt(map.get("id"));
        for (int i = 0; i < areaBeens.size(); i++) {
            if(areaBeens.get(i).getId()==id){
                AreaBean areaBean=areaBeens.get(i);
                areaBean.setName(map.get("name"));
                areaBean.setId(Integer.parseInt(map.get("id")));
                areaBean.setNotes(map.get("notes"));
                areaBean.setAddress(map.get("address"));
                areaBean.setAdmin(map.get("admin"));
                areaBean.setPhone(map.get("phone"));
                areaBean.setParent_id(map.get("parent_id"));
                areaBean.setIf_repeat(Integer.parseInt(map.get("if_repeat")));
                areaBean.setIs_employee(Integer.parseInt(map.get("is_employee")));
                areaBean.setKid(new ArrayList<KidBean>());
            }
        }
        adapter.notifyDataSetChanged();
    }
    private void addAreaBean(Map<String, String> map) {
        AreaBean areaBean=new AreaBean();
        areaBean.setName(map.get("name"));
        areaBean.setId(Integer.parseInt(map.get("id")));
        areaBean.setNotes(map.get("notes"));
        areaBean.setAddress(map.get("address"));
        areaBean.setAdmin(map.get("admin"));
        areaBean.setPhone(map.get("phone"));
        areaBean.setParent_id(map.get("parent_id"));
        areaBean.setIf_repeat(Integer.parseInt(map.get("if_repeat")));
        areaBean.setIs_employee(Integer.parseInt(map.get("is_employee")));
        areaBean.setKid(new ArrayList<KidBean>());
        areaBeens.add(areaBean);
        adapter.notifyDataSetChanged();
    }
}
