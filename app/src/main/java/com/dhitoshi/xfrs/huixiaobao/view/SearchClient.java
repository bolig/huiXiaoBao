package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.SearchClientManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ClientAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.ClearEditText;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.presenter.SearchClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchClient extends AppCompatActivity implements SearchClientManage.View {
    @BindView(R.id.search)
    ClearEditText search;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    private SearchClientPresenter searchClientPresenter;
    private int page = 1;
    private String searchText;
    private ClientAdapter adapter;
    private List<ClientBean> clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_client);
        ButterKnife.bind(this);
        clients = new ArrayList<>();
        initViews();
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintResource(R.color.colorPrimary);
        ActivityManagerUtil.addDestoryActivity(this,"SearchClient");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("SearchClient");
    }

    private void initViews() {
        searchClientPresenter = new SearchClientPresenter(this, this);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                searchText = search.getText().toString();
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        if (searchText.isEmpty()) {
                            Toast.makeText(SearchClient.this, "搜索条件不能为空", Toast.LENGTH_SHORT).show();
                        } else {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getWindow().peekDecorView().getWindowToken(), 0);
                            smartRefreshLayout.autoRefresh();
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                clients.removeAll(clients);
                String token = SharedPreferencesUtil.Obtain(SearchClient.this, "token", "").toString();
                page = 1;
                searchClientPresenter.searchClientList(token, searchText, String.valueOf(page), smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                String token = SharedPreferencesUtil.Obtain(SearchClient.this, "token", "").toString();
                ++page;
                searchClientPresenter.searchClientList(token, searchText, String.valueOf(page), smartRefreshLayout);
            }
        });
    }

    @Override
    public void searchClientList(PageBean<ClientBean> pageBean) {
        clients.addAll(pageBean.getList());
        int size = clients.size();
        empty.setVisibility(clients.size() == 0 ? View.VISIBLE : View.GONE);
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        if (adapter == null) {
            adapter = new ClientAdapter(clients, this);
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<ClientBean>() {
                @Override
                public void onItemClick(View view, ClientBean clientBean, int position) {
                    startActivity(new Intent(SearchClient.this, ClientInfo.class).putExtra("info", clientBean));
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    @OnClick(R.id.search_back)
    public void onViewClicked() {
        finish();
    }
}
