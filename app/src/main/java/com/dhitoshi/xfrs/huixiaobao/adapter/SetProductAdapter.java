package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/16.
 */
public class SetProductAdapter extends BaseAdapter<ProductBean>{
    public SetProductAdapter(List<ProductBean> mList, Context context) {
        super(mList, context, R.layout.product_item, 2);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<ProductBean> mList, int position) {
        holder.setText(R.id.product_name,mList.get(position).getName());
        holder.setText(R.id.product_type,mList.get(position).getType_name());
    }
}
