package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;

import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.KidBeanX;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by dxs on 2017/9/17.
 */

public class KidAdapter extends BaseAdapter<KidBean> {
    public KidAdapter(List<KidBean> mList, Context context) {
        super(mList, context,  R.layout.screen_item, 1);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<KidBean> mList, int position) {
        holder.setText(R.id.item_tv,mList.get(position).getName());
    }
}
