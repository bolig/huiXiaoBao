package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.AttendClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/12.
 */
public class AttendAdapter extends BaseAdapter<MeetClientBean> {
    private Context context;
    private AttendClick click;
    private int current=-1;
    public AttendAdapter(List<MeetClientBean> mList, Context context,int current) {
        super(mList, context, R.layout.attend_item, 4);
        this.context=context;
        this.current=current;
    }
    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public void covert(BaseRecyclerHolder holder, final List<MeetClientBean> mList, final int position) {
        final MeetClientBean item=mList.get(position);
        holder.setText(R.id.attend_name,item.getName());
        holder.setText(R.id.attend_phone,item.getPhone());
        ImageView iv=holder.getView(R.id.attend);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.check(v,mList.get(position),position);
            }
        });
        if(mList.get(position).getAttend().get(current).equals("1")){
            iv.setImageResource(R.mipmap.select);
        }else {
            iv.setImageResource(R.mipmap.unselect);
        }
    }
    public void addAtendClick( AttendClick click){
        this.click=click;
    }
}
