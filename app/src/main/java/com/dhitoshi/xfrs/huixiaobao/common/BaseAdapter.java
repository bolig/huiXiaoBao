package com.dhitoshi.xfrs.huixiaobao.common;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dhitoshi.xfrs.huixiaobao.Interface.ItemClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import java.util.List;
/**
 * Created by dxs on 2017/8/21.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseRecyclerHolder> {

    private List<T> mList;
    private Context context;
    private int layoutId;
    private int contentLength;
    //点击事件
    private ItemClick<T> itemClick;
    public BaseAdapter(List<T> mList, Context context, int layoutId, int contentLength) {
        this.mList = mList;
        this.context = context;
        this.layoutId = layoutId;
        this.contentLength = contentLength;
    }
    @Override
    public BaseRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
//        if(mList.size()==0){
//            view= LayoutInflater.from(context).inflate(R.layout.empty,parent,false);
//        }else{
            view= LayoutInflater.from(context).inflate(layoutId,parent,false);
       // }
        return BaseRecyclerHolder.getBaseRecyclerHolder(view,contentLength,context);
    }
    @Override
    public void onBindViewHolder(BaseRecyclerHolder holder, final int position) {
        covert(holder,mList,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClick!=null){
                    itemClick.onItemClick(view,mList.get(position),position);
                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
    public void addItemClickListener(ItemClick<T> itemClick) {
        this.itemClick = itemClick;
    }
    public  abstract  void covert(BaseRecyclerHolder holder,List<T> mList, int position);
}
