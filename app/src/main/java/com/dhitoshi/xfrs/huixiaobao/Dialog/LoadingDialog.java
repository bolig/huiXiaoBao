package com.dhitoshi.xfrs.huixiaobao.Dialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.R;
/**
 * Created by dxs on 2017/4/26.
 */
public class LoadingDialog extends Dialog{
    private TextView loadingTitle;
    private String title;
    public LoadingDialog(Context context) {
        this(context, R.style.loading_dialog);
    }
    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    public static LoadingDialog build(Context context) {
        return new LoadingDialog(context, R.style.loading_dialog);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        loadingTitle=(TextView) findViewById(R.id.loading_title);
        setCanceledOnTouchOutside(false);
        loadingTitle.setText(title);
    }
    public LoadingDialog setLoadingTitle(String title){
        this.title=title;
        return this;
    }
}
