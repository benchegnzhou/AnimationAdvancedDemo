package com.ztsc.house.huanxin.friends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ztsc.house.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 许阳 on 2017/2/27.
 */

public class FriendListAdapter extends BaseAdapter {
    private List<String> list = new ArrayList<>();
    private Context context;

    public FriendListAdapter(Context context, List<String> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.friend_list_item, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(holder);
        }else
        {
            holder=(ViewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(i));

        return convertView;
    }

    public  class ViewHolder{
        TextView textView;
    }
}
