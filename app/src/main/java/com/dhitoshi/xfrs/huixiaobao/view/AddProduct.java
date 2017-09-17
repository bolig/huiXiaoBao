package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddProduct extends BaseView {
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
        setRightText("提交");
    }
    private void initProductInfo() {
        area=productBean.getArea_id();
        productArea.setText(productBean.getArea());
        productType.setText(productBean.getType_name());
        name=productBean.getName();
        productName.setText(productBean.getName());
        productName.setTextColor(getResources().getColor(R.color.colorPrimary));
        cost=productBean.getCost();
        productPrice.setText(productBean.getCost());
        productPrice.setTextColor(getResources().getColor(R.color.colorPrimary));
        notes=productBean.getNotes();
        productDescribe.setText(productBean.getNotes());
        productDescribe.setTextColor(getResources().getColor(R.color.colorPrimary));
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
    }
    private void selectType() {

    }
}
