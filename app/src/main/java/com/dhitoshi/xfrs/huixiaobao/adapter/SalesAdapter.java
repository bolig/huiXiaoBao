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
public class SalesAdapter extends BaseAdapter<SpendBean> {
    public SalesAdapter(List<SpendBean> mList, Context context) {
        super(mList, context, R.layout.sales_item, 10);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<SpendBean> mList, int position) {
        SpendBean item=mList.get(position);
        holder.setText(R.id.sales_item,item.getItem_name());
        holder.setText(R.id.sales_time,item.getCreatetime());
        holder.setText(R.id.sales_man,item.getSaleman_name());
        holder.setText(R.id.sales_info,"单价:￥"+item.getCost()+",数量:"+item.getNumber()+",折扣:"+item.getDiscount()+"%");
        if((item.getCost()!=null&&!item.getCost().isEmpty())&&(item.getDiscount()!=null&&!item.getDiscount().isEmpty())
                &&(item.getNumber()!=null&&!item.getNumber().isEmpty())){
            float price=Float.parseFloat(item.getCost());
            int number=Integer.parseInt(item.getNumber());
            float dis=Float.parseFloat(item.getDiscount());
            float total=price*number*(1-dis/100);
            holder.setText(R.id.sales_total,"合计:￥"+total);
        }
    }
}
