package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by dxs on 2017/9/14.
 */
public class DialogAdapter extends BaseAdapter<BaseBean> {
    private String location;
    public DialogAdapter(List<BaseBean> mList, Context context, String location) {
        super(mList, context, R.layout.select_item, 1);
        this.location=location;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<BaseBean> mList, int position) {
        holder.setText(R.id.select_item_text,mList.get(position).getName());
        if(location.equals(mList.get(position).getName())){
            holder.getView(R.id.select_item_text).setBackgroundResource(R.color.gray_d);
        }
    }
}
