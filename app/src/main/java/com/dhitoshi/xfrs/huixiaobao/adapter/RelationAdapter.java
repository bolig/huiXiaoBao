package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.RelationBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/11.
 */
public class RelationAdapter extends BaseAdapter<RelationBean> {
    public RelationAdapter(List<RelationBean> mList, Context context) {
        super(mList, context, R.layout.relation_item, 6);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<RelationBean> mList, int position) {
        RelationBean item=mList.get(position);
        holder.setText(R.id.client_info_day,item.getCreatetime());
        holder.setText(R.id.client_info_time,item.getCreatetime());
        holder.setText(R.id.relation_name,item.getName());
        holder.setText(R.id.relation_relation,item.getRelation());
        holder.setText(R.id.relation_phone,item.getPhone());
        holder.setText(R.id.relation_email,item.getEmail());
    }
}
