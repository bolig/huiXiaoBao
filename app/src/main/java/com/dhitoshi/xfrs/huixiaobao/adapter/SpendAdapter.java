package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.SpendBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import com.dhitoshi.xfrs.huixiaobao.utils.DensityUtil;

import java.util.List;
/**
 * Created by dxs on 2017/9/11.
 */
public class SpendAdapter extends BaseAdapter<SpendBean> {
    public SpendAdapter(List<SpendBean> mList, Context context) {
        super(mList, context, R.layout.spend_item, 10);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<SpendBean> mList, int position) {
        SpendBean item=mList.get(position);
        holder.setText(R.id.client_info_day,item.getCreatetime());
        holder.setText(R.id.client_info_time,item.getCreatetime());
        holder.setText(R.id.spend_name,item.getItem_name());
        holder.setText(R.id.spend_date,item.getCreatetime());
        holder.setText(R.id.spend_price,item.getCost());
        holder.setText(R.id.spend_number,item.getNumber());
        holder.setText(R.id.spend_discount,item.getDiscount());
        holder.setText(R.id.spend_money,item.getAc_receive());
        holder.setText(R.id.spend_client,item.getCustomer_name());
        holder.setText(R.id.spend_company,item.getCustomer_company());
        if(position==mList.size()-1){
            holder.itemView.setPadding(0,0,0, DensityUtil.dp2px(10));
        }
    }
}
