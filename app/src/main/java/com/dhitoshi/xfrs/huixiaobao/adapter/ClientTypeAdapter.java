package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.CustomerTypeBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;

/**
 * Created by dxs on 2017/9/14.
 */
public class ClientTypeAdapter extends BaseAdapter<CustomerTypeBean>{
    private String type;
    public ClientTypeAdapter(List mList, Context context,String type) {
        super(mList, context, R.layout.select_item, 1);
        this.type=type;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<CustomerTypeBean> mList, int position) {
      holder.setText(R.id.select_item_text,mList.get(position).getName());
        if(type.equals(mList.get(position).getName())){
            holder.getView(R.id.select_item_text).setBackgroundResource(R.color.gray_d);
        }
    }

}
