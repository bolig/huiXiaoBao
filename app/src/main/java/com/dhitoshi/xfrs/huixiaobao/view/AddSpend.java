package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    private List<ProductBean> item;
    private List<SaleaddressBean> saleaddress;
    private List<SalesmanBean> salesman;
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

    }
    //选择购买地点
    private void selectLocation() {
        LocationAdapter adapter=new LocationAdapter(saleaddress,this,spendLocation.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择购买地点").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<SaleaddressBean>() {
            @Override
            public void onItemClick(View view, SaleaddressBean saleaddressBean, int position) {
                spendLocation.setText(saleaddressBean.getName());
                addressId=String.valueOf(saleaddressBean.getId());
                spendLocation.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择销售人员
    private void selectSaleMan() {

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
    @Override
    public void addSpend(String result) {

    }

    @Override
    public void editSpend(String result) {

    }

    @Override
    public void getListForSpending(HttpBean<InfoAddSpendBean> httpBean) {
        item=httpBean.getData().getItem();
        saleaddress=httpBean.getData().getSaleaddress();
        salesman=httpBean.getData().getSalesman();
    }
}
