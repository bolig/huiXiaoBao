package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.SexBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/14.
 */
public class SexAdapter extends BaseAdapter<SexBean>{
    private String sex;
    public SexAdapter(List mList, Context context,String sex) {
        super(mList, context, R.layout.select_item, 1);
        this.sex=sex;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<SexBean> mList, int position) {
        holder.setText(R.id.select_item_text,mList.get(position).getName());
        if(sex.equals(mList.get(position).getName())){
            holder.getView(R.id.select_item_text).setBackgroundResource(R.color.gray_d);
        }
    }

}
