package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.Bean.AttendBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.HttpBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AttendInfoManage;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.presenter.AttendInfoPresenter;

public class AttendInfo extends BaseView implements AttendInfoManage.View{
    private AttendInfoPresenter attendInfoPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attend_info);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("考勤数据");
        attendInfoPresenter=new AttendInfoPresenter(this,this);
    }

    @Override
    public void GetaAttendList(HttpBean<AttendBean<Integer>> httpBean) {

    }
}
