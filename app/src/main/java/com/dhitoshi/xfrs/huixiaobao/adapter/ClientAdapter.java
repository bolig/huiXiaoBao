package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/12.
 */
public class ClientAdapter extends BaseAdapter<ClientBean> {
    public ClientAdapter(List<ClientBean> mList, Context context) {
        super(mList, context, R.layout.client_item, 4);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<ClientBean> mList, int position) {
            ClientBean item=mList.get(position);
            holder.setText(R.id.client_item_name,item.getName());
            holder.setText(R.id.client_item_phone,item.getPhone());
           // holder.setText(R.id.client_name,item.getName());
    }
}
