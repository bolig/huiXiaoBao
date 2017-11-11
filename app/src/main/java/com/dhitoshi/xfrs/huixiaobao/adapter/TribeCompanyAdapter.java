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
public class TribeCompanyAdapter extends BaseAdapter<BaseBean> {

    public TribeCompanyAdapter(List<BaseBean> mList, Context context) {
        super(mList, context,  R.layout.company_item, 3);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<BaseBean> mList, final int position) {
        holder.setText(R.id.company_name,mList.get(position).getName());
    }

}
