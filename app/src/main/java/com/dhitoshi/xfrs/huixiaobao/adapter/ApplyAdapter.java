package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.Bean.ApplyMeetBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;

/**
 * Created by dxs on 2017/9/24.
 */
public class ApplyAdapter extends BaseAdapter<ApplyMeetBean> {
    private Context context;
    public ApplyAdapter(List<ApplyMeetBean> mList, Context context) {
        super(mList, context, R.layout.apply_item, 10);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<ApplyMeetBean> mList, int position) {
        holder.setImageResource(R.id.apply_image,mList.get(position).getImg(),R.mipmap.banner);
        holder.setText(R.id.apply_name,mList.get(position).getName());
        holder.setText(R.id.apply_time,mList.get(position).getStarttime());
        holder.setText(R.id.apply_end,mList.get(position).getEndtime());
    }
}
