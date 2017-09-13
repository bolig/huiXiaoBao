package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhitoshi.refreshlayout.SmartRefreshLayout;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.PageBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.GiftManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.GiftAdapter;
import com.dhitoshi.xfrs.huixiaobao.presenter.GiftPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

//赠品
public class Gift extends BaseFragment implements GiftManage.View {
    private static final String ARG_ID = "id";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    Unbinder unbinder;
    private int id;

    public Gift() {

    }

    @Override
    public void loadData() {
        GiftPresenter giftPresenter = new GiftPresenter(this);
        giftPresenter.getGiftLists(String.valueOf(id), "1");
    }

    public static Gift newInstance(int id) {
        Gift fragment = new Gift();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void getGiftLists(PageBean<GiftBean> pageBean) {
        GiftAdapter adapter=new GiftAdapter(pageBean.getList(),getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
