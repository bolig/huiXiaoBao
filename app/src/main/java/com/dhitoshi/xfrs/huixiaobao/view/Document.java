package com.dhitoshi.xfrs.huixiaobao.view;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.dhitoshi.xfrs.huixiaobao.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Document extends BaseView {
    private Intent it;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        initBaseViews();
        setTitle("会议文档视频");
        id=getIntent().getIntExtra("id",0);
    }
    @OnClick({R.id.document_video, R.id.document_graphic, R.id.document_audio})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.document_video:
                it=new Intent(this,MeetingVideo.class).putExtra("id",id);
                startActivity(it);
                break;
            case R.id.document_graphic:
                it=new Intent(this, LoadWeb.class).putExtra("url","http://119.29.144.125/hxb/public/houtai/user/tuwenlist.html").putExtra("title","图文列表");
                startActivity(it);
                break;
            case R.id.document_audio:
                it=new Intent(this, LoadWeb.class).putExtra("url","http://119.29.144.125:4000/audio").putExtra("title","audio");
                startActivity(it);
                break;
        }
    }
}
