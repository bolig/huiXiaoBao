package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SelectAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
public class SelectType extends BaseView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private int page=1;
    private List<BaseBean> productTypes;
    private SelectAdapter adapter;
    private String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_type);
        ButterKnife.bind(this);
        selected=getIntent().getStringExtra("selected");
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("选择产品类型");
        productTypes=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                productTypes.removeAll(productTypes);
                page=1;
                getItemType(page);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getItemType(++page);
            }
        });
    }
    private void getItemType(int page){
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().getItemType(String.valueOf(page)),new CommonObserver(new HttpResult<HttpBean<PageBean<BaseBean>>>() {
            @Override
            public void OnSuccess(HttpBean<PageBean<BaseBean>> httpBean) {
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    initData(httpBean.getData().getList());
                }else{
                    Toast.makeText(SelectType.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                Toast.makeText(SelectType.this,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    private void initData(List<BaseBean> list) {
        productTypes.addAll(list);
        int size=productTypes.size();
        if(size>=10&&size%10==0){
            smartRefreshLayout.setEnableLoadmore(true);
        }else{
            smartRefreshLayout.setEnableLoadmore(false);
        }
        if(adapter==null){
            adapter=new SelectAdapter(productTypes,this,selected);
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<BaseBean>() {
                @Override
                public void onItemClick(View view, BaseBean baseBean, int position) {
                    Log.e("TAG","id--->>>>"+baseBean.getId());
                    Intent it=new Intent();
                    it.putExtra("id",String.valueOf(baseBean.getId()));
                    it.putExtra("name",baseBean.getName());
                    setResult(600,it);
                    finish();
                }
            });
            adapter.addCheckBoxClick(new CheckBoxClick() {
                @Override
                public void check(boolean isChecked, String name, int id) {
                    Log.e("TAG","id-------"+id);
                    Intent it=new Intent();
                    it.putExtra("id",String.valueOf(id));
                    it.putExtra("name",name);
                    setResult(600,it);
                    finish();
                }
            });
        }else{
            adapter.notifyDataSetChanged();
        }
    }
}
