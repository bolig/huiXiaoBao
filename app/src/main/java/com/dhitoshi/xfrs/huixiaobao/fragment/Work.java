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

//work
public class Work extends Fragment {


    @BindView(R.id.title)
    TextView title;
    Unbinder unbinder;

    public Work() {

    }

    public static Work newInstance() {
        Work fragment = new Work();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        title.setText("工作");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.work_banner, R.id.work_examine, R.id.work_leave, R.id.work_sign, R.id.work_meet,
            R.id.work_target, R.id.work_performance, R.id.work_plan, R.id.work_visit, R.id.work_attendance,
            R.id.leave,R.id.examine, R.id.work_bbs, R.id.work_knowledge, R.id.work_news})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.work_banner:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_examine:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_leave:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_sign:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_meet:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_target:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_performance:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_plan:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_visit:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_attendance:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.leave:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.examine:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_bbs:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_knowledge:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
            case R.id.work_news:
                Toast.makeText(getContext(),"开发中，敬请期待",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
