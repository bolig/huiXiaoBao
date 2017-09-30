package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
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
    private CheckBoxClick click;
    public AttendAdapter(List<MeetClientBean> mList, Context context) {
        super(mList, context, R.layout.attend_item, 4);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<MeetClientBean> mList, final int position) {
        final MeetClientBean item=mList.get(position);
        holder.setText(R.id.attend_name,item.getName());
        holder.setText(R.id.attend_phone,item.getPhone());
        CheckBox checkBox=holder.getView(R.id.attend);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.check(true,mList.get(position).getName(),mList.get(position).getId());
            }
        });
//        if(selected.equals(mList.get(position).getName())){
//            checkBox.setChecked(true);
//        }else {
//            checkBox.setChecked(false);
//        }
    }
    public void addCheckBoxClick( CheckBoxClick click){
        this.click=click;
    }
}
