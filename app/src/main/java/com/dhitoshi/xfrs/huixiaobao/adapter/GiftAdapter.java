package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.GiftBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;

/**
 * Created by dxs on 2017/9/11.
 */

public class GiftAdapter extends BaseAdapter<GiftBean> {
    public GiftAdapter(List<GiftBean> mList, Context context) {
        super(mList, context, R.layout.gift_item, 6);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<GiftBean> mList, int position) {
        GiftBean item=mList.get(position);
        holder.setText(R.id.client_info_day,item.getCreatetime());
        holder.setText(R.id.client_info_time,item.getCreatetime());
        holder.setText(R.id.gift_name,item.getGift());
        holder.setText(R.id.gift_location,item.getSaleaddress());
        holder.setText(R.id.gift_man,item.getSalesman());
        holder.setText(R.id.gift_date,item.getCreatetime());
    }
}
