package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.ProductEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.ProductTypeEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddProductTypeManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddProductTypePresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddProductType extends BaseView implements AddProductTypeManage.View{
    @BindView(R.id.typeName)
    EditText typeName;
    private BaseBean baseBean;
    private String name="";
    private String id="";
    private AddProductTypePresenter addProductTypePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_type);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"AddProductType");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("AddProductType");
    }

    private void initViews() {
        initBaseViews();
        setRightText("提交");
        baseBean=getIntent().getParcelableExtra("type");
        setTitle(baseBean==null?"新增产品类型":"编辑产品类型");
        if(baseBean!=null){
            initProductType();
        }
        addProductTypePresenter=new AddProductTypePresenter(this,this);
    }
    private void initProductType() {
        name=baseBean.getName();
        typeName.setText(baseBean.getName());
        id=String.valueOf(baseBean.getId());
    }

    @OnClick(R.id.right_text)
    public void onViewClicked() {
        name=typeName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this,"请填写产品类型",Toast.LENGTH_SHORT).show();
            return;
        }
        String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
        dialog.show();
        if(baseBean==null){
            addProductTypePresenter.addItemType(token,name,dialog);
        }else{
            addProductTypePresenter.editItemType(id,token,name,dialog);
        }
    }

    @Override
    public void addItemType(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new ProductTypeEvent(1));
        finish();
    }

    @Override
    public void editItemType(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new ProductTypeEvent(1));
        finish();
    }
}
