package com.dhitoshi.xfrs.huixiaobao.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.NewVersion;

/**
 * Created by dxs on 2017/6/12.
 */
public class UpdateDialog extends Dialog implements View.OnClickListener{
    private TextView updateContent;
    private TextView updateRefuse;
    private TextView updateAgree;
    private LinearLayout updateDeal;
    private ImageView updateClose;
    private TextView updateTitle;
    private Context context;
    private String title;
    private String url;
    private String content;
    private String versionName;
    public UpdateDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    public UpdateDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.context=context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update);
        setCanceledOnTouchOutside(false);
        initView();
    }
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
    public void setContent(String content){
        this.content=content;
    }
    public void setUrl(String url){
        this.url=url;
    }
    public void setUpdateTitle(String title){this.title=title;}
    private void initView() {
        updateTitle=(TextView)findViewById(R.id.update_title);
        updateContent=(TextView)findViewById(R.id.update_content);
        updateAgree=(TextView)findViewById(R.id.update_agree);
        updateRefuse=(TextView)findViewById(R.id.update_refuse);
        updateClose=(ImageView)findViewById(R.id.update_close);
        updateDeal=(LinearLayout)findViewById(R.id.update_deal);
        updateClose.setOnClickListener(this);
        updateAgree.setOnClickListener(this);
        updateRefuse.setOnClickListener(this);
        updateContent.setText(content);
        updateTitle.setText(title);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_refuse:
                dismiss();
                break;
            case R.id.update_agree:
                downloadApk();
                break;
            case R.id.update_close:
                dismiss();
                break;
            default:
                break;
        }
    }
    private void downloadApk() {
        new NewVersion(context).downLoadNewApk(url,versionName);
        dismiss();
    }
}
