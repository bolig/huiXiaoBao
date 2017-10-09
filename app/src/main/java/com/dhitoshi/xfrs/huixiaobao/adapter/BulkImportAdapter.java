package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import com.dhitoshi.xfrs.huixiaobao.Bean.ClientBean;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckBoxBulkClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/12.
 */
public class BulkImportAdapter extends BaseAdapter<ClientBean> {
    private Context context;
    private CheckBoxBulkClick click;
    public BulkImportAdapter(List<ClientBean> mList, Context context) {
        super(mList, context, R.layout.bulkimport_item, 4);
        this.context=context;
    }

    @Override
    public void covert(BaseRecyclerHolder holder, final List<ClientBean> mList, final int position) {
        final ClientBean item=mList.get(position);
        holder.setText(R.id.bulkimport_name,item.getName());
        holder.setText(R.id.bulkimport_idcard,item.getIdcard());
        CheckBox checkBox=holder.getView(R.id.bulkimport_select);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                click.check(isChecked,mList.get(position).getName(),mList.get(position).getIdcard());
            }
        });
    }
    public void addCheckBoxClick(CheckBoxBulkClick click){
        this.click=click;
    }
}
