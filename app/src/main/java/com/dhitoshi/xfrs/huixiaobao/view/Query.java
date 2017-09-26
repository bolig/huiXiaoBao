package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoQuery;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PositionBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SaleaddressBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SalesmanBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SexBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.LocationAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.PositionAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.SexAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.QueryPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Query extends BaseView implements QueryManage.View{
    @BindView(R.id.query_result)
    TextView queryResult;
    @BindView(R.id.query_area)
    TextView queryArea;
    @BindView(R.id.query_name)
    EditText queryName;
    @BindView(R.id.query_sex)
    TextView querySex;
    @BindView(R.id.query_phone)
    EditText queryPhone;
    @BindView(R.id.query_vip)
    EditText queryVip;
    @BindView(R.id.query_address)
    EditText queryAddress;
    @BindView(R.id.query_company)
    EditText queryCompany;
    @BindView(R.id.query_position)
    TextView queryPosition;
    @BindView(R.id.createtime_start)
    TextView createtimeStart;
    @BindView(R.id.createtime_end)
    TextView createtimeEnd;
    @BindView(R.id.totalnum_start)
    EditText totalnumStart;
    @BindView(R.id.totalnum_end)
    EditText totalnumEnd;
    @BindView(R.id.query_sales)
    TextView querySales;
    @BindView(R.id.buy_address)
    TextView buyAddress;
    @BindView(R.id.buy_item)
    TextView buyItem;
    @BindView(R.id.buytime_start)
    TextView buytimeStart;
    @BindView(R.id.buytime_end)
    TextView buytimeEnd;
    @BindView(R.id.visit_start)
    TextView visitStart;
    @BindView(R.id.visit_end)
    TextView visitEnd;
    @BindView(R.id.unvisit_start)
    TextView unvisitStart;
    @BindView(R.id.unvisit_end)
    TextView unvisitEnd;
    private QueryPresenter queryPresenter;
    private String sex="";
    private String type="";
    private String area="";
    private String name="";
    private String phone="";
    private String address="";
    private String company="";
    private String vip_id="";
    private String workPosition="";
    private String salemanid;
    private String itemid;
    private String buyaddress="";
    private String totalnum_st="";
    private String totalnum_end="";
    private String createtime_st="";
    private String createtime_end="";
    private String buytime_st="";
    private String buytime_end="";
    private String feedback_time_st="";
    private String feedback_time_end="";
    private String feedback_time_st_un="";
    private String feedback_time_end_un="";
    private ArrayList<ProductBean> items;
    private List<PositionBean> positions;
    private List<BaseBean> saleaddresses;
    private ArrayList<BaseBean> salesmen;
    private Map<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("高级查询");
        queryPresenter=new QueryPresenter(this,this);
        queryPresenter.getListForSearch(SharedPreferencesUtil.Obtain(this,"token","").toString());
    }
    @OnClick({R.id.query_result, R.id.query_area, R.id.query_sex, R.id.query_position, R.id.createtime_start,
            R.id.createtime_end, R.id.query_sales, R.id.buy_address, R.id.buy_item, R.id.buytime_start,
            R.id.buytime_end, R.id.visit_start, R.id.visit_end, R.id.unvisit_start, R.id.unvisit_end,R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.query_result:
                selectResult();
                break;
            case R.id.query_area:
                startActivityForResult(new Intent(this, SelectArea.class), 5);
                break;
            case R.id.query_sex:
                selectSex();
                break;
            case R.id.query_position:
                selectPosition();
                break;
            case R.id.createtime_start:
                selectCreateStart();
                break;
            case R.id.createtime_end:
                selectCreateEnd();
                break;
            case R.id.query_sales:
                selectSales();
                break;
            case R.id.buy_address:
                selectBuyAddress();
                break;
            case R.id.buy_item:
                selectBuyItem();
                break;
            case R.id.buytime_start:
                selectBuyStart();
                break;
            case R.id.buytime_end:
                selectBuyEnd();
                break;
            case R.id.visit_start:
                selectVisitStart();
                break;
            case R.id.visit_end:
                selectVisitEnd();
                break;
            case R.id.unvisit_start:
                selectUnVisitStart();
                break;
            case R.id.unvisit_end:
                selectUnVisitEnd();
                break;
            case R.id.query:
                query();
                break;
        }
    }
    //选择销售人员
    private void selectSales() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",salesmen)
                .putExtra("type",4).putExtra("select",querySales.getText().toString()),4);
    }
    //选择购买产品
    private void selectBuyItem() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",items)
                .putExtra("type",3).putExtra("select",buyItem.getText().toString()),3);
    }
    //选择购买地点
    private void selectBuyAddress() {
        LocationAdapter adapter=new LocationAdapter(saleaddresses,this,buyAddress.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择购买地点").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                buyAddress.setText(baseBean.getName());
                buyaddress=String.valueOf(baseBean.getId());
                buyAddress.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择职位
    private void selectPosition() {
        PositionAdapter adapter = new PositionAdapter(positions, this, queryPosition.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择职位").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<PositionBean>() {
            @Override
            public void onItemClick(View view, PositionBean positionBean, int position) {
                queryPosition.setText(positionBean.getName());
                workPosition= String.valueOf(positionBean.getId());
                queryPosition.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择回访日期结束时间
    private void selectVisitEnd() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择结束日期").setTime(visitEnd.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                feedback_time_end = date;
                visitEnd.setText(date);
                visitEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择回访日期开始时间
    private void selectVisitStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择开始日期").setTime(visitStart.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                feedback_time_st = date;
                visitStart.setText(date);
                visitStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择购买结束日期
    private void selectBuyEnd() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择结束日期").setTime(buytimeEnd.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                buytime_end = date;
                buytimeEnd.setText(date);
                buytimeEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择购买开始日期
    private void selectBuyStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择开始日期").setTime(buytimeStart.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                buytime_st = date;
                buytimeStart.setText(date);
                buytimeStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择录入结束时间
    private void selectCreateEnd() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择结束日期").setTime(createtimeEnd.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                createtime_st = date;
                createtimeEnd.setText(date);
                createtimeEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择录入开始时间
    private void selectCreateStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择开始日期").setTime(createtimeStart.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                createtime_end = date;
                createtimeStart.setText(date);
                createtimeStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择未访日期开始时间
    private void selectUnVisitStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择开始日期").setTime(unvisitStart.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                feedback_time_st_un = date;
                unvisitStart.setText(date);
                unvisitStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择未访日期结束时间
    private void selectUnVisitEnd() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择结束日期").setTime(unvisitEnd.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                feedback_time_end_un = date;
                unvisitEnd.setText(date);
                unvisitEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择性别
    private void selectSex() {
        List<SexBean> sexBeens = new ArrayList<>();
        sexBeens.add(new SexBean("0", "男"));
        sexBeens.add(new SexBean("1", "女"));
        SexAdapter adapter = new SexAdapter(sexBeens, this, querySex.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择性别").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<SexBean>() {
            @Override
            public void onItemClick(View view, SexBean sexBean, int position) {
                querySex.setText(sexBean.getName());
                sex = sexBean.getId();
                querySex.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择查询结果
    private void selectResult() {
        List<SexBean> sexBeens = new ArrayList<>();
        sexBeens.add(new SexBean("1", "基本资料"));
        sexBeens.add(new SexBean("2", "消费记录"));
        sexBeens.add(new SexBean("3", "回访记录"));
        sexBeens.add(new SexBean("4", "社会关系"));
        sexBeens.add(new SexBean("5", "赠品记录"));
        sexBeens.add(new SexBean("6", "参会记录"));
        SexAdapter adapter = new SexAdapter(sexBeens, this, queryResult.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择查询结果").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<SexBean>() {
            @Override
            public void onItemClick(View view, SexBean sexBean, int position) {
                queryResult.setText(sexBean.getName());
                type = sexBean.getId();
                queryResult.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //查询
    private void query() {
        if(juge()){
            if(map==null){ map=new HashMap<>();}
            map.put("token",SharedPreferencesUtil.Obtain(this,"token","").toString());
            map.put("type",type);
            map.put("page","1");
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            if(type.equals("2")){
                map.put("salemanid",salemanid);
                map.put("itemid",itemid);
                map.put("buyaddress",buyaddress);
                map.put("buytime_st",buytime_st);
                map.put("buytime_end",buytime_end);
                queryPresenter.getSearchTwo(map,dialog);
            }
            else if(type.equals("3")){
                map.put("feedback_time_st",feedback_time_st);
                map.put("feedback_time_end",feedback_time_end);
                map.put("feedback_time_st_un",feedback_time_st_un);
                map.put("feedback_time_end_un",feedback_time_end_un);
                queryPresenter.getSearchThree(map,dialog);
            }else{
                map.put("area",area);
                map.put("name",name);
                map.put("phone",phone);
                map.put("address",address);
                map.put("company",company);
                map.put("vip_id",vip_id);
                map.put("position",workPosition);
                map.put("totalnum_st",totalnum_st);
                map.put("totalnum_end",totalnum_end);
                map.put("createtime_st",createtime_st);
                map.put("createtime_end",createtime_end);
                map.put("sex",sex);
                switch (type){
                    case "1":
                        queryPresenter.getSearchOne(map,dialog);
                        break;
                    case "4":
                        queryPresenter.getSearchFour(map,dialog);
                        break;
                    case "5":
                        queryPresenter.getSearchFive(map,dialog);
                        break;
                    case "6":
                        queryPresenter.getSearchSix(map,dialog);
                        break;
                }
            }
        }
    }
    private boolean juge() {
       if(type.equals("2")){
           return jugeBuy();
       }else if(type.equals("3")){
           return jugeVisit();
       }else {
           return jugeOther();
       }
    }
    //回访记录必传判断
    private boolean jugeVisit() {
        if(feedback_time_st.isEmpty()){
            Toast.makeText(this,"请选择回访开始日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(feedback_time_st.isEmpty()){
            Toast.makeText(this,"请选择回访结束日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(feedback_time_st.isEmpty()){
            Toast.makeText(this,"请选择未回访开始日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(feedback_time_st.isEmpty()){
            Toast.makeText(this,"请选择未回访结束日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //消费记录必传判断
    private boolean jugeBuy() {
        if(buytime_st.isEmpty()){
            Toast.makeText(this,"请选择购买开始日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(buytime_end.isEmpty()){
            Toast.makeText(this,"请选择购买结束日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //其他资料必传判断
    private boolean jugeOther() {
        if(createtime_st.isEmpty()){
            Toast.makeText(this,"请选择录入开始日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(createtime_end.isEmpty()){
            Toast.makeText(this,"请选择录入结束日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        totalnum_st=totalnumStart.getText().toString();
        if(totalnum_st.isEmpty()){
            Toast.makeText(this,"请填写累计金额起始值",Toast.LENGTH_SHORT).show();
            return false;
        }
        totalnum_end=totalnumEnd.getText().toString();
        if(totalnum_end.isEmpty()){
            Toast.makeText(this,"请填写累计金额结束值",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (resultCode == 200) {
            switch (requestCode) {
                case 5:
                    area = data.getStringExtra("area_id");
                    queryArea.setText(data.getStringExtra("area_name"));
                    queryArea.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 3:
                    itemid=data.getStringExtra("id");
                    buyItem.setText(data.getStringExtra("name"));
                    buyItem.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 4:
                    salemanid=data.getStringExtra("id");
                    querySales.setText(data.getStringExtra("name"));
                    querySales.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
    @Override
    public void getListForSearch(HttpBean<InfoQuery> httpBean) {
        items= (ArrayList<ProductBean>) httpBean.getData().getItem();
        positions=httpBean.getData().getPosition();
        saleaddresses=httpBean.getData().getSaleaddress();
        salesmen= (ArrayList<BaseBean>) httpBean.getData().getSalesman();
    }
    @Override
    public void getSearchOne(HttpBean<PageBean<ClientBean>> httpBean) {

    }
    @Override
    public void getSearchTwo(HttpBean<PageBean<SpendBean>> httpBean) {

    }
    @Override
    public void getSearchThree(HttpBean<PageBean<VisitBean>> httpBean) {

    }
    @Override
    public void getSearchFour(HttpBean<PageBean<RelationBean>> httpBean) {

    }
    @Override
    public void getSearchFive(HttpBean<PageBean<GiftBean>> httpBean) {

    }
    @Override
    public void getSearchSix(HttpBean<PageBean<MeetBean>> httpBean) {

    }
}
