package com.dhitoshi.xfrs.huixiaobao.common;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.dhitoshi.xfrs.huixiaobao.Interface.MyDismiss;
import com.dhitoshi.xfrs.huixiaobao.R;

import static com.github.abel533.echarts.code.AxisType.log;

/**
 * Created by dxs on 2017/9/5.
 */
public class PopupScreen {
    private View parent;
    private Context context;
    private  PopupWindow popupWindow;
    private  RecyclerView recyclerView;
    private MyDismiss dismiss;
    public PopupScreen(Context context, View view) {
        this.parent = view;
        this.context=context;
    }
    public  static PopupScreen Build( Context context, View view){
        return new PopupScreen(context,view);
    }
    public PopupScreen init(){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.screen, null);
        recyclerView=(RecyclerView) view.findViewById(R.id.screen_recyclerView);
        View shade=view.findViewById(R.id.shade);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.addItemDecoration(new MyDecoration(context, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        recyclerView.setLayoutManager(layoutManager);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        shade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismiss.dismiss();
            }
        });
        return this;
    }
    public void setResource(BaseAdapter adapter){
        recyclerView.setAdapter(adapter);
    }
    public void addDismiss(MyDismiss dismiss){
        this.dismiss=dismiss;
    }
    public void show(){
        int []location=new int[2];
        parent.getLocationOnScreen(location);
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.showAsDropDown(parent, 0, 0);
        } else {
            if(Build.DISPLAY.contains("Flyme")){
                popupWindow.showAsDropDown(parent, 0, 0);
            }else{
                popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0,parent.getHeight()+location[1]);
            }
        }
    }
    public  void dismisss(){
        if(null!=popupWindow){
            popupWindow.dismiss();
        }
    }
    public boolean isShowing(){
        return popupWindow.isShowing();
    }
}
