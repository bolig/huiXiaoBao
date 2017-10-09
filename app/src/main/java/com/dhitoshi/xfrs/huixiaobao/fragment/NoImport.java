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

public class NoImport extends Fragment {

    private static final String ARG_FAIL = "fail";
    @BindView(R.id.import_recyclerView)
    RecyclerView importRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.empty)
    RelativeLayout empty;
    private ArrayList<EnterResultBean> Fail;


    public NoImport() {

    }

    public static NoImport newInstance(ArrayList<EnterResultBean> Fail) {
        NoImport fragment = new NoImport();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_FAIL, Fail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Fail = getArguments().getParcelableArrayList(ARG_FAIL);
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
        empty.setVisibility(Fail.size() == 0 ? View.VISIBLE : View.GONE);
        ImportAdapter adapter = new ImportAdapter(Fail, getContext(), 0);
        importRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
