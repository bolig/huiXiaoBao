package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class AddMeeting extends BaseView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("新增会议记录");
        setRightText("提交");
    }
}
