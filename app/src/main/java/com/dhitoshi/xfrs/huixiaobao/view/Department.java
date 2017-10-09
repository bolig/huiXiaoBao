package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.AddAreaThreeEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SetAreaManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.DepartmentAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.SwipeItemLayout;
import com.dhitoshi.xfrs.huixiaobao.presenter.SetAreaPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
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

public class Department extends BaseView implements SetAreaManage.View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private String title;
    private ArrayList<KidBean> kidBeans;
    private SwipeItemLayout.OnSwipeItemTouchListener listener;
    private DepartmentAdapter adapter;
    private int deletePosition = -1;
    private SetAreaPresenter setAreaPresenter;
    private int parent_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_areaother);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"Department");
    }
    private void initViews() {
        initBaseViews();
        title = getIntent().getStringExtra("title");
        parent_id=getIntent().getIntExtra("parent_id",-1);
        setTitle(title);
        setRightIcon(R.mipmap.add);
        kidBeans = getIntent().getParcelableArrayListExtra("kid");
        setAreaPresenter=new SetAreaPresenter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        listener = new SwipeItemLayout.OnSwipeItemTouchListener(this);
        recyclerView.addOnItemTouchListener(listener);
        adapter = new DepartmentAdapter(kidBeans, this);
        recyclerView.setAdapter(adapter);
        adapter.addDeleteCallback(new DeleteCallback() {
            @Override
            public void delete(int id, int position) {
                deletePosition = position;
                String token = SharedPreferencesUtil.Obtain(Department.this, "token", "").toString();
                LoadingDialog dialog = LoadingDialog.build(Department.this).setLoadingTitle("删除中");
                dialog.show();
                setAreaPresenter.deleteArea(String.valueOf(id), token, dialog);
            }
        });
    }
    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this, AddDepartment.class).putExtra("type",2).putExtra("parent_id",String.valueOf(parent_id)));
    }

    @Override
    public void getAreaLists(HttpBean<List<AreaBean>> httpBean) {

    }

    @Override
    public void deleteArea(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        kidBeans.remove(deletePosition);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ActivityManagerUtil.destoryActivity("Department");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddAreaThreeEvent event) {
        switch (event.getState()){
            case 1:
                addKidBean(event.getMap());
                break;
            case 2:
                updateKidBean(event.getMap());
                break;
        }
    }
    private void updateKidBean(Map<String, String> map) {
        int id=Integer.parseInt(map.get("id"));
        for (int i = 0; i < kidBeans.size(); i++) {
            if(kidBeans.get(i).getId()==id){
                KidBean kidBean=kidBeans.get(i);
                kidBean.setName(map.get("name"));
                kidBean.setId(Integer.parseInt(map.get("id")));
                kidBean.setNotes(map.get("notes"));
                kidBean.setAddress(map.get("address"));
                kidBean.setAdmin(map.get("admin"));
                kidBean.setPhone(map.get("phone"));
                kidBean.setParent_id(map.get("parent_id"));
                kidBean.setIf_repeat(Integer.parseInt(map.get("if_repeat")));
                kidBean.setIs_employee(Integer.parseInt(map.get("is_employee")));
            }
        }
        adapter.notifyDataSetChanged();
    }
    private void addKidBean(Map<String, String> map) {
        KidBean kidBean=new KidBean();
        kidBean.setName(map.get("name"));
        kidBean.setId(Integer.parseInt(map.get("id")));
        kidBean.setNotes(map.get("notes"));
        kidBean.setAddress(map.get("address"));
        kidBean.setAdmin(map.get("admin"));
        kidBean.setPhone(map.get("phone"));
        kidBean.setParent_id(map.get("parent_id"));
        kidBean.setIf_repeat(Integer.parseInt(map.get("if_repeat")));
        kidBean.setIs_employee(Integer.parseInt(map.get("is_employee")));
        kidBeans.add(kidBean);
        adapter.notifyDataSetChanged();
    }
}
