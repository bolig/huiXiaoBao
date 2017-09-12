package com.dhitoshi.xfrs.huixiaobao.common;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
/**
 * Created by dxs on 2017/9/12.
 */
public class SelectDialog extends Dialog {
    public SelectDialog(@NonNull Context context) {
        super(context);
    }
    public SelectDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
}
