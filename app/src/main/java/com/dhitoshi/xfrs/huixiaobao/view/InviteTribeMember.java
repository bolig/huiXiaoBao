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
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.ChatContact;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
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
import static com.dhitoshi.xfrs.huixiaobao.app.MyApplication.getContext;

public class InviteTribeMember extends BaseView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private YWIMKit mIMKit;
    private IYWTribeService mTribeService;
    private long mTribeId;
    private List<ChatContact> chatContacts;
    private AddTribeNumberAdapter adapter;
    private  List<IYWContact> list;
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
                GetUsers(token);
            }
        });
        list=new ArrayList<>();
    }
    private void GetUsers(String token){
        final MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().GetUsers(token),new CommonObserver(new HttpResult<HttpBean<List<ChatContact>>>() {
            @Override
            public void OnSuccess(HttpBean<List<ChatContact>> httpBean) {
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    chatContacts=httpBean.getData();
                    adapter=new AddTribeNumberAdapter(chatContacts,getContext());
                    recyclerView.setAdapter(adapter);
                    adapter.addItemClickListener(new ItemClick<ChatContact>() {
                        @Override
                        public void onItemClick(View view, ChatContact chatContact, int position) {
                            if(chatContact.isSelected()){
                                remove(chatContact.getNick().isEmpty()?chatContact.getUserid():chatContact.getNick());
                            }else{
                                String showName=chatContact.getNick().isEmpty()?chatContact.getUserid():chatContact.getNick();
                                ComparableContact contact=new ComparableContact(showName,chatContact.getUserid(),"","24607089");
                                list.add(contact);
                            }
                            chatContacts.get(position).setSelected(!chatContact.isSelected());
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
                            chatContacts.get(position).setSelected(!isChecked);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }else if(httpBean.getStatus().getCode()==600){
                    LoginUtil.autoLogin(getContext(), new LoginCall() {
                        @Override
                        public void autoLogin(String token) {
                            GetUsers(token);
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
                    IMNotificationUtils.getInstance().showToast(InviteTribeMember.this, "添加群成员失败，code = " + code + ", info = " + info);
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
