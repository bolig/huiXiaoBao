package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SexBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.QueryManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.SexAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDateDialog;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.QueryPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Query extends BaseView implements QueryManage.View{
    @BindView(R.id.query_result)
    TextView queryResult;
    @BindView(R.id.query_area)
    TextView queryArea;
    @BindView(R.id.query_name)
    EditText queryName;
    @BindView(R.id.query_sex)
    TextView querySex;
    @BindView(R.id.query_phone)
    EditText queryPhone;
    @BindView(R.id.query_vip)
    EditText queryVip;
    @BindView(R.id.query_address)
    EditText queryAddress;
    @BindView(R.id.query_company)
    EditText queryCompany;
    @BindView(R.id.query_position)
    TextView queryPosition;
    @BindView(R.id.createtime_start)
    TextView createtimeStart;
    @BindView(R.id.createtime_end)
    TextView createtimeEnd;
    @BindView(R.id.totalnum_start)
    EditText totalnumStart;
    @BindView(R.id.totalnum_end)
    EditText totalnumEnd;
    @BindView(R.id.query_sales)
    TextView querySales;
    @BindView(R.id.buy_address)
    TextView buyAddress;
    @BindView(R.id.buy_item)
    TextView buyItem;
    @BindView(R.id.buytime_start)
    TextView buytimeStart;
    @BindView(R.id.buytime_end)
    TextView buytimeEnd;
    @BindView(R.id.visit_start)
    TextView visitStart;
    @BindView(R.id.visit_end)
    TextView visitEnd;
    @BindView(R.id.unvisit_start)
    TextView unvisitStart;
    @BindView(R.id.unvisit_end)
    TextView unvisitEnd;
    private QueryPresenter queryPresenter;
    private String sex="";
    private String type="";
    private String area="";

    private String createtime_st="";
    private String createtime_end="";
    private String buytime_st="";
    private String buytime_end="";
    private String feedback_time_st="";
    private String feedback_time_end="";
    private String feedback_time_st_un="";
    private String feedback_time_end_un="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("高级查询");
        queryPresenter=new QueryPresenter(this,this);
        queryPresenter.getListForSearch(SharedPreferencesUtil.Obtain(this,"token","").toString());
    }
    @OnClick({R.id.query_result, R.id.query_area, R.id.query_sex, R.id.query_position, R.id.createtime_start,
            R.id.createtime_end, R.id.query_sales, R.id.buy_address, R.id.buy_item, R.id.buytime_start,
            R.id.buytime_end, R.id.visit_start, R.id.visit_end, R.id.unvisit_start, R.id.unvisit_end,R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.query_result:
                selectResult();
                break;
            case R.id.query_area:
                startActivityForResult(new Intent(this, SelectArea.class), 5);
                break;
            case R.id.query_sex:
                selectSex();
                break;
            case R.id.query_position:
                break;
            case R.id.createtime_start:
                selectCreateStart();
                break;
            case R.id.createtime_end:
                selectCreateEnd();
                break;
            case R.id.query_sales:
                break;
            case R.id.buy_address:
                break;
            case R.id.buy_item:
                break;
            case R.id.buytime_start:
                selectBuyStart();
                break;
            case R.id.buytime_end:
                selectBuyEnd();
                break;
            case R.id.visit_start:
                selectVisitStart();
                break;
            case R.id.visit_end:
                selectVisitEnd();
                break;
            case R.id.unvisit_start:
                selectUnVisitStart();
                break;
            case R.id.unvisit_end:
                selectUnVisitEnd();
                break;
            case R.id.query:
                break;
        }
    }
    //选择回访日期结束时间
    private void selectVisitEnd() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择结束日期").setTime(visitEnd.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                feedback_time_end = date;
                visitEnd.setText(date);
                visitEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择回访日期开始时间
    private void selectVisitStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择开始日期").setTime(visitStart.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                feedback_time_st = date;
                visitStart.setText(date);
                visitStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择购买结束日期
    private void selectBuyEnd() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择结束日期").setTime(buytimeEnd.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                buytime_end = date;
                buytimeEnd.setText(date);
                buytimeEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择购买开始日期
    private void selectBuyStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择开始日期").setTime(buytimeStart.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                buytime_st = date;
                buytimeStart.setText(date);
                buytimeStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择录入结束时间
    private void selectCreateEnd() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择结束日期").setTime(createtimeEnd.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                createtime_st = date;
                createtimeEnd.setText(date);
                createtimeEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择录入开始时间
    private void selectCreateStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择开始日期").setTime(createtimeStart.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                createtime_end = date;
                createtimeStart.setText(date);
                createtimeStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择未访日期开始时间
    private void selectUnVisitStart() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择开始日期").setTime(unvisitStart.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                feedback_time_st_un = date;
                unvisitStart.setText(date);
                unvisitStart.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择未访日期结束时间
    private void selectUnVisitEnd() {
        SelectDateDialog dialog = new SelectDateDialog(this);
        dialog.setTitle("选择结束日期").setTime(unvisitEnd.getText().toString()).getDate(new DateCallBack() {
            @Override
            public void getDate(String date) {
                feedback_time_end_un = date;
                unvisitEnd.setText(date);
                unvisitEnd.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
        }).show();
    }
    //选择性别
    private void selectSex() {
        List<SexBean> sexBeens = new ArrayList<>();
        sexBeens.add(new SexBean("0", "男"));
        sexBeens.add(new SexBean("1", "女"));
        SexAdapter adapter = new SexAdapter(sexBeens, this, querySex.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择性别").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<SexBean>() {
            @Override
            public void onItemClick(View view, SexBean sexBean, int position) {
                querySex.setText(sexBean.getName());
                sex = sexBean.getId();
                querySex.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    //选择查询结果
    private void selectResult() {
        List<SexBean> sexBeens = new ArrayList<>();
        sexBeens.add(new SexBean("1", "基本资料"));
        sexBeens.add(new SexBean("2", "消费记录"));
        sexBeens.add(new SexBean("3", "回访记录"));
        sexBeens.add(new SexBean("4", "社会关系"));
        sexBeens.add(new SexBean("5", "赠品记录"));
        sexBeens.add(new SexBean("6", "参会记录"));
        SexAdapter adapter = new SexAdapter(sexBeens, this, queryResult.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择查询结果").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<SexBean>() {
            @Override
            public void onItemClick(View view, SexBean sexBean, int position) {
                queryResult.setText(sexBean.getName());
                type = sexBean.getId();
                queryResult.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (resultCode == 200) {
            switch (requestCode) {
                case 5:
                    area = data.getStringExtra("area_id");
                    queryArea.setText(data.getStringExtra("area_name"));
                    queryArea.setTextColor(getResources().getColor(R.color.colorPrimary));
                    break;
            }
        }
    }

    @Override
    public void getListForSearch(HttpBean<Object> httpBean) {

    }

    @Override
    public void getSearchOne(HttpBean<PageBean<ClientBean>> httpBean) {

    }

    @Override
    public void getSearchTwo(HttpBean<PageBean<SpendBean>> httpBean) {

    }

    @Override
    public void getSearchThree(HttpBean<PageBean<VisitBean>> httpBean) {

    }

    @Override
    public void getSearchFour(HttpBean<PageBean<RelationBean>> httpBean) {

    }

    @Override
    public void getSearchFive(HttpBean<PageBean<GiftBean>> httpBean) {

    }

    @Override
    public void getSearchSix(HttpBean<PageBean<MeetBean>> httpBean) {

    }
}
