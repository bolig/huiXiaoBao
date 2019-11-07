package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.TribeCompanyManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.TribeCompanyAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.presenter.TribeCompanyPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TribeCompany extends BaseView implements TribeCompanyManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private TribeCompanyPresenter presenter;
    private int page = 1;
    private List<BaseBean> companys;
    private TribeCompanyAdapter adapter;
    private long mTribeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tribe_company);
        ButterKnife.bind(this);
        ActivityManagerUtil.addDestoryActivity(this,"TribeCompany");
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("TribeCompany");
    }

    private void initView() {
        initBaseViews();
        setTitle("添加群成员");
        mTribeId = getIntent().getLongExtra(TribeConstants.TRIBE_ID, 0);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        presenter = new TribeCompanyPresenter(this, this);
        companys=new ArrayList<>();
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                companys.removeAll(companys);
                String token = SharedPreferencesUtil.Obtain(TribeCompany.this, "token", "").toString();
                page=1;
                presenter.getCompanyList(token,String.valueOf(page),smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                String token = SharedPreferencesUtil.Obtain(TribeCompany.this, "token", "").toString();
                ++page;
                presenter.getCompanyList(token,String.valueOf(page),smartRefreshLayout);
            }
        });
    }
    @Override
    public void getCompanyList(HttpPageBeanTwo<BaseBean> httpPageBeanTwo) {
        companys.addAll(httpPageBeanTwo.getData());
        int size = companys.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == adapter) {
            adapter = new TribeCompanyAdapter(companys, TribeCompany.this);
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<BaseBean>() {
                @Override
                public void onItemClick(View view, BaseBean baseBean, int position) {
                    startActivity(new Intent(TribeCompany.this, InviteTribeMember.class)
                            .putExtra(TribeConstants.TRIBE_ID,mTribeId ).putExtra("area_id",String.valueOf(baseBean.getId())));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }
}
