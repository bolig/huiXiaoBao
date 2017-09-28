package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import com.dhitoshi.xfrs.huixiaobao.utils.DensityUtil;
import com.dhitoshi.xfrs.huixiaobao.view.AddPermission;
import com.dhitoshi.xfrs.huixiaobao.view.AddProduct;

import java.util.List;
/**
 * Created by dxs on 2017/9/17.
 */
public class PermissionAdapter extends BaseAdapter<UserRole>{
    private DeleteCallback deleteCallback;
    private Context context;
    public PermissionAdapter(List<UserRole> mList, Context context) {
        super(mList, context, R.layout.permission_item, 5);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<UserRole> mList, final int position) {
        UserRole item=mList.get(position);
        holder.setText(R.id.permission_position,item.getName());
        holder.setText(R.id.permission_area,item.getArea());
        holder.setText(R.id.permission_notes,item.getNotes());
        holder.getView(R.id.permission_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AddPermission.class).putExtra("permission",mList.get(position)));
            }
        });
        holder.getView(R.id.permission_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCallback.delete(mList.get(position).getId(),position);
            }
        });
        if(position==mList.size()-1){
            RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.setMargins( DensityUtil.dp2px(20), DensityUtil.dp2px(5), DensityUtil.dp2px(20), DensityUtil.dp2px(10));
            holder.itemView.setLayoutParams(params);
        }
    }
    public void addDeleteCallback(DeleteCallback deleteCallback) {
        this.deleteCallback = deleteCallback;
    }
}
