package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterClient extends BaseView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.error)
    RelativeLayout error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_client);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("参会人员");
        setRightIcon(R.mipmap.add);
    }
    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this,AddMeetClient.class));
    }
}
