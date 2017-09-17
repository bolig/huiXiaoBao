package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Interface.PermissionManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.PermissionAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.PermissionPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Permission extends BaseView implements PermissionManage.View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private PermissionPresenter permissionPresenter;
    private  String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("权限组");
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setEnableLoadmore(false);
        permissionPresenter=new PermissionPresenter(this,this);
        token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                permissionPresenter.getGroupLists(token,smartRefreshLayout);
            }
        });
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
    }

    @Override
    public void getGroupLists(HttpBean<List<UserRole>> httpBean) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PermissionAdapter adapter=new PermissionAdapter(httpBean.getData(),this);
        recyclerView.setAdapter(adapter);
    }
}
