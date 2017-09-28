package com.dhitoshi.xfrs.huixiaobao.common;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AreaCallback;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.MyDismiss;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.adapter.AreaAdapter;
import com.dhitoshi.xfrs.huixiaobao.adapter.KidAdapter;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by dxs on 2017/9/5.
 */
public class PopupArea {
    private View parent;
    private Context context;
    private  PopupWindow popupWindow;
    private  RecyclerView one;
    private  RecyclerView two;
    private MyDismiss dismiss;
    private AreaAdapter areaAdapter;
    private KidAdapter kidAdapter;
    private List<KidBean> kidBeens;
    private AreaCallback areaCallback;
    private TextView ok;
    private String id;
    private String name;
    public PopupArea(Context context, View view) {
        this.parent = view;
        this.context=context;
        kidBeens=new ArrayList<>();
    }
    public  static PopupArea Build(Context context, View view){
        return new PopupArea(context,view);
    }
    public PopupArea init(List<AreaBean> areas){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.screen_area, null);
        ok=(TextView) view.findViewById(R.id.ok);
        one=(RecyclerView) view.findViewById(R.id.screen_one);
        two=(RecyclerView) view.findViewById(R.id.screen_two);
        one.addItemDecoration(new MyDecoration(context, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        one.setLayoutManager(new LinearLayoutManager(context));
        two.addItemDecoration(new MyDecoration(context, LinearLayoutManager.HORIZONTAL, R.drawable.divider_line));
        two.setLayoutManager(new LinearLayoutManager(context));
        areaAdapter=new AreaAdapter(areas,context);
        one.setAdapter(areaAdapter);
        if(areas.size()>0){
            kidBeens.addAll(areas.get(0).getKid());
            kidAdapter=new KidAdapter(kidBeens,context);
            two.setAdapter(kidAdapter);
        }
        View shade=view.findViewById(R.id.shade);
        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setAnimationStyle(R.style.anim_style);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x40000000));
        shade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaCallback.getArea(id,name);
                popupWindow.dismiss();
            }
        });
        areaAdapter.addItemClickListener(new ItemClick<AreaBean>() {
            @Override
            public void onItemClick(View view, AreaBean areaBean, int position) {
                areaAdapter.setSelected(areaBean.getName());
                areaAdapter.notifyDataSetChanged();
                kidBeens.removeAll(kidBeens);
                kidBeens.addAll(areaBean.getKid());
                kidAdapter.notifyDataSetChanged();
                id=String.valueOf(areaBean.getId());
                name=areaBean.getName();
            }
        });
        kidAdapter.addItemClickListener(new ItemClick<KidBean>() {
            @Override
            public void onItemClick(View view, KidBean kidBean, int position) {
                kidAdapter.setSelected(kidBean.getName());
                kidAdapter.notifyDataSetChanged();
                areaCallback.getArea(String.valueOf(kidBean.getId()),kidBean.getName());
                popupWindow.dismiss();
            }
        });
        return this;
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
            popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0,parent.getHeight()+location[1]);
        }
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismiss.dismiss();
            }
        });
    }
    public  void dismisss(){
        if(null!=popupWindow){
            popupWindow.dismiss();
        }
    }
    public boolean isShowing(){
        return popupWindow.isShowing();
    }
    public void addAreaClick(AreaCallback areaCallback){
        this.areaCallback=areaCallback;
    }
}
