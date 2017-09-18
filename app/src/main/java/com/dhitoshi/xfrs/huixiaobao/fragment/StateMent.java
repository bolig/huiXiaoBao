package com.dhitoshi.xfrs.huixiaobao.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
//报表页面
public class StateMent extends Fragment {
    @BindView(R.id.title)
    TextView title;
    Unbinder unbinder;
    public StateMent() {
    }
    public static StateMent newInstance() {
        StateMent fragment = new StateMent();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_state_ment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }
    private void initViews() {
        title.setText("报表");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.increment, R.id.clientType, R.id.member, R.id.sales, R.id.leaderboard, R.id.products,
            R.id.visit_number, R.id.visit_type, R.id.visit_man, R.id.plan_number, R.id.plan_man, R.id.plan_state})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.increment:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.clientType:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.member:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sales:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.leaderboard:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.products:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.visit_number:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.visit_type:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.visit_man:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.plan_number:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.plan_man:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.plan_state:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
