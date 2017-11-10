package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.gingko.model.tribe.YWTribeType;
import com.alibaba.mobileim.tribe.IYWTribeService;
import com.alibaba.mobileim.ui.contact.component.ComparableContact;
import com.alibaba.mobileim.utility.IMNotificationUtils;
import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnLoadmoreListener;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.ChatContact;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeMemberBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxBulkClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AddTribeNumberAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.alibaba.mobileim.channel.itf.mimsc.VideoMsg.FIELDS.size;
import static com.dhitoshi.xfrs.huixiaobao.app.MyApplication.getContext;

public class InviteTribeMember extends BaseView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private YWIMKit mIMKit;
    private IYWTribeService mTribeService;
    private long mTribeId;
    private List<TribeMemberBean> tribeMembers;
    private AddTribeNumberAdapter adapter;
    private  List<IYWContact> list;
    private int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_invite_tribe_member);
        ButterKnife.bind(this);
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();
        mTribeId = getIntent().getLongExtra(TribeConstants.TRIBE_ID, 0);
        initTitle();
    }
    private void initTitle() {
        initBaseViews();
        setTitle("添加群成员");
        setRightText("完成");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new MyDecoration(getContext(), LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                String token = SharedPreferencesUtil.Obtain(getContext(), "token", "").toString();
                String area_id = SharedPreferencesUtil.Obtain(getContext(), "areId", "").toString();
                page=1;
                GetUsers(token,area_id,String.valueOf(page));
            }
        });
        smartRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                String token = SharedPreferencesUtil.Obtain(getContext(), "token", "").toString();
                String area_id = SharedPreferencesUtil.Obtain(getContext(), "areId", "").toString();
                ++page;
                GetUsers(token,area_id,String.valueOf(page));
            }
        });
        list=new ArrayList<>();
    }
    private void GetUsers(String token, final String area_id, final String page){
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().userList(token,area_id,page),new CommonObserver(new HttpResult<HttpPageBeanTwo<TribeMemberBean>>() {
            @Override
            public void OnSuccess(HttpPageBeanTwo<TribeMemberBean> httpBean) {
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    tribeMembers=httpBean.getData();
                    int size = tribeMembers.size();
                    if (size >= 10 && size % 10 == 0) {
                        smartRefreshLayout.setEnableLoadmore(true);
                    } else {
                        smartRefreshLayout.setEnableLoadmore(false);
                    }
                    adapter=new AddTribeNumberAdapter(tribeMembers,getContext());
                    recyclerView.setAdapter(adapter);
                    adapter.addItemClickListener(new ItemClick<TribeMemberBean>() {
                        @Override
                        public void onItemClick(View view, TribeMemberBean tribeMemberBean, int position) {
                            if(tribeMemberBean.isSelected()){
                                remove(tribeMemberBean.getTruename().isEmpty()?tribeMemberBean.getName():tribeMemberBean.getName());
                            }else{
                                String showName=tribeMemberBean.getTruename().isEmpty()?tribeMemberBean.getName():tribeMemberBean.getName();
                                ComparableContact contact=new ComparableContact(showName,tribeMemberBean.getName(),"","24607089");
                                list.add(contact);
                            }
                            tribeMembers.get(position).setSelected(!tribeMemberBean.isSelected());
                            adapter.notifyDataSetChanged();
                        }
                    });
                    adapter.addCheckBoxClick(new CheckBoxBulkClick() {
                        @Override
                        public void check(boolean isChecked, String name, String idcard, int position) {
                            if(isChecked){
                                remove(name.isEmpty()?idcard:name);
                            }else{
                                String showName=name.isEmpty()?idcard:name;
                                ComparableContact contact=new ComparableContact(showName,idcard,"","24607089");
                                list.add(contact);
                            }
                            tribeMembers.get(position).setSelected(!isChecked);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(getContext(), new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            GetUsers(token,area_id,page);
                        }
                    });
                }
                else{
                    Toast.makeText(getContext(),httpBean.getStatus().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void OnFail(String msg) {
                smartRefreshLayout.finishRefresh();
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        }));
    }
    private void remove(String name){
        int size=list.size();
        for (int i = 0; i < size; i++) {
            if(list.get(i).getShowName().equals(name)){
                list.remove(i);
            }
        }
    }
    private void add() {
        final YWTribeType tribeType = mTribeService.getTribe(mTribeId).getTribeType();
        if (list != null && list.size() > 0) {
            mTribeService.inviteMembers(mTribeId, list, new IWxCallback() {
                @Override
                public void onSuccess(Object... result) {
                    Integer retCode = (Integer) result[0];
                    if (retCode == 0) {
                        if (tribeType == YWTribeType.CHATTING_GROUP) {
                            IMNotificationUtils.getInstance().showToast(InviteTribeMember.this, "添加群成员成功！");
                        } else {
                            IMNotificationUtils.getInstance().showToast(InviteTribeMember.this, "群邀请发送成功！");
                        }
                        finish();
                    }
                }

                @Override
                public void onError(int code, String info) {
                    IMNotificationUtils.getInstance().showToast(InviteTribeMember.this, "添加群成员失败");
                }

                @Override
                public void onProgress(int progress) {

                }
            });
        }
    }
    @OnClick(R.id.right_text)
    public void onViewClicked() {
        add();
    }
}
