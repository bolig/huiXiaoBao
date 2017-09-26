package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AreaAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.KidAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.presenter.AreaPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class SelectArea extends BaseView implements AreaManage.View {
    @BindView(R.id.recyclerview_one)
    RecyclerView recyclerviewOne;
    @BindView(R.id.recyclerview_two)
    RecyclerView recyclerviewTwo;
    private AreaAdapter areaAdapter;
    private KidAdapter kidAdapter;
    private List<AreaBean> areas;
    private AreaPresenter areaPresenter;
    private List<KidBean> kidBeens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_area);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("部门");
        kidBeens=new ArrayList<>();
        areaPresenter=new AreaPresenter(this,this);
        String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        areaPresenter.getAreaLists(token);
        recyclerviewOne.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        recyclerviewOne.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewTwo.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        recyclerviewTwo.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void getAreaLists(HttpBean<List<AreaBean>> httpBean) {
        areas=httpBean.getData();
        areaAdapter=new AreaAdapter(areas,this);
        recyclerviewOne.setAdapter(areaAdapter);
        if(areas.size()>0){
            kidBeens.addAll(areas.get(0).getKid());
            kidAdapter=new KidAdapter(kidBeens,this);
            recyclerviewTwo.setAdapter(kidAdapter);
        }
        areaAdapter.addItemClickListener(new ItemClick<AreaBean>() {
            @Override
            public void onItemClick(View view, AreaBean areaBean, int position) {
                areaAdapter.setSelected(areaBean.getName());
                areaAdapter.notifyDataSetChanged();
                kidBeens.removeAll(kidBeens);
                kidBeens.addAll(areaBean.getKid());
                kidAdapter.notifyDataSetChanged();
                if(kidBeens.size()>0){

                }else{
                    Intent it=new Intent();
                    it.putExtra("area_id",String.valueOf(areaBean.getId()));
                    it.putExtra("area_name",areaBean.getName());
                    setResult(200,it);
                    finish();
                }
            }
        });
        kidAdapter.addItemClickListener(new ItemClick<KidBean>() {
            @Override
            public void onItemClick(View view, KidBean kidBean, int position) {
                kidAdapter.setSelected(kidBean.getName());
                kidAdapter.notifyDataSetChanged();
                Intent it=new Intent();
                it.putExtra("area_id",String.valueOf(kidBean.getId()));
                it.putExtra("area_name",kidBean.getName());
                setResult(200,it);
                finish();
            }
        });
    }
}
