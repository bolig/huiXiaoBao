package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.UserRole;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/17.
 */
public class PermissionAdapter extends BaseAdapter<UserRole>{

    public PermissionAdapter(List<UserRole> mList, Context context) {
        super(mList, context, R.layout.permission_item, 5);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<UserRole> mList, int position) {
        UserRole item=mList.get(position);
        holder.setText(R.id.permission_position,item.getName());
        holder.setText(R.id.permission_area,item.getArea());
        holder.setText(R.id.permission_notes,item.getNotes());
    }
}
