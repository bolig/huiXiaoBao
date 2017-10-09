package com.dhitoshi.xfrs.huixiaobao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dhitoshi.xfrs.huixiaobao.Bean.EnterResultBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.ImportAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.MyDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.alibaba.mobileim.channel.itf.mimsc.VideoMsg.FIELDS.size;

public class HasImport extends Fragment {
    private static final String ARG_SUCCESS = "success";
    @BindView(R.id.import_recyclerView)
    RecyclerView importRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.empty)
    RelativeLayout empty;
    private ArrayList<EnterResultBean> Success;

    public HasImport() {

    }

    public static HasImport newInstance(ArrayList<EnterResultBean> Success) {
        HasImport fragment = new HasImport();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_SUCCESS, Success);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Success = getArguments().getParcelableArrayList(ARG_SUCCESS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.has_import, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        importRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        importRecyclerView.addItemDecoration(new MyDecoration(getContext(), LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        empty.setVisibility(Success.size()==0?View.VISIBLE:View.GONE);
        ImportAdapter adapter = new ImportAdapter(Success, getContext(), 1);
        importRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
