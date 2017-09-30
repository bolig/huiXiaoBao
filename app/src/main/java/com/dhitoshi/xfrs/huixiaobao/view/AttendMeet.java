package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.CustomerTypeBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetClientManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AttendAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.CommonAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.presenter.MeetClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.dhitoshi.xfrs.huixiaobao.R.id.clientType;

public class AttendMeet extends BaseView implements MeetClientManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.error)
    RelativeLayout error;
    @BindView(R.id.title)
    TextView title;
    private MeetClientPresenter meetClientPresenter;
    private String meetingid = "";
    private int page = 1;
    private Map<String, String> map;
    private List<MeetClientBean> meetClientBeens;
    private AttendAdapter adapter;
    private int days = 0;
    private String start;
    private List<BaseBean> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend_meet);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        initBaseViews();
        Calendar mCalendar = Calendar.getInstance();
        Date date = new Date();
        mCalendar.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM--dd");
        String time = sdf.format(date);
        int apm = mCalendar.get(Calendar.AM_PM);
        setTitleIcon(time + (apm == 0 ? " 上午" : " 下午"));
        meetingid = getIntent().getStringExtra("id");
        days = getIntent().getIntExtra("days", 0);
        start = getIntent().getStringExtra("start");
        initDates();
        meetClientPresenter = new MeetClientPresenter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        smartRefreshLayout.autoRefresh();
        map = new HashMap<>();
        meetClientBeens = new ArrayList<>();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                meetClientBeens.removeAll(meetClientBeens);
                page = 1;
                map.put("meetingid", meetingid);
                map.put("token", SharedPreferencesUtil.Obtain(AttendMeet.this, "token", "").toString());
                map.put("page", String.valueOf(page));
                meetClientPresenter.getCustomerList(map, smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                map.put("meetingid", meetingid);
                map.put("token", SharedPreferencesUtil.Obtain(AttendMeet.this, "token", "").toString());
                map.put("page", String.valueOf(page));
                meetClientPresenter.getCustomerList(map, smartRefreshLayout);
            }
        });
    }

    private void initDates() {
        dates = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(start));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < days; i++) {
            String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + i);// 获取当前日份的日期号码
            BaseBean forenoon = new BaseBean(i, mYear + "-" + mMonth + "-" + mDay + " 上午");
            BaseBean afternoon = new BaseBean(i + 1, mYear + "-" + mMonth + "-" + mDay + " 下午");
            dates.add(forenoon);
            dates.add(afternoon);
        }
    }

    @Override
    public void getCustomerList(HttpBean<PageBean<MeetClientBean>> httpBean) {
        meetClientBeens.addAll(httpBean.getData().getList());
        int size = meetClientBeens.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size == 0 ? View.VISIBLE : View.GONE);
        if (null == adapter) {
            adapter = new AttendAdapter(meetClientBeens, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.title)
    public void onViewClicked() {
        CommonAdapter adapter = new CommonAdapter(dates, this, title.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择日期").setAdapter(adapter).show();
        adapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                title.setText(baseBean.getName());
//                type = String.valueOf(customerTypeBean.getId());
//                clientType.setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.dismiss();
            }
        });
    }
}
