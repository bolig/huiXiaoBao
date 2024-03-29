package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.InfoAddVisitBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Dialog.LoadingDialog;
import com.dhitoshi.xfrs.huixiaobao.Event.QueryResultEvent;
import com.dhitoshi.xfrs.huixiaobao.Event.VisitEvent;
import com.dhitoshi.xfrs.huixiaobao.Interface.AddVisitManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.CommonAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.presenter.AddVisitPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private int type=1;
    private Map<String,String> map;
    private LoadingDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_visit);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"AddVisit");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("AddVisit");
    }

    private void initViews() {
        initBaseViews();
        userId=getIntent().getIntExtra("id",0);
        type=getIntent().getIntExtra("type",0);
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
                if(null==feedman){
                    reListForVisit(0);
                }else{
                    selectSalesMan();
                }
                break;
            case R.id.visit_date:
                selectDate();
                break;
            case R.id.visit_nextDate:
                selectNextDate();
                break;
            case R.id.visit_type:
                if(null==feedtypes){
                    reListForVisit(1);
                }else{
                    selectType();
                }
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
            if(map==null){
                map=new HashMap<>();
            }
            if(!createtime.isEmpty()){
                map.put("createtime",createtime);
            }
            if(!nexttime.isEmpty()){
                map.put("nexttime",nexttime);
            }
            if(!feedbody.isEmpty()){
                map.put("feedbody",feedbody);
            }
            if(!advice.isEmpty()){
                map.put("advice",advice);
            }
            if(!notes.isEmpty()){
                map.put("notes",notes);
            }
            dialog = LoadingDialog.build(this).setLoadingTitle("提交中");
            dialog.show();
            String token= SharedPreferencesUtil.Obtain(this,"token","").toString();
            map.put("token",token);
            if(visitBean==null){
                map.put("userid",String.valueOf(userId));
                if(!feedman.isEmpty()){
                    map.put("feedman",feedman);
                }
                if(!feedtype.isEmpty()){
                    map.put("feedtype",feedtype);
                }
                addVisitPresenter.addVisit(map,dialog);
            }else{
                map.put("id",String.valueOf(visitBean.getId()));
                if(!TextUtils.isEmpty(visitBean.getFeedman_name())&&TextUtils.isEmpty(feedman)){
                    reListForVisit(2);
                }else if(!TextUtils.isEmpty(visitBean.getFeedtype())&&TextUtils.isEmpty(feedtype)){
                    reListForVisit(2);
                }else {
                    if(!feedman.isEmpty()){
                        map.put("feedman",feedman);
                    }
                    if(!feedtype.isEmpty()){
                        map.put("feedtype",feedtype);
                    }
                    addVisitPresenter.editVisit(map,dialog);
                }

            }
        }
    }
    private boolean juge() {
        if(createtime.isEmpty()){
            Toast.makeText(this,"请选择回访时间",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(nexttime.isEmpty()){
            Toast.makeText(this,"请选择再次回访时间",Toast.LENGTH_SHORT).show();
            return false;
        }
        feedbody=visitContent.getText().toString();
        notes=visitNotes.getText().toString();
        advice=visitSuggest.getText().toString();
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
        if(type==1){
            EventBus.getDefault().post(new QueryResultEvent(1));
        }else{
            EventBus.getDefault().post(new VisitEvent(1));
        }
        finish();
    }
    private void reListForVisit(final int type){
        MyHttp http=MyHttp.getInstance();
        String token=SharedPreferencesUtil.Obtain(this,"token","").toString();
        http.send(http.getHttpService().getListForVisit(token),new CommonObserver(new HttpResult<HttpBean<InfoAddVisitBean>>() {
            @Override
            public void OnSuccess(HttpBean<InfoAddVisitBean> httpBean) {
                if(httpBean.getStatus().getCode()==200) {
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
                    if(type==0){
                        selectSalesMan();
                    }else if(type==1){
                        selectType();
                    }else{
                        if(!feedman.isEmpty()){
                            map.put("feedman",feedman);
                        }
                        if(!feedtype.isEmpty()){
                            map.put("feedtype",feedtype);
                        }
                        addVisitPresenter.editVisit(map,dialog);
                    }
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(AddVisit.this, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                           reListForVisit(type);
                        }
                    });
                }else{
                    Toast.makeText(AddVisit.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                    if(type==2){
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void OnFail(String msg) {
                Toast.makeText(AddVisit.this,msg,Toast.LENGTH_SHORT).show();
                if(type==2){
                    dialog.dismiss();
                }
            }
        }));
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
