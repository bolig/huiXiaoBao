package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.refreshlayout.api.RefreshLayout;
import com.dhitoshi.refreshlayout.listener.OnRefreshListener;
import com.dhitoshi.xfrs.huixiaobao.Bean.ChatContact;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.LoginCall;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ContactInfoAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.CommonObserver;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;
import com.dhitoshi.xfrs.huixiaobao.http.HttpResult;
import com.dhitoshi.xfrs.huixiaobao.http.MyHttp;
import com.dhitoshi.xfrs.huixiaobao.utils.LoginUtil;
import com.dhitoshi.xfrs.huixiaobao.utils.SharedPreferencesUtil;
import com.dhitoshi.xfrs.huixiaobao.view.Chat;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ContactInfo extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    private ContactInfoAdapter adapter;
    public ContactInfo() {
    }
    @Override
    public void loadData() {
        smartRefreshLayout.autoRefresh();
    }
    public static ContactInfo newInstance() {
        ContactInfo fragment = new ContactInfo();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }
    private void initViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new MyDecoration(getContext(), LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        smartRefreshLayout.setEnableLoadmore(false);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                String token= SharedPreferencesUtil.Obtain(getContext(),"token","").toString();
                GetUsers(token);
            }
        });
    }
    private void GetUsers(String token){
        MyHttp http=MyHttp.getInstance();
        http.send(http.getHttpService().GetUsers(token),new CommonObserver(new HttpResult<HttpBean<List<ChatContact>>>() {
            @Override
            public void OnSuccess(HttpBean<List<ChatContact>> httpBean) {
                smartRefreshLayout.finishRefresh();
                if(httpBean.getStatus().getCode()==200){
                    adapter=new ContactInfoAdapter(httpBean.getData(),getContext());
                    recyclerView.setAdapter(adapter);
                    adapter.addItemClickListener(new ItemClick<ChatContact>() {
                        @Override
                        public void onItemClick(View view, ChatContact chatContact, int position) {
                            Intent intent = new Intent(getContext(), Chat.class);
                            intent.putExtra("target",chatContact.getUserid());
                            intent.putExtra("name",chatContact.getNick());
                            startActivity(intent);
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
