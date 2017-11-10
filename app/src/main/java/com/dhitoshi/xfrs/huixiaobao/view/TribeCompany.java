package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Interface.TribeCompanyManage;
import com.dhitoshi.xfrs.huixiaobao.R;
public class TribeCompany extends BaseView implements TribeCompanyManage.View{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tribe_company);
        initView();
    }
    private void initView() {
        initBaseViews();
        setTitle("添加群成员");
    }
    @Override
    public void getCompanyList(HttpPageBeanTwo<BaseBean> httpPageBeanTwo) {

    }
}
