package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.PermissionEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.PermissionManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.PermissionAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.PermissionPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Permission extends BaseView implements PermissionManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    private PermissionPresenter permissionPresenter;
    private PermissionAdapter adapter;
    private int deletePosition = -1;
    private List<UserRole> userRoles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission);
        ButterKnife.bind(this);
        initViews();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initViews() {
        initBaseViews();
        setTitle("权限组");
        setRightIcon(R.mipmap.add);
        smartRefreshLayout.autoRefresh();
        userRoles = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        smartRefreshLayout.setEnableLoadmore(false);
        permissionPresenter = new PermissionPresenter(this, this);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                userRoles.removeAll(userRoles);
                permissionPresenter.getGroupLists(SharedPreferencesUtil.Obtain(Permission.this, "token", "").toString(), smartRefreshLayout);
            }
        });
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this, AddPermission.class));
    }

    @Override
    public void getGroupLists(HttpBean<List<UserRole>> httpBean) {
        userRoles.addAll(httpBean.getData());
        int size = userRoles.size();
        empty.setVisibility(userRoles.size() == 0 ? View.VISIBLE : View.GONE);
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        if (adapter == null) {
            adapter = new PermissionAdapter(userRoles, this);
            recyclerView.setAdapter(adapter);
            adapter.addDeleteCallback(new DeleteCallback() {
                @Override
                public void delete(int id, int position) {
                    deletePosition = position;
                    String token = SharedPreferencesUtil.Obtain(Permission.this, "token", "").toString();
                    LoadingDialog dialog = LoadingDialog.build(Permission.this).setLoadingTitle("删除中");
                    dialog.show();
                    permissionPresenter.deleteGroup(String.valueOf(id), token, dialog);
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void deleteGroup(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        userRoles.remove(deletePosition);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PermissionEvent event) {
        switch (event.getState()) {
            case 1:
                smartRefreshLayout.autoRefresh();
                break;
        }
    }
}
