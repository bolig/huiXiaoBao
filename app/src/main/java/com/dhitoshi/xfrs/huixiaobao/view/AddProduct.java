package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddProductBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.ProductEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddProductManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddProductPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddProduct extends BaseView implements AddProductManage.View{
    @BindView(R.id.product_area)
    TextView productArea;
    @BindView(R.id.product_type)
    TextView productType;
    @BindView(R.id.product_name)
    EditText productName;
    @BindView(R.id.product_price)
    EditText productPrice;
    @BindView(R.id.product_describe)
    EditText productDescribe;
    private ProductBean productBean;
    private String name="";
    private String type_id="";
    private String area="";
    private String cost="";
    private String notes="";
    private AddProductPresenter addProductPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        productBean=getIntent().getParcelableExtra("product");
        setTitle(productBean==null?"新增产品":"编辑产品");
        if(productBean!=null){
            initProductInfo();
        }
        addProductPresenter=new AddProductPresenter(this,this);
        setRightText("提交");
    }
    private void initProductInfo() {
        area=productBean.getArea_id();
        productArea.setText(productBean.getArea());
        productArea.setTextColor(getResources().getColor(R.color.colorPrimary));
        type_id=productBean.getType_id();
        productType.setText(productBean.getType_name());
        productType.setTextColor(getResources().getColor(R.color.colorPrimary));
        name=productBean.getName();
        productName.setText(productBean.getName());
        cost=productBean.getCost();
        productPrice.setText(productBean.getCost());
        notes=productBean.getNotes();
        productDescribe.setText(productBean.getNotes());
    }
    @OnClick({R.id.right_text, R.id.product_area, R.id.product_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.product_area:
                selectArea();
                break;
            case R.id.product_type:
                selectType();
                break;
        }
    }
    private void commit() {
        if(juge()){
            AddProductBean bean=new AddProductBean();
            bean.setArea(area);
            bean.setCost(cost);
            bean.setName(name);
            bean.setNotes(notes);
            bean.setType_id(type_id);
            bean.setToken(SharedPreferencesUtil.Obtain(this,"token","").toString());
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            if(productBean==null) {
                addProductPresenter.addItem(bean,dialog);
            }else{
                bean.setId(String.valueOf(productBean.getId()));
                addProductPresenter.editItem(bean,dialog);
            }
        }
    }
    private boolean juge() {
        if(area.isEmpty()){
            Toast.makeText(this,"请选择所属区域",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(type_id.isEmpty()){
            Toast.makeText(this,"请选择产品类型",Toast.LENGTH_SHORT).show();
            return false;
        }
        name=productName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this,"请填写产品名称",Toast.LENGTH_SHORT).show();
            return false;
        }
        cost=productPrice.getText().toString();
        if(cost.isEmpty()){
            Toast.makeText(this,"请填写产品零售价",Toast.LENGTH_SHORT).show();
            return false;
        }
        notes=productDescribe.getText().toString();
        if(notes.isEmpty()){
            Toast.makeText(this,"请填写产品描述",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void selectArea() {
        startActivityForResult(new Intent(this,SelectArea.class),0);
    }
    private void selectType() {
        startActivityForResult(new Intent(this,SelectType.class).putExtra("selected",productType.getText().toString()),6);
    }
    @Override
    public void addItem(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new ProductEvent(1));
        finish();
    }
    @Override
    public void editItem(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new ProductEvent(1));
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==600){
            switch (requestCode){
                case 6:
                    type_id=data.getStringExtra("id");
                    productType.setText(data.getStringExtra("name"));
                    productType.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
        else if(resultCode==200){
            switch (requestCode){
                case 0:
                    area=data.getStringExtra("area_id");
                    productArea.setText(data.getStringExtra("area_name"));
                    productArea.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
}
