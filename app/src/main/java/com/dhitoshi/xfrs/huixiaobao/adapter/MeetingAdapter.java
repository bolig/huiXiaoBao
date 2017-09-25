package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import com.dhitoshi.xfrs.huixiaobao.utils.DensityUtil;

import java.util.List;
/**
 * Created by dxs on 2017/9/11.
 */
public class MeetingAdapter extends BaseAdapter<MeetBean> {
    public MeetingAdapter(List<MeetBean> mList, Context context) {
        super(mList, context, R.layout.meeting_item, 6);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<MeetBean> mList, int position) {
        MeetBean item=mList.get(position);
        holder.setText(R.id.client_info_day,item.getCreatetime());
        holder.setText(R.id.client_info_time,item.getCreatetime());
        holder.setText(R.id.meet_date,item.getCreatetime());
        holder.setText(R.id.meet_type,item.getType());
        holder.setText(R.id.meet_man,item.getSalesman());
        holder.setText(R.id.meet_client_type,item.getUsertype());
        if(position==mList.size()-1){
            holder.itemView.setPadding(0,0,0, DensityUtil.dp2px(10));
        }
    }
}
