package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AreaAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.KidAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.KidXAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.presenter.AreaPresenter;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Area extends BaseView implements AreaManage.View {
    @BindView(R.id.recyclerview_one)
    RecyclerView recyclerviewOne;
    @BindView(R.id.recyclerview_two)
    RecyclerView recyclerviewTwo;
    @BindView(R.id.recyclerview_three)
    RecyclerView recyclerviewThree;
    private AreaAdapter areaAdapter;
    private KidXAdapter kidXAdapter;
    private KidAdapter kidAdapter;
    private List<AreaBean> areas;
    private AreaPresenter areaPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_area);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("地区");
        areaPresenter=new AreaPresenter(this,this);
        areaPresenter.getAreaLists();
        recyclerviewOne.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        recyclerviewOne.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewTwo.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        recyclerviewTwo.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewThree.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        recyclerviewThree.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void getAreaLists(HttpBean<List<AreaBean>> httpBean) {
        areas=httpBean.getData();
        areaAdapter=new AreaAdapter(areas,this);
        recyclerviewOne.setAdapter(areaAdapter);
        kidXAdapter=new KidXAdapter(areas.get(0).getKid(),this);
        recyclerviewTwo.setAdapter(kidXAdapter);
        kidAdapter=new KidAdapter(areas.get(0).getKid().get(0).getKid(),this);
        recyclerviewThree.setAdapter(kidAdapter);
    }
}
