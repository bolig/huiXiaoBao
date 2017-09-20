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
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBeanX;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.AddAreaOneEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.AddAreaTwoEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.SetAreaManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AddAreaTwoAdapter;
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

public class AddAreaTwo extends BaseView implements SetAreaManage.View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String title;
    private ArrayList<KidBeanX> kidBeanXes;
    private SwipeItemLayout.OnSwipeItemTouchListener listener;
    private AddAreaTwoAdapter adapter;
    private int deletePosition=-1;
    private SetAreaPresenter setAreaPresenter;
    private int parent_id;
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
        title = getIntent().getStringExtra("title");
        parent_id=getIntent().getIntExtra("parent_id",-1);
        kidBeanXes = getIntent().getParcelableArrayListExtra("kidx");
        setTitle(title);
        setRightIcon(R.mipmap.add);
        setAreaPresenter=new SetAreaPresenter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        listener = new SwipeItemLayout.OnSwipeItemTouchListener(this);
        recyclerView.addOnItemTouchListener(listener);
        adapter=new AddAreaTwoAdapter(kidBeanXes,this);
        recyclerView.setAdapter(adapter);
        adapter.addDeleteCallback(new DeleteCallback() {
            @Override
            public void delete(int id, int position) {
                deletePosition=position;
                String token= SharedPreferencesUtil.Obtain(AddAreaTwo.this,"token","").toString();
                LoadingDialog dialog = LoadingDialog.build(AddAreaTwo.this).setLoadingTitle("删除中");
                dialog.show();
                setAreaPresenter.deleteArea(String.valueOf(id),token,dialog);
            }
        });
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this,AddArea.class).putExtra("type",1).putExtra("parent_id",String.valueOf(parent_id)));
    }

    @Override
    public void getAreaLists(HttpBean<List<AreaBean>> httpBean) {

    }
    @Override
    public void deleteArea(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        kidBeanXes.remove(deletePosition);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddAreaTwoEvent event) {
        switch (event.getState()){
            case 1:
                addKidBeanX(event.getMap());
                break;
            case 2:
                updateKidBeanX(event.getMap());
                break;
        }
    }
    private void updateKidBeanX(Map<String, String> map) {
        int id=Integer.parseInt(map.get("id"));
        for (int i = 0; i < kidBeanXes.size(); i++) {
            if(kidBeanXes.get(i).getId()==id){
                KidBeanX kidBeanX=kidBeanXes.get(i);
                kidBeanX.setName(map.get("name"));
                kidBeanX.setId(Integer.parseInt(map.get("id")));
                kidBeanX.setNotes(map.get("notes"));
                kidBeanX.setAddress(map.get("address"));
                kidBeanX.setAdmin(map.get("admin"));
                kidBeanX.setPhone(map.get("phone"));
                kidBeanX.setParent_id(map.get("parent_id"));
                kidBeanX.setIf_repeat(Integer.parseInt(map.get("if_repeat")));
                kidBeanX.setIs_employee(Integer.parseInt(map.get("is_employee")));
                kidBeanX.setKid(new ArrayList<KidBean>());
            }
        }
        adapter.notifyDataSetChanged();
    }
    private void addKidBeanX(Map<String, String> map) {
        KidBeanX kidBeanX=new KidBeanX();
        kidBeanX.setName(map.get("name"));
        kidBeanX.setId(Integer.parseInt(map.get("id")));
        kidBeanX.setNotes(map.get("notes"));
        kidBeanX.setAddress(map.get("address"));
        kidBeanX.setAdmin(map.get("admin"));
        kidBeanX.setPhone(map.get("phone"));
        kidBeanX.setParent_id(map.get("parent_id"));
        kidBeanX.setIf_repeat(Integer.parseInt(map.get("if_repeat")));
        kidBeanX.setIs_employee(Integer.parseInt(map.get("is_employee")));
        kidBeanX.setKid(new ArrayList<KidBean>());
        kidBeanXes.add(kidBeanX);
        adapter.notifyDataSetChanged();
    }
}
