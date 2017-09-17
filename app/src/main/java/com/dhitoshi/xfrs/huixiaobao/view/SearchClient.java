package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
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
    private SearchClientPresenter searchClientPresenter;
    private int page = 1;
    private String searchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_client);
        ButterKnife.bind(this);
        initViews();
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
                            smartRefreshLayout.autoRefresh();
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                searchClientPresenter.searchClientList(searchText, String.valueOf(page), smartRefreshLayout);
            }
        });
    }
    @Override
    public void searchClientList(PageBean<ClientBean> pageBean) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        ClientAdapter adapter = new ClientAdapter(pageBean.getList(), this);
        recyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<ClientBean>() {
            @Override
            public void onItemClick(View view, ClientBean clientBean, int position) {
                startActivity(new Intent(SearchClient.this, ClientInfo.class).putExtra("info", clientBean));
            }
        });
    }
    @OnClick(R.id.search_back)
    public void onViewClicked() {
        finish();
    }
}
