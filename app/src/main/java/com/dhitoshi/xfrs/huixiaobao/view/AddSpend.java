package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PositionBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SaleaddressBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SalesmanBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.QueryResultEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.SpendEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddSpendManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.LocationAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.PositionAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddSpendPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dhitoshi.xfrs.huixiaobao.R.mipmap.relation;

public class AddSpend extends BaseView implements AddSpendManage.View{
    @BindView(R.id.spend_date)
    TextView spendDate;
    @BindView(R.id.spend_product)
    TextView spendProduct;
    @BindView(R.id.spend_location)
    TextView spendLocation;
    @BindView(R.id.spend_saleMan)
    TextView spendSaleMan;
    @BindView(R.id.spend_price)
    EditText spendPrice;
    @BindView(R.id.spend_number)
    EditText spendNumber;
    @BindView(R.id.spend_discount)
    EditText spendDiscount;
    @BindView(R.id.spend_money)
    EditText spendMoney;
    @BindView(R.id.spend_debt)
    EditText spendDebt;
    @BindView(R.id.spend_acNum)
    EditText spendAcNum;
    @BindView(R.id.spend_waitNum)
    EditText spendWaitNum;
    @BindView(R.id.spend_notes)
    EditText spendNotes;
    private SpendBean spendBean;
    private String createtime="";
    private String productId="";
    private String addressId="";
    private String saleManId="";
    private String price="";
    private String number="";
    private String discount="";
    private String receive="";
    private String debt="";
    private String acNumber="";
    private String waitNumber="";
    private String notes="";
    private AddSpendPresenter addSpendPresenter;
    private ArrayList<ProductBean> item;
    private List<BaseBean> saleaddress;
    private ArrayList<BaseBean> salesman;
    private int userId;
    private int type=0;
    private Map<String,String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_spend);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        userId=getIntent().getIntExtra("id",0);
        type=getIntent().getIntExtra("type",0);
        spendBean = getIntent().getParcelableExtra("spend");
        setTitle(null == spendBean ? "新增消费记录" : "编辑消费记录");
        if (null != spendBean) {
            initSpendInfo();
        }
        setRightText("提交");
        addSpendPresenter=new AddSpendPresenter(this,this);
        String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        addSpendPresenter.getListForSpending(token);
    }
    private void initSpendInfo() {
        createtime=spendBean.getCreatetime();
        spendDate.setText(spendBean.getCreatetime());
        spendDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        spendProduct.setText(spendBean.getItem_name());
        spendProduct.setTextColor(getResources().getColor(R.color.colorPrimary));
        spendLocation.setText(spendBean.getBuyaddress());
        spendLocation.setTextColor(getResources().getColor(R.color.colorPrimary));
        spendSaleMan.setText(spendBean.getSaleman_name());
        spendSaleMan.setTextColor(getResources().getColor(R.color.colorPrimary));
        price=spendBean.getCost();
        spendPrice.setText(spendBean.getCost());
        number=spendBean.getNumber();
        spendNumber.setText(spendBean.getNumber());
        discount=spendBean.getDiscount();
        spendDiscount.setText(spendBean.getDiscount());
        receive=spendBean.getAc_receive();
        spendMoney.setText(spendBean.getAc_receive());
        debt=spendBean.getDebt();
        spendDebt.setText(spendBean.getDebt());
        acNumber=spendBean.getAc_num();
        spendAcNum.setText(spendBean.getAc_num());
        waitNumber=spendBean.getWait_num();
        spendWaitNum.setText(spendBean.getWait_num());
        spendNotes.setText(spendBean.getNotes());
    }
    @OnClick({R.id.spend_date, R.id.spend_product, R.id.spend_location, R.id.spend_saleMan,R.id.right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spend_date:
                selectDate();
                break;
            case R.id.spend_product:
                selectProduct();
                break;
            case R.id.spend_location:
                selectLocation();
                break;
            case R.id.spend_saleMan:
                selectSaleMan();
                break;
            case R.id.right_text:
                commit();
        }
    }
    private void commit() {
       if(juge()){
           if(map==null){
               map=new HashMap<>();
           }
           if(!createtime.isEmpty()){
               map.put("createtime",createtime);
           }
           if(!productId.isEmpty()){
               map.put("itemid",productId);
           }
           if(!addressId.isEmpty()){
               map.put("buyaddress",addressId);
           }
           if(!saleManId.isEmpty()){
               map.put("salemanid",saleManId);
           }
           if(!price.isEmpty()){
               map.put("cost",price);
           }
           if(!number.isEmpty()){
               map.put("number",number);
           }
           if(!discount.isEmpty()){
               map.put("discount",discount);
           }
           if(!receive.isEmpty()){
               map.put("ac_receive",receive);
           }
           if(!debt.isEmpty()){
               map.put("debt",debt);
           }
           if(!acNumber.isEmpty()){
               map.put("ac_num",acNumber);
           }
           if(!waitNumber.isEmpty()){
               map.put("wait_num",waitNumber);
           }
           if(!notes.isEmpty()){
               map.put("notes",notes);
           }
           LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
           dialog.show();
           String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
           map.put("token",token);
           if(null==spendBean){
               map.put("userid",String.valueOf(userId));
               addSpendPresenter.addSpend(map,dialog);
           }else{
               map.put("id",String.valueOf(spendBean.getId()));
               addSpendPresenter.editSpend(map,dialog);
           }
       }
    }
    private boolean juge(){
        if(createtime.isEmpty()){
            Toast.makeText(this," 请选择购买日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        price=spendPrice.getText().toString();
        number=spendNumber.getText().toString();
        discount=spendDiscount.getText().toString();
        receive=spendMoney.getText().toString();
        debt=spendDebt.getText().toString();
        acNumber=spendAcNum.getText().toString();
        waitNumber=spendWaitNum.getText().toString();
        notes=spendNotes.getText().toString();
        return true;
    }
    //选择产品
    private void selectProduct() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",item)
                .putExtra("type",3).putExtra("select",spendProduct.getText().toString()),3);
    }
    //选择购买地点
    private void selectLocation() {
        LocationAdapter adapter=new LocationAdapter(saleaddress,this,spendLocation.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择购买地点").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                spendLocation.setText(baseBean.getName());
                addressId=String.valueOf(baseBean.getId());
                spendLocation.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择销售人员
    private void selectSaleMan() {
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",salesman)
                .putExtra("type",4).putExtra("select",spendSaleMan.getText().toString()),4);
    }
    //选择购买日期
    private void selectDate() {
        SelectDateDialog dialog=new SelectDateDialog(this);
        dialog.setTitle("选择购买日期").setTime(spendDate.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                createtime=date;
                spendDate.setText(date);
                spendDate.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //添加消费
    @Override
    public void addSpend(String result) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new SpendEvent(1));
        finish();
    }
    //编辑消费
    @Override
    public void editSpend(String result) {
        Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
        if(type==1){
            EventBus.getDefault().post(new SpendEvent(1));
        }else{
            EventBus.getDefault().post(new QueryResultEvent(1));
        }
        finish();
    }
    //获得添加消费所需列表
    @Override
    public void getListForSpending(HttpBean<InfoAddSpendBean> httpBean) {
        item= (ArrayList<ProductBean>) httpBean.getData().getItem();
        saleaddress=httpBean.getData().getSaleaddress();
        salesman= (ArrayList<BaseBean>) httpBean.getData().getSalesman();
        if(spendBean!=null){
            for (int i = 0; i < item.size(); i++) {
                if(item.get(i).getName().equals(spendBean.getItem_name())){
                    productId=String.valueOf(item.get(i).getId());
                }
            }
            for (int j = 0; j < saleaddress.size(); j++) {
                if(saleaddress.get(j).getName().equals(spendBean.getBuyaddress())){
                    addressId=String.valueOf(saleaddress.get(j).getId());
                }
            }
            for (int k = 0; k < salesman.size(); k++) {
                if(salesman.get(k).getName().equals(spendBean.getSaleman_name())){
                    saleManId=String.valueOf(salesman.get(k).getId());
                }
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==200){
            switch (requestCode){
                case 3:
                    productId=data.getStringExtra("id");
                    spendProduct.setText(data.getStringExtra("name"));
                    spendProduct.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
                case 4:
                    saleManId=data.getStringExtra("id");
                    spendSaleMan.setText(data.getStringExtra("name"));
                    spendSaleMan.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
}
