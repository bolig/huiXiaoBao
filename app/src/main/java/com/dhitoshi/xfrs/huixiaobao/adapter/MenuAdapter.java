package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.Bean.Menu;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/5.
 */
public class MenuAdapter extends BaseAdapter<Menu> {

    public MenuAdapter(List<Menu> mList, Context context, int layoutId, int contentLength) {
        super(mList, context, layoutId, contentLength);
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<Menu> mList, int position) {
        TextView tv=holder.getView(R.id.item_text);
        tv.setText(mList.get(position).getTitle());
        tv.setCompoundDrawables(mList.get(position).getDrawable(),null,null,null);
        if(position==mList.size()-1){
            holder.getView(R.id.item_view).setVisibility(View.GONE);
        }
    }

}
