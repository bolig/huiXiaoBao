package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by dxs on 2017/9/16.
 */
public class SetTypeAdapter extends BaseAdapter<BaseBean>{
    public SetTypeAdapter(List<BaseBean> mList, Context context) {
        super(mList, context, R.layout.type_item, 1);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<BaseBean> mList, int position) {
        holder.setText(R.id.type_name,mList.get(position).getName());
    }
}
