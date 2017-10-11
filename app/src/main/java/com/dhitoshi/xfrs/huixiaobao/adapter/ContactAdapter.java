package com.dhitoshi.xfrs.huixiaobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.dhitoshi.xfrs.huixiaobao.Bean.PhoneInfo;
import com.dhitoshi.xfrs.huixiaobao.Interface.CheckContactClick;
import com.dhitoshi.xfrs.huixiaobao.R;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<PhoneInfo> list;
    private CheckContactClick click;
    public ContactAdapter(Context context, List<PhoneInfo> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        final PhoneInfo phoneInfo= list.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_item, null);
            viewHolder.index = (TextView) convertView.findViewById(R.id.index);
            viewHolder.contactName = (TextView) convertView.findViewById(R.id.contact_name);
            viewHolder.contactPhone = (TextView) convertView.findViewById(R.id.contact_phone);
            viewHolder.contactCheck=(ImageView) convertView.findViewById(R.id.contact_check);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.contactName.setText(phoneInfo.getName());
        viewHolder.contactPhone.setText(phoneInfo.getNumber());
        if(phoneInfo.isSelect()){
            viewHolder.contactCheck.setImageResource(R.mipmap.select);
        }else{
            viewHolder.contactCheck.setImageResource(R.mipmap.unselect);
        }
        viewHolder.contactCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.check(phoneInfo.isSelect(),phoneInfo.getName(),phoneInfo.getNumber(),position);
            }
        });
        char index =getIndex(phoneInfo.getName());
        if(position == 0 || getIndex(list.get(position - 1).getName()) != index){
            viewHolder.index.setVisibility(View.VISIBLE);
            viewHolder.index.setText(String.valueOf(index));
        }else{
            viewHolder.index.setVisibility(View.GONE);
        }
        return convertView;
    }

    class ViewHolder {
        TextView index;
        TextView contactName;
        TextView contactPhone;
        ImageView contactCheck;
    }
    public void addCheckBoxClick(CheckContactClick click){
        this.click=click;
    }
    public static final char getIndex(String src){
        String res = src;
        try{
            res = PinyinHelper.convertToPinyinString(src, "", PinyinFormat.WITHOUT_TONE).toUpperCase();
        }catch(Exception e){
        }
        if(res.charAt(0) >= 'A' && res.charAt(0) <= 'Z'){
            return res.charAt(0);
        }else{
            return '#';
        }
    }
}
