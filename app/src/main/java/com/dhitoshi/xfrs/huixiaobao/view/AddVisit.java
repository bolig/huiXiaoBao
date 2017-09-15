package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Bean.AddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddVisitManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddVisitPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddVisit extends BaseView implements AddVisitManage.View{

    @BindView(R.id.visit_client)
    EditText visitClient;
    @BindView(R.id.visit_salesman)
    TextView visitSalesman;
    @BindView(R.id.visit_date)
    TextView visitDate;
    @BindView(R.id.visit_nextDate)
    TextView visitNextDate;
    @BindView(R.id.visit_type)
    TextView visitType;
    @BindView(R.id.visit_content)
    EditText visitContent;
    @BindView(R.id.visit_notes)
    EditText visitNotes;
    @BindView(R.id.visit_suggest)
    EditText visitSuggest;
    private String createtime="";
    private String nexttime="";
    private String feedman="";
    private String feedtype="";
    private String feedbody="";
    private String notes="";
    private String advice="";
    private String img="";
    private int userId;
    private VisitBean visitBean;
    private List<BaseBean> feedmen;
    private List<BaseBean> feedtypes;
    private AddVisitPresenter addVisitPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_visit);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        userId=getIntent().getIntExtra("id",0);
        visitBean=getIntent().getParcelableExtra("visit");
        setTitle(visitBean==null?"新增回访记录":"编辑回访记录");
        if(visitBean!=null){
            initVisitInfo();
        }
        setRightText("提交");
        addVisitPresenter=new AddVisitPresenter(this);
        addVisitPresenter.getListForVisit();
    }
    private void initVisitInfo() {
        visitClient.setText(visitBean.getCustomer_name());
        visitClient.setTextColor(getResources().getColor(R.color.colorPrimary));
        visitSalesman.setText(visitBean.getFeedman_name());
        visitSalesman.setTextColor(getResources().getColor(R.color.colorPrimary));
        visitDate.setText(visitBean.getCreatetime());
        visitDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        visitNextDate.setText(visitBean.getNexttime());
        visitNextDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        visitType.setText(visitBean.getFeedtype());
        visitType.setTextColor(getResources().getColor(R.color.colorPrimary));
        visitContent.setText(visitBean.getFeedbody());
        visitContent.setTextColor(getResources().getColor(R.color.colorPrimary));
        visitNotes.setText(visitBean.getNotes());
        visitNotes.setTextColor(getResources().getColor(R.color.colorPrimary));
        visitSuggest.setText(visitBean.getAdvice());
        visitSuggest.setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    @OnClick({R.id.right_text, R.id.visit_salesman, R.id.visit_date, R.id.visit_nextDate, R.id.visit_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_text:
                commit();
                break;
            case R.id.visit_salesman:
                selectSalesMan();
                break;
            case R.id.visit_date:
                selectDate();
                break;
            case R.id.visit_nextDate:
                selectNextDate();
                break;
            case R.id.visit_type:
                selectType();
                break;
        }
    }
    private void selectSalesMan() {
    }
    private void selectDate() {

    }
    private void selectNextDate() {

    }
    private void selectType() {

    }
    private void commit() {
        if(juge()){
            AddVisitBean bean=new AddVisitBean();
            bean.setNotes(notes);
            bean.setCreatetime(createtime);
            bean.setAdvice(advice);
            bean.setFeedbody(feedbody);
            bean.setFeedman(feedman);
            bean.setFeedtype(feedtype);
            bean.setImg(img);
            bean.setNexttime(nexttime);
            bean.setUserid(String.valueOf(userId));
            if(visitBean==null){
                addVisitPresenter.addVisit(bean);
            }else{
                addVisitPresenter.editVisit(bean);
            }
        }
    }
    private boolean juge() {
        feedman=visitSalesman.getText().toString();
        if(feedman.isEmpty()){
            Toast.makeText(this,"请选择回访人",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(createtime.isEmpty()){
            Toast.makeText(this,"请选择回访时间",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nexttime.isEmpty()){
            Toast.makeText(this,"请选择下次回访时间",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(feedtype.isEmpty()){
            Toast.makeText(this,"请选择回访类型",Toast.LENGTH_SHORT).show();
            return false;
        }
        feedbody=visitContent.getText().toString();
        if(feedbody.isEmpty()){
            Toast.makeText(this,"请填写回访内容",Toast.LENGTH_SHORT).show();
            return false;
        }
        notes=visitNotes.getText().toString();
        if(notes.isEmpty()){
            Toast.makeText(this,"请填写回访备注",Toast.LENGTH_SHORT).show();
            return false;
        }
        advice=visitSuggest.getText().toString();
        if(advice.isEmpty()){
            Toast.makeText(this,"请填写客户建议",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    @Override
    public void addVisit(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void editVisit(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void getListForVisit(HttpBean<InfoAddVisitBean> httpBean) {
        feedmen=httpBean.getData().getFeedman();
        feedtypes=httpBean.getData().getFeedtype();
    }
}
