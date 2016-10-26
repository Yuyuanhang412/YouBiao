package com.bwie.youbiao.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.youbiao.R;
import com.bwie.youbiao.bean.BeanData;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MyListAdapter extends BaseAdapter {
    private Context context;
    private List<BeanData.DataEntity.ForumListEntity.GroupEntity> list;

    public MyListAdapter(Context context, List<BeanData.DataEntity.ForumListEntity.GroupEntity> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_item, null);
            holder = new Holder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.tv_name);
            holder.textView2 = (TextView) convertView.findViewById(R.id.tv_total);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView1.setText(list.get(position).getName());
        holder.textView2.setText(list.get(position).getDescription());
        return convertView;
    }

    class Holder {
        TextView textView1, textView2;
    }
}
