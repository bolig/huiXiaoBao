package com.dhitoshi.xfrs.huixiaobao.common;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.R;

public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
    SparseArray<View> mView;
    private Context context;
    public BaseRecyclerHolder(View itemView, int itemLength, Context context) {
        super(itemView);
        this.context=context;
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
    public void setImageResource(int viewId,String url) {
        ImageView circleImageView=getView(viewId);
        Glide.with(context).load(url).placeholder(R.mipmap.head).error(R.mipmap.head).transform(new GlideCircleTransform(context)).into(circleImageView);
    }
    public void setImageResource(int viewId,String url,int resId) {
        ImageView circleImageView=getView(viewId);
        Glide.with(context).load(url).placeholder(resId).error(resId).into(circleImageView);
    }
}
