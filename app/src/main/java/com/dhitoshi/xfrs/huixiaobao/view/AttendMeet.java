package com.dhitoshi.xfrs.huixiaobao.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AttendClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.Interface.MeetClientManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AttendAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.CommonAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.SelectDialog;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.presenter.MeetClientPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
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
    private List<MeetClientBean> meetClientBeens;
    private AttendAdapter adapter;
    private int days = 0;
    private String start;
    private List<BaseBean> dates;
    private CommonAdapter commonAdapter;
    private int current=-1;
    private Map<String,String> map;
    private String attend="";
    private String currentAttend="";
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
        int day=c.get(Calendar.DAY_OF_MONTH);
        for (int i = 0; i < days; i++) {
            c.set(Calendar.DAY_OF_MONTH,day + i);
            String mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
            String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
            if(c.get(Calendar.MONTH) +1<10){
                mMonth="0"+mMonth;
            }
            String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前日份的日期号码
            if(c.get(Calendar.DAY_OF_MONTH)<10){
                mDay="0"+mDay;
            }
            BaseBean forenoon = new BaseBean(i*2, mYear + "-" + mMonth + "-" + mDay + " 上午");
            if(forenoon.getName().equals(title.getText().toString())){
                current=forenoon.getId();
            }
            BaseBean afternoon = new BaseBean(i*2 + 1, mYear + "-" + mMonth + "-" + mDay + " 下午");
            if(afternoon.getName().equals(title.getText().toString())){
                current=afternoon.getId();
            }
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
            adapter = new AttendAdapter(meetClientBeens, this,current);
            recyclerView.setAdapter(adapter);
            adapter.addAtendClick(new AttendClick() {
                @Override
                public void check(View view, MeetClientBean meetClientBean, int position) {
                    Map<String,String>  map=new HashMap<>();
                    map.put("id",String.valueOf(meetClientBean.getId()));
                    map.put("token",SharedPreferencesUtil.Obtain(AttendMeet.this,"token","").toString());
                    if(meetClientBean.getAttend().get(position).equals("1")){
                        currentAttend="0";
                    }else{
                        currentAttend="1";
                    }
                    int size=meetClientBean.getAttend().size();
                    for (int i = 0; i < size; i++) {
                        if(attend.isEmpty()){
                            if(position==i){
                                attend+=currentAttend;
                            }else{
                                attend+=meetClientBean.getAttend().get(i);
                            }
                        }else{
                            if(position==i){
                                attend+=","+currentAttend;
                            }else{
                                attend+=","+meetClientBean.getAttend().get(i);
                            }
                        }
                    }
                    Log.e("TAG","attend------>>>>"+attend);
                    map.put("attend",attend);
                    //attend(map,view);
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }
    }
    private void attend(final Map<String,String> map, final View view){
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().attend(map),new CommonObserver(new HttpResult<HttpBean<Object>>() {
            @Override
            public void OnSuccess(HttpBean<Object> httpBean) {
                if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(AttendMeet.this, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            attend(map,view);
                        }
                    });
                }else if(httpBean.getStatus().getCode()==200){
                    Toast.makeText(AttendMeet.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                    ((ImageView)view).setImageResource(currentAttend.equals("1")?R.mipmap.select:R.mipmap.unselect);
                }else{
                    Toast.makeText(AttendMeet.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                Toast.makeText(AttendMeet.this,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @OnClick(R.id.title)
    public void onViewClicked() {
        commonAdapter = new CommonAdapter(dates, this, title.getText().toString());
        final SelectDialog dialog = new SelectDialog(this);
        dialog.setTitle("选择日期").setAdapter(commonAdapter).show();
        commonAdapter.addItemClickListener(new ItemClick<BaseBean>() {
            @Override
            public void onItemClick(View view, BaseBean baseBean, int position) {
                title.setText(baseBean.getName());
                current=position;
                adapter.setCurrent(current);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
    }
}
