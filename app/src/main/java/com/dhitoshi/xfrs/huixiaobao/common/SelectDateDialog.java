package com.dhitoshi.xfrs.huixiaobao.common;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.Interface.DateCallBack;
import com.dhitoshi.xfrs.huixiaobao.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dxs on 2017/9/12.
 */
public class SelectDateDialog extends Dialog {
    private TextView title;
    private TextView cancel;
    private TextView confirm;
    private DatePicker datePicker;
    private String s;
    private String time;
    private DateCallBack call;
    public SelectDateDialog(@NonNull Context context) {
        super(context,android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
    }
    public SelectDateDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context,android.R.style.Theme_Holo_Light_Dialog_NoActionBar);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date_select);
        initViews();
    }
    private void initViews() {
        title=(TextView)findViewById(R.id.dialog_title);
        title.setText(s);
        datePicker=(DatePicker) findViewById(R.id.date);
        if(!time.isEmpty()){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date=df.parse(time);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                datePicker.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cancel=(TextView)findViewById(R.id.dialog_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        confirm=(TextView)findViewById(R.id.dialog_ok);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                int mouth = datePicker.getMonth() + 1;
                String mou=mouth<10?"0"+mouth:String.valueOf(mouth);
                int day = datePicker.getDayOfMonth();
                String da=day<10?"0"+day:String.valueOf(day);
                call.getDate(datePicker.getYear() + "-" + mou +"-"+ da);
            }
        });
    }
    public SelectDateDialog getDate(DateCallBack call){
        this.call=call;
        return this;
    }
    public SelectDateDialog setTitle(String s){
        this.s=s;
        return this;
    }
    public SelectDateDialog setTime(String time){
        this.time=time;
        return this;
    }
}
