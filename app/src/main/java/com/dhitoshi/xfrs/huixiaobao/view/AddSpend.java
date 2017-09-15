package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddSpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PositionBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SaleaddressBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SalesmanBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddSpendManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.LocationAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.PositionAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddSpendPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private String createtime;
    private String productId;
    private String addressId;
    private String saleManId;
    private String price;
    private String number;
    private String discount;
    private String receive;
    private String debt;
    private String acNumber;
    private String waitNumber;
    private String notes;
    private AddSpendPresenter addSpendPresenter;
    private ArrayList<ProductBean> item;
    private List<BaseBean> saleaddress;
    private ArrayList<BaseBean> salesman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_spend);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        spendBean = getIntent().getParcelableExtra("spend");
        setTitle(null == spendBean ? "新增消费记录" : "编辑消费记录");
        if (null != spendBean) {
            initSpendInfo();
        }
        setRightText("提交");
        addSpendPresenter=new AddSpendPresenter(this);
        addSpendPresenter.getListForSpending();
    }
    private void initSpendInfo() {
        spendDate.setText(spendBean.getCreatetime());
        spendDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        spendProduct.setText(spendBean.getItem_name());
        spendProduct.setTextColor(getResources().getColor(R.color.colorPrimary));
        spendLocation.setText(spendBean.getBuyaddress());
        spendLocation.setTextColor(getResources().getColor(R.color.colorPrimary));
        spendSaleMan.setText(spendBean.getSaleman_name());
        spendSaleMan.setTextColor(getResources().getColor(R.color.colorPrimary));
        spendPrice.setText(spendBean.getCost());
        spendNumber.setText(spendBean.getNumber());
        spendDiscount.setText(spendBean.getDiscount());
        spendMoney.setText(spendBean.getAc_receive());
        spendDebt.setText(spendBean.getDebt());
        spendAcNum.setText(spendBean.getAc_num());
        spendWaitNum.setText(spendBean.getWait_num());
        spendNotes.setText(spendBean.getNotes());
        spendNotes.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    @OnClick({R.id.spend_date, R.id.spend_product, R.id.spend_location, R.id.spend_saleMan})
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
        }
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
    }
    //编辑消费
    @Override
    public void editSpend(String result) {
        Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
    }
    //获得添加消费所需列表
    @Override
    public void getListForSpending(HttpBean<InfoAddSpendBean> httpBean) {
        item= (ArrayList<ProductBean>) httpBean.getData().getItem();
        saleaddress=httpBean.getData().getSaleaddress();
        salesman= (ArrayList<BaseBean>) httpBean.getData().getSalesman();
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
