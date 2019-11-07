package com.dhitoshi.xfrs.huixiaobao.Interface;
import android.view.View;
/**
 * Created by Administrator on 2016/12/2.
 */
public interface ItemClick<T> {
    //点击事件接口回调
    void onItemClick(View view, T t, int position);

}
