package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import com.dhitoshi.xfrs.huixiaobao.view.AddProduct;

import java.util.List;
/**
 * Created by dxs on 2017/9/16.
 */
public class SetProductAdapter extends BaseAdapter<ProductBean>{
    private Context context;
    private DeleteCallback deleteCallback;
    public SetProductAdapter(List<ProductBean> mList, Context context) {
        super(mList, context, R.layout.product_item, 2);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<ProductBean> mList, final int position) {
        holder.setText(R.id.product_name,mList.get(position).getName());
        holder.setText(R.id.product_type,mList.get(position).getType_name());
        holder.getView(R.id.product_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AddProduct.class).putExtra("product",mList.get(position)));
            }
        });
        holder.getView(R.id.product_delete).setOnClickListener(new View.OnClickListener() {
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
