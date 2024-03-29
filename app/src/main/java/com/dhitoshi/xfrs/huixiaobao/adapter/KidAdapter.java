package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by dxs on 2017/9/17.
 */

public class KidAdapter extends BaseAdapter<KidBean> {
    private String selected="";
    public KidAdapter(List<KidBean> mList, Context context) {
        super(mList, context,  R.layout.screen_item, 1);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<KidBean> mList, int position) {
        TextView tv=holder.getView(R.id.item_tv);
        holder.setText(R.id.item_tv,mList.get(position).getName());
        if(mList.get(position).getName().equals(selected)){
            tv.setTextColor(Color.parseColor("#34B1FF"));
            tv.setBackgroundResource(R.color.gray_d);
        }else{
            tv.setTextColor(Color.parseColor("#666666"));
            tv.setBackgroundResource(android.R.color.white);
        }
    }
    public void setSelected(String selected) {
        this.selected = selected;
    }
}
