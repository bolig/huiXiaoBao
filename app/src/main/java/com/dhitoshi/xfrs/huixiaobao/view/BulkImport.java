package com.dhitoshi.xfrs.huixiaobao.view;
import android.os.Bundle;
import com.dhitoshi.xfrs.huixiaobao.R;
public class BulkImport extends BaseView {
    private String meetingid="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulk_import);
        initViews();
    }
    private void initViews() {
        initBaseViews();
        setTitle("批量添加人员");
        setRightText("提交");
        meetingid=getIntent().getStringExtra("id");
    }
}
