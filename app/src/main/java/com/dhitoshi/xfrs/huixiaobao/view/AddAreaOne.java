package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddAreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AddAreaAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.SwipeItemLayout;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddAreaPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddAreaOne extends BaseView implements AddAreaManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private AddAreaAdapter adapter;
    private AddAreaPresenter addAreaPresenter;
    private SwipeItemLayout.OnSwipeItemTouchListener listener;
    private int deletePosition=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_areaother);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("地区");
        setRightIcon(R.mipmap.add);
        smartRefreshLayout.autoRefresh();
        addAreaPresenter=new AddAreaPresenter(this,this);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                addAreaPresenter.getAreaLists(smartRefreshLayout);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        listener = new SwipeItemLayout.OnSwipeItemTouchListener(this);
        recyclerView.addOnItemTouchListener(listener);
    }
    @Override
    public void getAreaLists(HttpBean<List<AreaBean>> httpBean) {
        adapter=new AddAreaAdapter(httpBean.getData(),this);
        recyclerView.setAdapter(adapter);
        adapter.addItemClickListener(new ItemClick<AreaBean>() {
            @Override
            public void onItemClick(View view, AreaBean areaBean, int position) {
                deletePosition=position;
                String token= SharedPreferencesUtil.Obtain(AddAreaOne.this,"token","").toString();
                LoadingDialog dialog = LoadingDialog.build(AddAreaOne.this).setLoadingTitle("删除中");
                dialog.show();
               // productTypePresenter.deleteItemType(token,String.valueOf(id),dialog);
            }
        });
    }

    @OnClick(R.id.right_icon)
    public void onViewClicked() {
        startActivity(new Intent(this,AddArea.class));
    }
}
