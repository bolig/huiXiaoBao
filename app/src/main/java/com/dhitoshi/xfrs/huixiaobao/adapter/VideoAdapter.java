package com.dhitoshi.xfrs.huixiaobao.adapter;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dhitoshi.xfrs.huixiaobao.Bean.Format;
import com.dhitoshi.xfrs.huixiaobao.Bean.VideoBean;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.dhitoshi.xfrs.huixiaobao.common.BaseAdapter;
import com.dhitoshi.xfrs.huixiaobao.common.BaseRecyclerHolder;
import com.dhitoshi.xfrs.huixiaobao.utils.DateUtil;
import java.util.List;
/**
 * Created by dxs on 2017/11/12.
 */
public class VideoAdapter extends BaseAdapter<VideoBean> {
    private Context context;
    public VideoAdapter(List<VideoBean> mList, Context context) {
        super(mList, context, R.layout.video_item, 4);
        this.context=context;
    }
    @Override
    public void covert(BaseRecyclerHolder holder, List<VideoBean> mList, int position) {
        if(position==0){
            setMargin(holder);
        }
        VideoBean item=mList.get(position);
        holder.setText(R.id.video_title,item.getTitle()+"("+item.getNumber()+")");
        holder.setText(R.id.video_hot,"火热度:"+item.getHot());
        holder.setText(R.id.video_time, DateUtil.longToString(((long)item.getCreate_time())*1000, Format.YEAR_MONTH_DAY_CROSS));
        Glide.with(context).load(item.getUrl()).into((ImageView) holder.getView(R.id.video_img));
    }

}
