package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.BulkImportBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.MeetClientEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.BulkImportManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxBulkClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.BulkImportAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.presenter.BulkImportPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BulkImport extends BaseView implements BulkImportManage.View{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.error)
    RelativeLayout error;
    private String meetingid = "";
    private String names="";
    private String idcards="";
    private int page=1;
    private List<ClientBean> clients;
    private BulkImportPresenter bulkImportPresenter;
    private BulkImportAdapter adapter;
    private Map<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulk_import);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"BulkImport");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("BulkImport");
    }

    private void initViews() {
        initBaseViews();
        setTitle("批量添加人员");
        setRightText("提交");
        clients = new ArrayList<>();
        meetingid = getIntent().getStringExtra("id");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        smartRefreshLayout.autoRefresh();
        bulkImportPresenter=new BulkImportPresenter(this,this);
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                clients.removeAll(clients);
                page = 1;
                Map<String, String> map = new HashMap<>();
                map.put("area",  SharedPreferencesUtil.Obtain(BulkImport.this, "areId","").toString());
                map.put("page", String.valueOf(page));
                String token=SharedPreferencesUtil.Obtain(BulkImport.this,"token","").toString();
                map.put("token",token);
                bulkImportPresenter.getClientList(map, smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                Map<String, String> map = new HashMap<>();
                map.put("area",  SharedPreferencesUtil.Obtain(BulkImport.this, "areId","").toString());
                map.put("page", String.valueOf(page));
                String token=SharedPreferencesUtil.Obtain(BulkImport.this,"token","").toString();
                map.put("token",token);
                bulkImportPresenter.getClientList(map, smartRefreshLayout);
            }
        });
    }
    @OnClick(R.id.right_text)
    public void onViewClicked() {
         if(names.isEmpty()){
             Toast.makeText(this,"请选择要添加的人员",Toast.LENGTH_SHORT).show();
             return;
         }
        if(map==null){
            map=new HashMap<>();
        }
        map.put("name",names.substring(0,names.length()-1));
        map.put("idcard",idcards.substring(0,idcards.length()-1));
        map.put("meetingid",meetingid);
        map.put("token",SharedPreferencesUtil.Obtain(this,"token","").toString());
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        dialog.show();
        bulkImportPresenter.addCustomerAll(map,dialog);
    }
    @Override
    public void getClientList(HttpPageBean<ClientBean> httpPageBean) {
        clients.addAll(httpPageBean.getList());
        int size = clients.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size==0?View.VISIBLE:View.GONE);
        if (null == adapter) {
            adapter = new BulkImportAdapter(clients, this);
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<ClientBean>() {
                @Override
                public void onItemClick(View view, ClientBean clientBean, int position) {
                    if(clientBean.isSelected()){
                        idcards=idcards.replace(clientBean.getIdcard()+",","");
                        names= names.replace(clientBean.getName()+",","");
                    }else{
                        names+=clientBean.getName()+",";
                        idcards+=clientBean.getIdcard()+",";
                    }
                    clients.get(position).setSelected(!clientBean.isSelected());
                    adapter.notifyDataSetChanged();
                }
            });
            adapter.addCheckBoxClick(new CheckBoxBulkClick() {
                @Override
                public void check(boolean isChecked, String name, String idcard,int position) {
                    if(isChecked){
                        idcards=idcards.replace(idcard+",","");
                        names= names.replace(name+",","");
                    }else{
                        names+=name+",";
                        idcards+=idcard+",";
                    }
                    clients.get(position).setSelected(!isChecked);
                    adapter.notifyDataSetChanged();
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void addCustomerAll(HttpBean<BulkImportBean> httpBean) {
        EventBus.getDefault().post(new MeetClientEvent(2));
        startActivity(new Intent(this,BulkImportResult.class).putParcelableArrayListExtra("success",(ArrayList)httpBean.getData().getSuccess())
                .putParcelableArrayListExtra("fail",(ArrayList)httpBean.getData().getFail()));
        finish();
    }
}
