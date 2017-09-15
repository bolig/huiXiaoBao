package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddRelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddRelationManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddRelationPresenter;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class AddRelation extends BaseView implements AddRelationManage.View{

    @BindView(R.id.relation_name)
    EditText relationName;
    @BindView(R.id.relation_sex)
    TextView relationSex;
    @BindView(R.id.relation_relation)
    TextView relationRelation;
    @BindView(R.id.relation_birthday)
    TextView relationBirthday;
    @BindView(R.id.relation_phone)
    EditText relationPhone;
    @BindView(R.id.relation_telephone)
    EditText relationTelephone;
    @BindView(R.id.relation_email)
    EditText relationEmail;
    @BindView(R.id.relation_company)
    EditText relationCompany;
    @BindView(R.id.relation_position)
    TextView relationPosition;
    @BindView(R.id.relation_notes)
    EditText relationNotes;
    private String name="";
    private String sex="";
    private String relation="";
    private String birthday="";
    private String phone="";
    private String telephone="";
    private String email="";
    private String company="";
    private String position="";
    private int userId;
    private List<BaseBean> relations;
    private List<BaseBean> positions;
    private RelationBean relationBean;
    private AddRelationPresenter addRelationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_relation);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        userId=getIntent().getIntExtra("id",0);
        relationBean=getIntent().getParcelableExtra("relation");
        setTitle(relation==null?"新增关系记录":"编辑关系记录");
        if(null!=relationBean){
            initRelationInfo();
        }
        setRightText("提交");
        addRelationPresenter=new AddRelationPresenter(this);
        addRelationPresenter.getListForRelation();
    }
    private void initRelationInfo() {
        relationName.setText(relationBean.getName());
        relationName.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationSex.setText(relationBean.getSex());
        relationSex.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationRelation.setText(relationBean.getRelation());
        relationRelation.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationBirthday.setText(relationBean.getBirthday());
        relationBirthday.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationPhone.setText(relationBean.getPhone());
        relationPhone.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationTelephone.setText(relationBean.getTelephone());
        relationTelephone.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationEmail.setText(relationBean.getEmail());
        relationEmail.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationCompany.setText(relationBean.getCompany());
        relationCompany.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationPosition.setText(relationBean.getPosition());
        relationPosition.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationNotes.setText(relationBean.getNotes());
        relationNotes.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @OnClick({R.id.right_text, R.id.relation_sex, R.id.relation_relation, R.id.relation_birthday, R.id.relation_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.relation_sex:
                SelectSex();
                break;
            case R.id.relation_relation:
                SelectRelation();
                break;
            case R.id.relation_birthday:
                SelectBirthday();
                break;
            case R.id.relation_position:
                SelectPosition();
                break;
        }
    }
    //选择性别
    private void SelectSex() {

    }
    //选择关系
    private void SelectRelation() {

    }
    //选择出生日期
    private void SelectBirthday() {

    }
    //选择职位
    private void SelectPosition() {

    }
    //提交
    private void commit() {
        if(juge()){
            AddRelationBean bean=new AddRelationBean();
            bean.setName(name);
            bean.setSex(sex);
            bean.setRelation(relation);
            bean.setBirthday(birthday);
            bean.setPhone(phone);
            bean.setTelephone(telephone);
            bean.setEmail(email);
            bean.setCompany(company);
            bean.setPosition(position);
            bean.setNotes(relationNotes.getText().toString());
            if(relationBean==null){
                addRelationPresenter.addRelation(bean);
            }else{
                addRelationPresenter.editRelation(bean);
            }
        }
    }

    private boolean juge() {
        name=relationName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this,"请填写姓名",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(sex.isEmpty()){
            Toast.makeText(this,"请选择性别",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(relation.isEmpty()){
            Toast.makeText(this,"请选择关系",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(birthday.isEmpty()){
            Toast.makeText(this,"请选择出生日期",Toast.LENGTH_SHORT).show();
            return false;
        }
        phone=relationPhone.getText().toString();
        if(phone.isEmpty()){
            Toast.makeText(this,"请填写手机号码",Toast.LENGTH_SHORT).show();
            return false;
        }
        telephone=relationTelephone.getText().toString();
        if(telephone.isEmpty()){
            Toast.makeText(this,"请填写联系电话",Toast.LENGTH_SHORT).show();
            return false;
        }
        email=relationEmail.getText().toString();
        if(email.isEmpty()){
            Toast.makeText(this,"请填写电子邮箱",Toast.LENGTH_SHORT).show();
            return false;
        }
        company=relationCompany.getText().toString();
        if(company.isEmpty()){
            Toast.makeText(this,"请填写工作单位",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(position.isEmpty()){
            Toast.makeText(this,"请选择职位",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void addRelation(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void editRelation(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void getListForRelation(HttpBean<InfoAddRelationBean> httpBean) {
        relations=httpBean.getData().getRelation();
        positions=httpBean.getData().getPosition();
    }
}
