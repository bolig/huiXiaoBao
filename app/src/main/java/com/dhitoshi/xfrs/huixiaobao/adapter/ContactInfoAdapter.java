package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import com.dhitoshi.xfrs.huixiaobao.Bean.ChatContact;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import java.util.List;
/**
 * Created by dxs on 2017/9/12.
 */
public class ContactInfoAdapter extends BaseAdapter<ChatContact> {
    private Context context;
    public ContactInfoAdapter(List<ChatContact> mList, Context context) {
        super(mList, context, R.layout.contactinfo_item, 4);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<ChatContact> mList, int position) {
        if(mList.get(position).getNick().equals("")){
            holder.setText(R.id.contactInfo_name,mList.get(position).getUserid());
        }else{
            holder.setText(R.id.contactInfo_name,mList.get(position).getNick());
        }
        holder.setImageResource(R.id.contactInfo_head,mList.get(position).getIcon_url());
    }
}
