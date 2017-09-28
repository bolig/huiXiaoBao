package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.QueryResultBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by dxs on 2017/9/12.
 */
public class QueryClientAdapter extends BaseAdapter<QueryResultBean<ClientBean>> {
    private Context context;
    public QueryClientAdapter(List<QueryResultBean<ClientBean>> mList, Context context) {
        super(mList, context, R.layout.client_item, 4);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<QueryResultBean<ClientBean>> mList, final int position) {

            holder.setText(R.id.client_item_name,mList.get(position).getName());
            holder.setText(R.id.client_item_phone,mList.get(position).getPhone());
            holder.getView(R.id.client_item_call).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + mList.get(position).getPhone()));
                    context.startActivity(phoneIntent);
                }
            });
         holder.setImageResource(R.id.client_head,mList.get(position).getHead());
    }
}
