package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
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
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.VisitEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddVisitManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.CommonAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddVisitPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddVisit extends BaseView implements AddVisitManage.View{

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
    private ArrayList<BaseBean> feedmen;
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
        addVisitPresenter=new AddVisitPresenter(this,this);
        String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
        addVisitPresenter.getListForVisit(token);
    }
    private void initVisitInfo() {
        visitSalesman.setText(visitBean.getFeedman_name());
        visitSalesman.setTextColor(getResources().getColor(R.color.colorPrimary));
        createtime=visitBean.getCreatetime();
        visitDate.setText(visitBean.getCreatetime());
        visitDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        nexttime=visitBean.getNexttime();
        visitNextDate.setText(visitBean.getNexttime());
        visitNextDate.setTextColor(getResources().getColor(R.color.colorPrimary));
        visitType.setText(visitBean.getFeedtype());
        visitType.setTextColor(getResources().getColor(R.color.colorPrimary));
        feedbody=visitBean.getFeedbody();
        visitContent.setText(visitBean.getFeedbody());
        visitContent.setTextColor(getResources().getColor(R.color.colorPrimary));
        notes=visitBean.getNotes();
        visitNotes.setText(visitBean.getNotes());
        visitNotes.setTextColor(getResources().getColor(R.color.colorPrimary));
        advice=visitBean.getAdvice();
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
        startActivityForResult(new Intent(this,Select.class).putParcelableArrayListExtra("list",feedmen)
                .putExtra("type",5).putExtra("select",visitSalesman.getText().toString()),5);
    }
    private void selectDate() {
        SelectDateDialog dialog=new SelectDateDialog(this);
        dialog.setTitle("选择回访日期").setTime(visitDate.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                createtime=date;
                visitDate.setText(date);
                visitDate.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    private void selectNextDate() {
        SelectDateDialog dialog=new SelectDateDialog(this);
        dialog.setTitle("选择再次回访日期").setTime(visitNextDate.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                nexttime=date;
                visitNextDate.setText(date);
                visitNextDate.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    private void selectType() {
        CommonAdapter adapter=new CommonAdapter(feedtypes,this,visitType.getText().toString());
        final SelectDialog dialog=new SelectDialog(this);
        dialog.setTitle("选择回访方式").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                visitType.setText(baseBean.getName());
                feedtype=String.valueOf(baseBean.getId());
                visitType.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
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
            LoadingDialog dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
            if(visitBean==null){
                bean.setUserid(String.valueOf(userId));
                addVisitPresenter.addVisit(token,bean,dialog);
            }else{
                bean.setId(String.valueOf(visitBean.getId()));
                addVisitPresenter.editVisit(token,bean,dialog);
            }
        }
    }
    private boolean juge() {
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
        EventBus.getDefault().post(new VisitEvent(1));
        finish();
    }
    @Override
    public void editVisit(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new VisitEvent(1));
        finish();
    }
    @Override
    public void getListForVisit(HttpBean<InfoAddVisitBean> httpBean) {
        feedmen= (ArrayList<BaseBean>) httpBean.getData().getFeedman();
        feedtypes=httpBean.getData().getFeedtype();
        if(visitBean!=null){
            for (int i = 0; i < feedmen.size(); i++) {
                if(feedmen.get(i).getName().equals(visitBean.getFeedman_name())){
                    feedman=String.valueOf(feedmen.get(i).getId());
                }
            }
            for (int j = 0; j < feedtypes.size(); j++) {
                if(feedtypes.get(j).getName().equals(visitBean.getFeedtype())){
                    feedtype=String.valueOf(feedtypes.get(j).getId());
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==200){
            switch (requestCode){
                case 5:
                    feedman=data.getStringExtra("id");
                    visitSalesman.setText(data.getStringExtra("name"));
                    visitSalesman.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }
}
