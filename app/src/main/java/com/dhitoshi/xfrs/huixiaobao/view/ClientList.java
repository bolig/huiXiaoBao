package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ClientAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ClientList extends BaseView {
    @BindView(R.id.client_recyclerView)
    RecyclerView clientRecyclerView;
    private String title;
    private ArrayList<ClientBean> clients;
    private ClientAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"ClientList");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("ClientList");
    }

    private void initViews() {
        initBaseViews();
        title = getIntent().getStringExtra("title");
        clients=getIntent().getParcelableArrayListExtra("clients");
        setTitle(title);
        clientRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        clientRecyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        adapter = new ClientAdapter(clients,this);
        clientRecyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<ClientBean>() {
            @Override
            public void onItemClick(View view, ClientBean clientBean, int position) {
                startActivity(new Intent(ClientList.this, ClientInfo.class).putExtra("info", clientBean));
            }
        });
    }
}
