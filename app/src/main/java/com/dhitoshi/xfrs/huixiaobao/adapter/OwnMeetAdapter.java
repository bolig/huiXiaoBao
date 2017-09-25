package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.OwnMeetBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by dxs on 2017/9/24.
 */
public class OwnMeetAdapter extends BaseAdapter<OwnMeetBean> {
    public OwnMeetAdapter(List<OwnMeetBean> mList, Context context) {
        super(mList, context, R.layout.apply_item, 10);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<OwnMeetBean> mList, int position) {
        holder.setText(R.id.apply_name,mList.get(position).getType().getName());
        holder.setText(R.id.apply_time,mList.get(position).getStarttime()+"至"+mList.get(position).getEndtime());
        holder.setText(R.id.apply_end,mList.get(position).getEndtime());
    }
}
