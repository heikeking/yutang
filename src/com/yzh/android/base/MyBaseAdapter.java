package com.yzh.android.base;

import java.util.ArrayList;
import java.util.List;

import com.yzh.android.util.ViewFindUtils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
	private Context c;
	private int listcellId;
	private List<T> list = new ArrayList<T>();

	/**
	 * 
	 * @param c
	 *            activity.this
	 * @param resId
	 *            item的id
	 * @param list
	 *            数据list对象
	 */
	public MyBaseAdapter(Context c, int resId, List<T> list) {
		this.c = c;
		this.listcellId = resId;
		this.list = list;
	}

	public Context getContext() {
		return c;
	}

	public void add(T item) {
		list.add(item);
		notifyDataSetChanged();
	}

	public void remove(int position) {
		list.remove(position);
		notifyDataSetChanged();
	}

	public void removeLast(int position) {
		remove(getCount() - 1);
	}

	@Override
	public int getCount() {
		if (list.size()==0) {
			return 0;
		}else{
			return list.size();
		}
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(listcellId,
					null);
		}
//			convertView.setTag(viewHolder);
//		} else {
//			viewHolder = (ViewHolder) convertView.getTag();
//		}

		initListCell(position, convertView, parent);
		
		return convertView;
	}

	protected abstract void initListCell(int position, View convertView,
			ViewGroup parent);

/*	public class ViewHolder {
		public ImageView picture;
		public TextView name;
	}
*/
	// 跳转界面，单击事件实现
	public class COrder implements OnClickListener {
		public int position;
		public Class<?> tocontext;

		public COrder(int p, Class<?> tocontext) {
			position = p;
			this.tocontext = tocontext;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(c, tocontext);
			c.startActivity(intent);
		}

	}

	// 更新数据
	class RemoveListener implements OnClickListener {
		public int position;

		public RemoveListener(int p) {
			position = p;
		}

		@Override
		public void onClick(View v) {
			remove(position);
		}

	}
}
