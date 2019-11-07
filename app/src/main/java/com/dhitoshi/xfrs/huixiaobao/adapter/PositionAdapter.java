package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.PositionBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.SexBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by dxs on 2017/9/14.
 */
public class PositionAdapter extends BaseAdapter<PositionBean>{
    private String workPosition;
    public PositionAdapter(List mList, Context context,String position) {
        super(mList, context, R.layout.select_item, 1);
        this.workPosition=position;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<PositionBean> mList, int position) {
        holder.setText(R.id.select_item_text,mList.get(position).getName());
        if(workPosition.equals(mList.get(position).getName())){
            holder.getView(R.id.select_item_text).setBackgroundResource(R.color.gray_d);
        }
    }

}
