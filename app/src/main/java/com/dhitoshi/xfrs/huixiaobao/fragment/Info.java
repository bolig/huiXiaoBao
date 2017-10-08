package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Event.InfoEvent;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.GlideCircleTransform;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//信息
public class Info extends Fragment {
    @BindView(R.id.info_head)
    ImageView infoHead;
    @BindView(R.id.info_name)
    TextView infoName;
    @BindView(R.id.info_birthday)
    TextView infoBirthday;
    @BindView(R.id.info_phone)
    TextView infoPhone;
    @BindView(R.id.info_hobby)
    TextView infoHobby;
    @BindView(R.id.info_vip)
    TextView infoVip;
    @BindView(R.id.info_area)
    TextView infoArea;
    @BindView(R.id.info_telPhone)
    TextView infoTelPhone;
    @BindView(R.id.info_email)
    TextView infoEmail;
    @BindView(R.id.info_position)
    TextView infoPosition;
    @BindView(R.id.info_address)
    TextView infoAddress;
    @BindView(R.id.info_company)
    TextView infoCompany;
    @BindView(R.id.info_companyPhone)
    TextView infoCompanyPhone;
    @BindView(R.id.info_companyAddress)
    TextView infoCompanyAddress;
    @BindView(R.id.info_entryMan)
    TextView infoEntryMan;
    @BindView(R.id.info_ill)
    TextView infoIll;
    @BindView(R.id.info_notes)
    TextView infoNotes;
    Unbinder unbinder;
    private static final String ARG_Client = "client";
    @BindView(R.id.info_sex)
    TextView infoSex;
    @BindView(R.id.info_idcard)
    TextView infoIdcard;
    private ClientBean clientBean;
    private String hobby = "";
    private String ill = "";

    public Info() {
    }

    public static Info newInstance(ClientBean clientBean) {
        Info fragment = new Info();
        Bundle args = new Bundle();
        args.putParcelable(ARG_Client, clientBean);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            clientBean = getArguments().getParcelable(ARG_Client);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        EventBus.getDefault().register(this);
        return view;
    }

    private void initViews() {
        infoName.setText(clientBean.getName());
        Glide.with(this).load(clientBean.getHead()).placeholder(R.mipmap.head).error(R.mipmap.head)
                .transform(new GlideCircleTransform(getContext())).into(infoHead);
        infoSex.setText(clientBean.getSex());
        infoBirthday.setText(clientBean.getBirthday());
        infoPhone.setText(clientBean.getPhone());
        infoIdcard.setText(clientBean.getIdcard());
        int hobbySize = clientBean.getHobby().size();
        for (int i = 0; i < hobbySize; i++) {
            hobby += clientBean.getHobby().get(i).getHobbyname() + " ";
        }
        infoHobby.setText(hobby);
        infoVip.setText(clientBean.getVip_id());
        infoArea.setText(clientBean.getArea());
        infoTelPhone.setText(clientBean.getTelephone());
        infoEmail.setText(clientBean.getEmail());
        infoPosition.setText(clientBean.getPosition());
        infoAddress.setText(clientBean.getAddress());
        infoCompany.setText(clientBean.getCompany());
        infoCompanyAddress.setText(clientBean.getCompany_address());
        infoCompanyPhone.setText(clientBean.getCompany_phone());
        infoEntryMan.setText(clientBean.getEntryman());
        int illSize = clientBean.getIll().size();
        for (int i = 0; i < illSize; i++) {
            ill += clientBean.getIll().get(i).getIllname() + " ";
        }
        infoIll.setText(ill);
        infoNotes.setText(clientBean.getNotes());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.info_send, R.id.info_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.info_send:
                break;
            case R.id.info_call:
                Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + clientBean.getPhone()));
                getContext().startActivity(phoneIntent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(InfoEvent event) {
        switch (event.getState()) {
            case 1:
                clientBean = event.getClientBean();
                initViews();
                break;
        }
    }
}
