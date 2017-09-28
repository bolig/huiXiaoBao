package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;

import java.util.List;

/**
 * Created by dxs on 2017/9/12.
 */
public class UserAdapter extends BaseAdapter<UserBean> {
    private Context context;
    public UserAdapter(List<UserBean> mList, Context context) {
        super(mList, context, R.layout.client_item, 4);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<UserBean> mList, int position) {
            final UserBean item=mList.get(position);
            holder.setText(R.id.client_item_name,item.getName());
            holder.setText(R.id.client_item_phone,item.getPhone());
            holder.getView(R.id.client_item_call).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + item.getPhone()));
                    context.startActivity(phoneIntent);
                }
            });
        holder.setImageResource(R.id.client_head,item.getHead());
    }
}
