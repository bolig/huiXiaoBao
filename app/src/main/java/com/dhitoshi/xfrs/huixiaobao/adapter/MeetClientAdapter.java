package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.dhitoshi.xfrs.huixiaobao.Bean.MeetClientBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import com.dhitoshi.xfrs.huixiaobao.view.AddMeetClient;
import java.util.List;
/**
 * Created by dxs on 2017/9/12.
 */
public class MeetClientAdapter extends BaseAdapter<MeetClientBean> {
    private Context context;
    public MeetClientAdapter(List<MeetClientBean> mList, Context context) {
        super(mList, context, R.layout.enter_item, 4);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, final List<MeetClientBean> mList, final int position) {
        final MeetClientBean item=mList.get(position);
        holder.setText(R.id.enter_name,item.getName());
        holder.setText(R.id.enter_phone,item.getIdcard());
        holder.getView(R.id.enter_update).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, AddMeetClient.class)
                    .putExtra("meetclient",mList.get(position)));
           }
      });
    }
}
