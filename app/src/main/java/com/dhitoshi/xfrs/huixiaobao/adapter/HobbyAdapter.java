package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dhitoshi.xfrs.huixiaobao.Bean.HobbyBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxClick;
import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/14.
 */
public class HobbyAdapter extends BaseAdapter<HobbyBean> {

    private CheckBoxClick click;
    private String selected;
    public HobbyAdapter(List<HobbyBean> mList, Context context,String selected) {
        super(mList, context, R.layout.multiple_select, 2);
        this.selected=selected;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<HobbyBean> mList, final int position) {
        holder.setText(R.id.multiple_text,mList.get(position).getName());
        CheckBox checkBox=holder.getView(R.id.multiple_select);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                click.check(isChecked,mList.get(position).getName(),mList.get(position).getId());
            }
        });
        if(selected.indexOf(mList.get(position).getName())!=-1){
            checkBox.setChecked(true);
        }
    }
   public void addCheckBoxClick(CheckBoxClick click){
       this.click=click;
   }
}
