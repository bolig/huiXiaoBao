package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.DeleteCallback;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import com.dhitoshi.xfrs.huixiaobao.view.AddArea;
import com.dhitoshi.xfrs.huixiaobao.view.AddAreaTwo;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by dxs on 2017/9/17.
 */

public class AddAreaOneAdapter extends BaseAdapter<AreaBean> {
    private Context context;
    private DeleteCallback deleteCallback;
    public AddAreaOneAdapter(List<AreaBean> mList, Context context) {
        super(mList, context,  R.layout.area_item, 3);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<AreaBean> mList, final int position) {
        holder.setText(R.id.area_name,mList.get(position).getName());
        holder.getView(R.id.are_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,AddAreaTwo.class)
                        .putExtra("title",mList.get(position).getName())
                        .putExtra("parent_id",mList.get(position).getId())
                        .putExtra("kid",(ArrayList)mList.get(position).getKid()));
            }
        });
        holder.getView(R.id.area_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AddArea.class)
                        .putExtra("parent_id",mList.get(position).getParent_id())
                        .putExtra("id",mList.get(position).getId())
                        .putExtra("type",-1)
                        .putExtra("name",mList.get(position).getName())
                        .putExtra("admin",mList.get(position).getAdmin())
                        .putExtra("phone",mList.get(position).getPhone())
                        .putExtra("address",mList.get(position).getAddress())
                        .putExtra("notes",mList.get(position).getNotes())
                        .putExtra("is_employee",mList.get(position).getIs_employee())
                        .putExtra("if_repeat",mList.get(position).getIf_repeat()));
            }
        });
        holder.getView(R.id.area_delete).setOnClickListener(new View.OnClickListener() {
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
