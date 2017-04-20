package com.itteam.yutang.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.R;
import com.itteam.yutang.bean.EquipmentWarningBean;
import com.itteam.yutang.bean.MsgBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.bean.Response_main;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.base.MyBaseAdapter;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WarningAdapter extends MyBaseAdapter<EquipmentWarningBean> {
	private List<EquipmentWarningBean> list;
	private Context mContext;
	private boolean ischeck = false;
	private HashMap<Integer, Boolean> ispress;

	public WarningAdapter(Context mContext, int resId, List<EquipmentWarningBean> list) {
		super(mContext, resId, list);
		this.list = list;
		this.mContext = mContext;
		ispress = new HashMap<Integer, Boolean>();
	}

	@Override
	protected void initListCell(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder = new ViewHolder();
		ispress.put(position, false);
		holder.msg = (TextView) convertView.findViewById(R.id.name);
		holder.createTime = (TextView) convertView.findViewById(R.id.createtime);
		holder.change = (Button) convertView.findViewById(R.id.change);
		holder.msg.setText(list.get(position).pondName+":"+list.get(position).msg);
		holder.createTime.setText(list.get(position).createTime);
		if (list.get(position).ack_time == null) {
			holder.change.setText("未确认");
			ischeck = false;
		} else {
			holder.change.setText("已确认");
			ischeck = true;
		}
		holder.change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.isSelected() == false) {
					ToolAlert.loading(getContext(), "提交中", false);
					v.setSelected(!v.isSelected());
					ischeck = !ischeck;
					ispress.put(position, !ischeck);
					holder.change.setText("已确认");
					EquipmentErrorConfirm(list.get(position).alarmId,holder.change);
				} 
//				else if (v.isSelected() == true) {
//					v.setSelected(!v.isSelected());
//					ischeck = !ischeck;
//					ispress.put(position, !ischeck);
//					holder.change.setText("未确认");
//					EquipmentErrorConfirm(list.get(position).alarmId,holder.change);
//				}
			}
		});
	}
	public void EquipmentErrorConfirm(String alarmId,final Button change) {
		Map<String, String> map = new HashMap<>();
		map.put("alarmId", alarmId);
		ToolHTTP.get(mContext, Contants.equipmentErrorConfirm, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(),
						new TypeToken<Response<MsgBean>>() {
						}.getType());
				if (resultBean.code==1) {
					
				}else if(resultBean.code==2){
					if (ischeck==true) {
						ischeck = !ischeck;
						change.setText("未确认");
					}else{
						ischeck = !ischeck;
						change.setText("确认");
					}
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
			}

			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}
	class ViewHolder {
		private TextView msg;
		private TextView createTime;
		private Button change;
	}
}
