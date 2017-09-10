package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.CustomerTypeBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/10.
 */
public class TypeAdapter extends BaseAdapter<CustomerTypeBean> {

    public TypeAdapter(List<CustomerTypeBean> mList, Context context, int layoutId) {
        super(mList, context, layoutId, 1);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<CustomerTypeBean> mList, int position) {
        holder.setText(R.id.item_tv,mList.get(position).getName());
    }
}
