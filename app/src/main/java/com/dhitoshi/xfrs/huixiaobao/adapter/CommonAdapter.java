package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/17.
 */
public class CommonAdapter extends BaseAdapter<BaseBean>{
    private String selected;
    public CommonAdapter(List<BaseBean> mList, Context context,String selected) {
        super(mList, context, R.layout.screen_item, 1);
        this.selected=selected;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<BaseBean> mList, int position) {
        holder.setText(R.id.item_tv,mList.get(position).getName());
        if(selected.equals(mList.get(position).getName())){
            holder.getView(R.id.item_tv).setBackgroundResource(R.color.gray_d);
        }
    }
}
