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
import com.dhitoshi.xfrs.huixiaobao.Bean.RemindBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.RemindManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.RemindPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Remind extends BaseView implements RemindManage.View {
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.today_count)
    TextView todayCount;
    @BindView(R.id.mouth_count)
    TextView mouthCount;
    @BindView(R.id.visit_count)
    TextView visitCount;
    @BindView(R.id.feedtime_count)
    TextView feedtimeCount;
    @BindView(R.id.buy_count)
    TextView buyCount;
    @BindView(R.id.visit_uncount)
    TextView visitUncount;
    @BindView(R.id.buy_uncount)
    TextView buyUncount;
    private RemindPresenter remindPresenter;
    private ArrayList<ClientBean> birthday_today;
    private ArrayList<ClientBean> birthday_month;
    private ArrayList<ClientBean> feedtime_un;
    private ArrayList<ClientBean> feedtime_today;
    private ArrayList<ClientBean> feedtime_month;
    private ArrayList<ClientBean> buy_un;
    private ArrayList<ClientBean> buy_d_month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remind);
        ButterKnife.bind(this);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("客户提醒");
        remindPresenter = new RemindPresenter(this, this);
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setDisableContentWhenRefresh(true);//是否在刷新的时候禁止列表的操作
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                remindPresenter.getRemind( SharedPreferencesUtil.Obtain(Remind.this, "token", "").toString(), smartRefreshLayout);
            }
        });

    }
    @Override
    public void getRemind(HttpBean<RemindBean> httpBean) {
        birthday_today= (ArrayList<ClientBean>) httpBean.getData().getBirthday_today();
        birthday_month= (ArrayList<ClientBean>) httpBean.getData().getBirthday_month();
        feedtime_un= (ArrayList<ClientBean>) httpBean.getData().getFeedtime_un();
        feedtime_today= (ArrayList<ClientBean>) httpBean.getData().getFeedtime_today();
        feedtime_month= (ArrayList<ClientBean>) httpBean.getData().getFeedtime_month();
        buy_un= (ArrayList<ClientBean>) httpBean.getData().getBuy_un();
        buy_d_month= (ArrayList<ClientBean>) httpBean.getData().getBuy_d_month();

        todayCount.setText(httpBean.getData().getBirthday_today_count() + "");
        mouthCount.setText(httpBean.getData().getBirthday_month_count() + "");
        visitUncount.setText(httpBean.getData().getFeedtime_un_count() + "");
        visitCount.setText(httpBean.getData().getFeedtime_month_count() + "");
        feedtimeCount.setText(httpBean.getData().getFeedtime_today_count() + "");
        buyUncount.setText(httpBean.getData().getBuy_un_count() + "");
        buyCount.setText(httpBean.getData().getBuy_d_month_count() + "");
    }

    @OnClick({R.id.birthday_today, R.id.birthday_mouth, R.id.visit_mouth, R.id.feedtime_today, R.id.buy_mouth, R.id.visit_un, R.id.buy_un})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.birthday_today:
                if(birthday_today.size()==0){
                    Toast.makeText(this,"今天没有过生日的客户", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",birthday_today)
                            .putExtra("title",birthday_today.size()+"个今天过生日的客户"));
                }
                break;
            case R.id.birthday_mouth:
                if(birthday_month.size()==0){
                    Toast.makeText(this,"30天内没有过生日的客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",birthday_month)
                            .putExtra("title",birthday_month.size()+"个30天内过生日的客户"));
                }
                break;
            case R.id.visit_mouth:
                if(feedtime_month.size()==0){
                    Toast.makeText(this,"没有30天内没有回访的客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",feedtime_month)
                            .putExtra("title",feedtime_month.size()+"个30天内没有回访的客户"));
                }
                break;
            case R.id.feedtime_today:
                if(feedtime_today.size()==0){
                    Toast.makeText(this,"今日没有预定回访的客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",feedtime_today)
                            .putExtra("title",feedtime_today.size()+"个今日预定回访的客户"));
                }
                break;
            case R.id.buy_mouth:
                if(buy_d_month.size()==0){
                    Toast.makeText(this,"没有60天内没有购买的客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",buy_d_month)
                            .putExtra("title",buy_d_month.size()+"个60天内没有购买的客户"));
                }
                break;
            case R.id.visit_un:
                if(feedtime_un.size()==0){
                    Toast.makeText(this,"没有从未回访的客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",feedtime_un)
                            .putExtra("title",feedtime_un.size()+"个从未回访的客户"));
                }
                break;
            case R.id.buy_un:
                if(buy_un.size()==0){
                    Toast.makeText(this,"没有从未购买过的客户",Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(this,ClientList.class).putParcelableArrayListExtra("clients",buy_un)
                            .putExtra("title",buy_un.size()+"个从未购买过的客户"));
                }
                break;
        }
    }
}
