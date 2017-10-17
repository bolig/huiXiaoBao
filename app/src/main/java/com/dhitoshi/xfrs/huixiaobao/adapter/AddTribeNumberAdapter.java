package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.dhitoshi.xfrs.huixiaobao.Bean.ChatContact;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxBulkClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

import static com.github.abel533.echarts.code.Trigger.item;
import static com.github.abel533.echarts.code.TriggerOn.click;

/**
 * Created by dxs on 2017/9/12.
 */
public class AddTribeNumberAdapter extends BaseAdapter<ChatContact> {
    private Context context;
    private CheckBoxBulkClick click;
    public AddTribeNumberAdapter(List<ChatContact> mList, Context context) {
        super(mList, context, R.layout.tribenumber_item, 4);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<ChatContact> mList, final int position) {
        final ChatContact item=mList.get(position);
        if(item.getNick().equals("")){
            holder.setText(R.id.tribe_name,mList.get(position).getUserid());
        }else{
            holder.setText(R.id.tribe_name,mList.get(position).getNick());
        }
        holder.setImageResource(R.id.tribe_head,item.getIcon_url());
        ImageView iv=holder.getView(R.id.tribe_select);
        if(item.isSelected()){
            iv.setImageResource(R.mipmap.select);
        }else{
            iv.setImageResource(R.mipmap.unselect);
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.check(item.isSelected(),item.getNick(),item.getUserid(),position);
            }
        });
    }
    public void addCheckBoxClick(CheckBoxBulkClick click){
        this.click=click;
    }
}
