package com.itteam.yutang.adapter;

import java.util.List;

import com.itteam.yutang.R;
import com.yzh.android.base.MyBaseAdapter;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DataCenterAdapter extends MyBaseAdapter<String> {
	private List<String> list;
	public DataCenterAdapter(Context mContext, int resId, List<String> list) {
		super(mContext, resId, list);
		this.list =list;
	}
	@Override
	protected void initListCell(int position, View convertView, ViewGroup parent) {
		ViewHolder holder =new ViewHolder();
		holder.name =(TextView) convertView.findViewById(R.id.datacenter_name);
		holder.img = (ImageView) convertView.findViewById(R.id.datacenter_img);
		holder.name.setText(list.get(position));
	}
	class ViewHolder{
		private TextView name;
		private ImageView img;
	}
}
