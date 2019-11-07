package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.Bean.CustomerTypeBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/10.
 */
public class TypeAdapter extends BaseAdapter<CustomerTypeBean> {
    private String selected="";
    public void setSelected(String selected) {
        this.selected = selected;
    }
    public TypeAdapter(List<CustomerTypeBean> mList, Context context) {
        super(mList, context, R.layout.screen_item, 1);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<CustomerTypeBean> mList, int position) {
        TextView tv=holder.getView(R.id.item_tv);
        holder.setText(R.id.item_tv,mList.get(position).getName());
        if(mList.get(position).getName().equals(selected)){
           tv.setTextColor(Color.parseColor("#34B1FF"));
           tv.setBackgroundResource(R.color.gray_d);
        }
    }
}
