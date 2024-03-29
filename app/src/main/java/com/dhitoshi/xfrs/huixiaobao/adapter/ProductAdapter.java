package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.dhitoshi.xfrs.huixiaobao.Bean.BaseBean;
import com.dhitoshi.xfrs.huixiaobao.Bean.ProductBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;

/**
 * Created by dxs on 2017/9/15.
 */

public class ProductAdapter extends BaseAdapter<ProductBean>{
    private CheckBoxClick click;
    private String selected;
    public ProductAdapter(List<ProductBean> mList, Context context, String selected) {
        super(mList, context, R.layout.multiple_select, 2);
        this.selected=selected;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<ProductBean> mList, final int position) {
        holder.setText(R.id.multiple_text,mList.get(position).getName());
        CheckBox checkBox=holder.getView(R.id.multiple_select);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.check(true,mList.get(position).getName(),mList.get(position).getId());
            }
        });
        if(selected.equals(mList.get(position).getName())){
            checkBox.setChecked(true);
        }
    }
    public void addCheckBoxClick( CheckBoxClick click){
        this.click=click;
    }
}
