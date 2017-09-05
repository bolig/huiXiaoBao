package com.dhitoshi.xfrs.huixiaobao.common;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
    SparseArray<View> mView;
    private int itemLength=0;
    private Context context;
    private  View item;
    public BaseRecyclerHolder(View itemView, int itemLength, Context context) {
        super(itemView);
        this.item=itemView;
        this.context=context;
        this.itemLength=itemLength;
        mView=new SparseArray<>(itemLength);
    }
    public static BaseRecyclerHolder getBaseRecyclerHolder(View itemView, int itemLength, Context context) {
        return new BaseRecyclerHolder(itemView,itemLength,context);
    }
    //通过view的id获取对应的控件，如果没有则加入views中
    public <T extends View> T getView(int viewId) {
        View view = mView.get(viewId);
        if (view == null ) {
            view = itemView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return (T) view;
    }
    public void setText(int viewId,String text) {
        TextView tv=getView(viewId);
        tv.setText(text);
    }
}
