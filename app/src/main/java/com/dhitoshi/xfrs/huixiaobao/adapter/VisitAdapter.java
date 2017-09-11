package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.VisitBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;

/**
 * Created by dxs on 2017/9/11.
 */

public class VisitAdapter extends BaseAdapter<VisitBean> {
    public VisitAdapter(List<VisitBean> mList, Context context) {
        super(mList, context, R.layout.visit_item, 9);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<VisitBean> mList, int position) {
        VisitBean item=mList.get(position);
        holder.setText(R.id.client_info_day,item.getCreatetime());
        holder.setText(R.id.client_info_time,item.getCreatetime());
        holder.setText(R.id.visit_client_name,item.getCustomer_name());
        holder.setText(R.id.visit_type,item.getFeedtype());
        holder.setText(R.id.visit_content,item.getFeedbody());
        holder.setText(R.id.visit_next,item.getNexttime());
        holder.setText(R.id.visit_name,item.getFeedman_name());
        holder.setText(R.id.visit_date,item.getCreatetime());
    }
}
