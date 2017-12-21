package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeMemberBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxBulkClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.InviteMemberManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AddTribeNumberAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.presenter.InviteMemberPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteTribeMember extends BaseView implements InviteMemberManage.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.empty)
    RelativeLayout empty;
    private YWIMKit mIMKit;
    private long mTribeId;
    private List<TribeMemberBean> tribeMembers;
    private AddTribeNumberAdapter adapter;
    private int page = 1;
    private String areaId;
    private InviteMemberPresenter presenter;
    private int type=1;//0-内部群 1-平台群
    private Map<String, String> map;
    private String uids="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_invite_tribe_member);
        ButterKnife.bind(this);
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeId = getIntent().getLongExtra(TribeConstants.TRIBE_ID, 0);
        areaId = getIntent().getStringExtra("area_id");
        if (TextUtils.isEmpty(areaId)) {
            type=0;
            areaId = SharedPreferencesUtil.Obtain(this, "areId", "").toString();
        }
        initTitle();
    }
    private void initTitle() {
        initBaseViews();
        setTitle("添加群成员");
        setRightText("完成");
        tribeMembers = new ArrayList<>();
        presenter=new InviteMemberPresenter(this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDecoration(this, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                tribeMembers.removeAll(tribeMembers);
                String token = SharedPreferencesUtil.Obtain(InviteTribeMember.this, "token", "").toString();
                page = 1;
                presenter.getUserList(token, areaId, String.valueOf(page),smartRefreshLayout);
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                String token = SharedPreferencesUtil.Obtain(InviteTribeMember.this, "token", "").toString();
                ++page;
                presenter.getUserList(token, areaId, String.valueOf(page),smartRefreshLayout);
            }
        });
    }
    @Override
    public void getUserList(HttpPageBeanTwo<TribeMemberBean> httpPageBeanTwo) {
        tribeMembers.addAll(httpPageBeanTwo.getData());
        int size = tribeMembers.size();
        if (size >= 10 && size % 10 == 0) {
            smartRefreshLayout.setEnableLoadmore(true);
        } else {
            smartRefreshLayout.setEnableLoadmore(false);
        }
        empty.setVisibility(size == 0 ? View.VISIBLE : View.GONE);
        if (null == adapter) {
            adapter = new AddTribeNumberAdapter(tribeMembers, InviteTribeMember.this);
            recyclerView.setAdapter(adapter);
            adapter.addItemClickListener(new ItemClick<TribeMemberBean>() {
                @Override
                public void onItemClick(View view, TribeMemberBean tribeMemberBean, int position) {
                    if (tribeMemberBean.isSelected()) {
                        uids=uids.replace(tribeMemberBean.getId()+",","") ;
                    } else {
                        uids+=tribeMemberBean.getId()+",";

                    }
                    tribeMembers.get(position).setSelected(!tribeMemberBean.isSelected());
                    adapter.notifyDataSetChanged();
                }
            });
            adapter.addCheckBoxClick(new CheckBoxBulkClick() {
                @Override
                public void check(boolean isChecked, String name, String idcard, int position) {
                    if (isChecked) {
                        uids=uids.replace(idcard+",","") ;
                    } else {
                        uids+=idcard+",";
                    }
                    tribeMembers.get(position).setSelected(!isChecked);
                    adapter.notifyDataSetChanged();
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

    }
    private void invite(){
        MyHttp http=MyHttp.getInstance();
        if(map==null){
            map=new HashMap<>();
        }
        map.put("tribe_id",String.valueOf(mTribeId));
        map.put("token",SharedPreferencesUtil.Obtain(this,"token","").toString());
        map.put("uids",uids.substring(0,uids.length()-1));
        http.send(http.getHttpService().invite(map),new CommonObserver(new HttpResult<HttpBean<Object>>() {
            @Override
            public void OnSuccess(HttpBean<Object> httpBean) {
                if(httpBean.getStatus().getCode()==200){
                    IMNotificationUtils.getInstance().showToast(InviteTribeMember.this, "添加群成员成功！");
                    if (type==1){
                        ActivityManagerUtil.destoryActivity("TribeCompany");
                    }
                    finish();
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(InviteTribeMember.this, new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            map.put("token",token);
                            invite();
                        }
                    });
                }
                else{
                    Toast.makeText(InviteTribeMember.this,httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                Toast.makeText(InviteTribeMember.this,msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    @OnClick(R.id.right_text)
    public void onViewClicked() {
        if(TextUtils.isEmpty(uids)){
            IMNotificationUtils.getInstance().showToast(InviteTribeMember.this, "请选择要添加的成员！");
            return;
        }
        invite();
    }
}
