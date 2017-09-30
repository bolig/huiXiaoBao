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
import com.dhitoshi.xfrs.huixiaobao.Bean.PositionBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SexBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.QueryResultEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.RelationEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.SpendEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddRelationManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.CommonAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.PositionAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.SexAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddRelationPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
    private String workPosition="";
    private int userId;
    private List<BaseBean> relations;
    private List<BaseBean> positions;
    private RelationBean relationBean;
    private AddRelationPresenter addRelationPresenter;
    private int type=1;
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
        type=getIntent().getIntExtra("type",0);
        relationBean=getIntent().getParcelableExtra("relation");
        setTitle(relation==null?"新增关系记录":"编辑关系记录");
        if(null!=relationBean){
            initRelationInfo();
        }
        setRightText("提交");
        addRelationPresenter=new AddRelationPresenter(this,this);
        String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        addRelationPresenter.getListForRelation(token);
    }
    private void initRelationInfo() {
        name=relationBean.getName();
        relationName.setText(relationBean.getName());
        relationName.setTextColor(getResources().getColor(R.color.colorPrimary));
        sex=relationBean.getSex().equals("男")?"1":"0";
        relationSex.setText(relationBean.getSex());
        relationSex.setTextColor(getResources().getColor(R.color.colorPrimary));
        relationRelation.setText(relationBean.getRelation());
        relationRelation.setTextColor(getResources().getColor(R.color.colorPrimary));
        birthday=relationBean.getBirthday();
        relationBirthday.setText(relationBean.getBirthday());
        relationBirthday.setTextColor(getResources().getColor(R.color.colorPrimary));
        phone=relationBean.getPhone();
        relationPhone.setText(relationBean.getPhone());
        relationPhone.setTextColor(getResources().getColor(R.color.colorPrimary));
        telephone=relationBean.getTelephone();
        relationTelephone.setText(relationBean.getTelephone());
        relationTelephone.setTextColor(getResources().getColor(R.color.colorPrimary));
        email=relationBean.getEmail();
        relationEmail.setText(relationBean.getEmail());
        relationEmail.setTextColor(getResources().getColor(R.color.colorPrimary));
        company=relationBean.getCompany();
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
        List<SexBean> sexBeens=new ArrayList<>();
        sexBeens.add(new SexBean("0","男"));
        sexBeens.add(new SexBean("1","女"));
        SexAdapter adapter=new SexAdapter(sexBeens,this,relationSex.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择性别").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<SexBean>() {
            @Override
            public void onItemClick(View view, SexBean sexBean, int position) {
                relationSex.setText(sexBean.getName());
                sex=sexBean.getId();
                relationSex.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择关系
    private void SelectRelation() {
        CommonAdapter adapter=new CommonAdapter(relations,this,relationRelation.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择职位").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                relationRelation.setText(baseBean.getName());
                relation=String.valueOf(baseBean.getId());
                relationRelation.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择出生日期
    private void SelectBirthday() {
        SelectDateDialog dialog=new SelectDateDialog(this);
        dialog.setTitle("选择出生日期").setTime(relationBirthday.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                birthday=date;
                relationBirthday.setText(date);
                relationBirthday.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择职位
    private void SelectPosition() {
        CommonAdapter adapter=new CommonAdapter(positions,this,relationPosition.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择职位").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                relationPosition.setText(baseBean.getName());
                workPosition=String.valueOf(baseBean.getId());
                relationPosition.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
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
            bean.setPosition(workPosition);
            bean.setNotes(relationNotes.getText().toString());
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
            if(relationBean==null){
                bean.setUserid(String.valueOf(userId));
                addRelationPresenter.addRelation(token,bean,dialog);
            }else{
                bean.setId(String.valueOf(relationBean.getId()));
                addRelationPresenter.editRelation(token,bean,dialog);
            }
        }
    }

    private boolean juge() {
        name=relationName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(this,"请填写姓名",Toast.LENGTH_SHORT).show();
            return false;
        }
        phone=relationPhone.getText().toString();
        if(phone.isEmpty()){
            Toast.makeText(this,"请填写手机号码",Toast.LENGTH_SHORT).show();
            return false;
        }
        telephone=relationTelephone.getText().toString();
        email=relationEmail.getText().toString();
        company=relationCompany.getText().toString();
        return true;
    }

    @Override
    public void addRelation(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new RelationEvent(1));
        finish();
    }
    @Override
    public void editRelation(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        if(type==1){
            EventBus.getDefault().post(new QueryResultEvent(1));
        }else{
            EventBus.getDefault().post(new RelationEvent(1));
        }

        finish();
    }
    @Override
    public void getListForRelation(HttpBean<InfoAddRelationBean> httpBean) {
        relations=httpBean.getData().getRelation();
        positions=httpBean.getData().getPosition();
        if(relationBean!=null){
            for (int i = 0; i < relations.size(); i++) {
                if(relations.get(i).getName().equals(relationBean.getRelation())){
                    relation=String.valueOf(relations.get(i).getId());
                }
            }
            for (int j = 0; j < positions.size(); j++) {
                if(positions.get(j).getName().equals(relationBean.getPosition())){
                    workPosition=String.valueOf(positions.get(j).getId());
                }
            }
        }
    }
}
