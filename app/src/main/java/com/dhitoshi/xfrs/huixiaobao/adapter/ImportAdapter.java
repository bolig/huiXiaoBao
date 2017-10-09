package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.Bean.EnterResultBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/10/9.
 */

public class ImportAdapter extends BaseAdapter<EnterResultBean> {
    private int type;
    public ImportAdapter(List<EnterResultBean> mList, Context context,int type) {
        super(mList, context, R.layout.import_result_item, 4);
        this.type=type;
    }

    @Override
    public void covert(BaseRecyclerHolder holder, List<EnterResultBean> mList, int position) {
        EnterResultBean item=mList.get(position);
        TextView Name=holder.getView(R.id.result_name);
        TextView Idcard=holder.getView(R.id.result_idcard);
        TextView reason=holder.getView(R.id.result_reason);
        Name.setText(item.getData().getName());
        Idcard.setText(item.getData().getIdcard());
        if(type==0){
            Name.setTextColor(Color.parseColor("#ff0000"));
            Idcard.setTextColor(Color.parseColor("#ff0000"));
            reason.setVisibility(View.VISIBLE);
            reason.setText(item.getMsg());
        }else{
            reason.setVisibility(View.GONE);
        }
    }
}
