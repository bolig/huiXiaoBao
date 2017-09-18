package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import com.dhitoshi.xfrs.huixiaobao.view.AddProductType;

import java.util.List;
/**
 * Created by dxs on 2017/9/16.
 */
public class SetTypeAdapter extends BaseAdapter<BaseBean>{
    private Context context;
    private DeleteCallback deleteCallback;
    public SetTypeAdapter(List<BaseBean> mList, Context context) {
        super(mList, context, R.layout.type_item, 1);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<BaseBean> mList, final int position) {
        holder.setText(R.id.type_name,mList.get(position).getName());
        holder.getView(R.id.type_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AddProductType.class).putExtra("type",mList.get(position)));
            }
        });
        holder.getView(R.id.type_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCallback.delete(mList.get(position).getId(),position);
            }
        });
    }
    public void addDeleteCallback(DeleteCallback deleteCallback) {
        this.deleteCallback = deleteCallback;
    }
}
