package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.AreaBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/17.
 */

public class CompanyAdapter extends BaseAdapter<AreaBean> {
    private Context context;
    public CompanyAdapter(List<AreaBean> mList, Context context) {
        super(mList, context,  R.layout.company_item, 3);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<AreaBean> mList, final int position) {
        holder.setText(R.id.company_name,mList.get(position).getName());
    }

}
