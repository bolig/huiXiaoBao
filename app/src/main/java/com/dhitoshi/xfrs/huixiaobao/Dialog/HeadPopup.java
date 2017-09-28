package com.dhitoshi.xfrs.huixiaobao.Dialog;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.R;
/**
 * Created by dxs on 2017/7/24.
 */
public class HeadPopup implements View.OnTouchListener,View.OnClickListener{
    private Context context;
    private View parent;
    private PopupWindow popupWindow;
    private WindowManager.LayoutParams lp;
    private TextView camera;//相机
    private TextView album;//相册
    private TextView cancel;//取消
    public HeadPopup(Context context,View view) {
        this.context = context;
        this.parent=view;
    }
    public  static HeadPopup Build(Context context, View view){
        return new HeadPopup(context,view);
    }
    public HeadPopup init() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.head_popup, null);
        camera=(TextView) view.findViewById(R.id.head_camera);
        camera.setOnClickListener(this);
        camera.setOnTouchListener(this);
        album=(TextView) view.findViewById(R.id.head_album);
        album.setOnClickListener(this);
        album.setOnTouchListener(this);
        cancel=(TextView) view.findViewById(R.id.head_cancel);
        cancel.setOnClickListener(this);
        cancel.setOnTouchListener(this);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.anim_bottom);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                ((Activity)context).getWindow().setAttributes(lp);
                ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });
        return this;
    }
    public void show(){
        lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = 0.7f;
        ((Activity)context).getWindow().setAttributes(lp);
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        popupWindow.showAtLocation(parent, Gravity.LEFT | Gravity.BOTTOM, 0, 0);
    }
    public  void dismisss(){
        if(null!=popupWindow){
            popupWindow.dismiss();
        }
    }
    public boolean isShowing(){
        return popupWindow.isShowing();
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                v.setAlpha(0.5f);
                break;
            case MotionEvent.ACTION_UP:
                v.setAlpha(1.0f);
                break;
        }
        return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head_camera:

                break;
            case R.id.head_album:
                break;
            case R.id.head_cancel:
                dismisss();
                break;
        }
    }
}
