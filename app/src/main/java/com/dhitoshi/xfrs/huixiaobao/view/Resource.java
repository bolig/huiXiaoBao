package com.dhitoshi.xfrs.huixiaobao.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ResourceBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ResourceManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.ResourcePresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Resource extends BaseView implements ResourceManage.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.now_count)
    TextView nowCount;
    @BindView(R.id.today_count)
    TextView todayCount;
    @BindView(R.id.week_count)
    TextView weekCount;
    @BindView(R.id.mouth_count)
    TextView mouthCount;
    @BindView(R.id.sales_today)
    TextView salesToday;
    @BindView(R.id.sales_week)
    TextView salesWeek;
    @BindView(R.id.sales_mouth)
    TextView salesMouth;
    @BindView(R.id.visit_count)
    TextView visitCount;
    private ResourcePresenter resourcePresenter;
    private ArrayList<ClientBean> customer_month;
    private ArrayList<ClientBean> customer_now;
    private ArrayList<ClientBean> customer_today;
    private ArrayList<ClientBean> customer_week;
    private ArrayList<ClientBean> feedback_today;
    private ArrayList<SpendBean> sale_today;
    private ArrayList<SpendBean> sale_week;
    private ArrayList<SpendBean> sale_month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resource);
        ButterKnife.bind(this);
        initViews();
        ActivityManagerUtil.addDestoryActivity(this,"Resource");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerUtil.destoryActivity("Resource");
    }

    private void initViews() {
        initBaseViews();
        setTitle("我的资源");
        resourcePresenter = new ResourcePresenter(this, this);
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setDisableContentWhenRefresh(true);//是否在刷新的时候禁止列表的操作
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                resourcePresenter.getMyResource(SharedPreferencesUtil.Obtain(Resource.this, "token", "").toString(), smartRefreshLayout);
            }
        });
    }
    @Override
    public void getMyResource(HttpBean<ResourceBean> httpBean) {
        nowCount.setText(httpBean.getData().getCustomer_now_count() + "");
        todayCount.setText(httpBean.getData().getCustomer_today_count() + "");
        weekCount.setText(httpBean.getData().getCustomer_week_count() + "");
        mouthCount.setText(httpBean.getData().getCustomer_month_count() + "");
        salesToday.setText(httpBean.getData().getSale_today_total() + "");
        salesWeek.setText(httpBean.getData().getSale_week_total() + "");
        salesMouth.setText(httpBean.getData().getSale_month_total() + "");
        visitCount.setText(httpBean.getData().getFeedback_today_count() + "");
        customer_month= (ArrayList<ClientBean>) httpBean.getData().getCustomer_month();
        customer_now= (ArrayList<ClientBean>) httpBean.getData().getCustomer_now();
        customer_today= (ArrayList<ClientBean>) httpBean.getData().getCustomer_today();
        customer_week= (ArrayList<ClientBean>) httpBean.getData().getCustomer_week();
        feedback_today= (ArrayList<ClientBean>) httpBean.getData().getFeedback_today();
        sale_today= (ArrayList<SpendBean>) httpBean.getData().getSale_today();
        sale_week= (ArrayList<SpendBean>) httpBean.getData().getSale_week();
        sale_month= (ArrayList<SpendBean>) httpBean.getData().getSale_month();
    }
    @OnClick({R.id.now_client, R.id.today_client, R.id.week_client, R.id.mouth_client, R.id.today_sales,
            R.id.week_sales, R.id.mouth_sales, R.id.visit_client})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.now_client:
                if(customer_now.size()==0){
                    Toast.makeText(this,"没有现有客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",customer_now)
                            .putExtra("title",customer_now.size()+"个我的现有客户"));
                }
                break;
            case R.id.today_client:
                if(customer_today.size()==0){
                    Toast.makeText(this,"今天没有增加客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",customer_today)
                            .putExtra("title",customer_today.size()+"个今天增加的客户"));
                }
                break;
            case R.id.week_client:
                if(customer_month.size()==0){
                    Toast.makeText(this,"本周没有增加客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",customer_week)
                            .putExtra("title",customer_week.size()+"个本周增加的客户"));
                }
                break;
            case R.id.mouth_client:
                if(customer_month.size()==0){
                    Toast.makeText(this,"本月没有增加客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",customer_month)
                            .putExtra("title",customer_month.size()+"个本月增加的客户"));
                }
                break;
            case R.id.today_sales:
                if(sale_today.size()==0){
                    Toast.makeText(this,"今天没有销售额",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,SalesInfo.class).putParcelableArrayListExtra("spends",sale_today)
                            .putExtra("title","今天没有销售额"));
                }
                break;
            case R.id.week_sales:
                if(sale_week.size()==0){
                    Toast.makeText(this,"本周没有销售额",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,SalesInfo.class).putParcelableArrayListExtra("spends",sale_week)
                            .putExtra("title","本周没有销售额"));
                }
                break;
            case R.id.mouth_sales:
                if(sale_month.size()==0){
                    Toast.makeText(this,"本月没有销售额",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,SalesInfo.class).putParcelableArrayListExtra("spends",sale_month)
                            .putExtra("title","本月没有销售额"));
                }
                break;
            case R.id.visit_client:
                if(feedback_today.size()==0){
                    Toast.makeText(this,"今天没有回访客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",feedback_today)
                            .putExtra("title",feedback_today.size()+"个今天回访的客户"));
                }

                break;
        }
    }
}
