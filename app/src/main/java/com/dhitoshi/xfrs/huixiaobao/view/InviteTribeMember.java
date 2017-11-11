package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
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
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpPageBeanTwo;
import com.dhitoshi.xfrs.huixiaobao.Bean.TribeMemberBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxBulkClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.InviteMemberManage;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AddTribeNumberAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.common.TribeConstants;
import com.dhitoshi.xfrs.huixiaobao.presenter.InviteMemberPresenter;
import com.dhitoshi.xfrs.huixiaobao.utils.ActivityManagerUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;
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
    private IYWTribeService mTribeService;
    private long mTribeId;
    private List<TribeMemberBean> tribeMembers;
    private AddTribeNumberAdapter adapter;
    private List<IYWContact> list;
    private int page = 1;
    private String areaId;
    private InviteMemberPresenter presenter;
    private int type=1;//0-内部群 1-平台群
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity_invite_tribe_member);
        ButterKnife.bind(this);
        String userId = SharedPreferencesUtil.Obtain(this, "account", "").toString().split("@")[0];
        mIMKit = YWAPI.getIMKitInstance(userId, "24607089");
        mTribeService = mIMKit.getTribeService();
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
        list = new ArrayList<>();
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
                        remove(tribeMemberBean.getTruename().isEmpty() ? tribeMemberBean.getName() : tribeMemberBean.getName());
                    } else {
                        String showName = tribeMemberBean.getTruename().isEmpty() ? tribeMemberBean.getName() : tribeMemberBean.getName();
                        ComparableContact contact = new ComparableContact(showName, tribeMemberBean.getName(), "", "24607089");
                        list.add(contact);
                    }
                    tribeMembers.get(position).setSelected(!tribeMemberBean.isSelected());
                    adapter.notifyDataSetChanged();
                }
            });
            adapter.addCheckBoxClick(new CheckBoxBulkClick() {
                @Override
                public void check(boolean isChecked, String name, String idcard, int position) {
                    if (isChecked) {
                        remove(name.isEmpty() ? idcard : name);
                    } else {
                        String showName = name.isEmpty() ? idcard : name;
                        ComparableContact contact = new ComparableContact(showName, idcard, "", "24607089");
                        list.add(contact);
                    }
                    tribeMembers.get(position).setSelected(!isChecked);
                    adapter.notifyDataSetChanged();
                }
            });
        } else {
            adapter.notifyDataSetChanged();
        }

    }
    private void remove(String name) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getShowName().equals(name)) {
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
                        if (type==1){
                            ActivityManagerUtil.destoryActivity("TribeCompany");
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
