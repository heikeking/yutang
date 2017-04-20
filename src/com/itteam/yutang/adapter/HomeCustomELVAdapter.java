package com.itteam.yutang.adapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.R;
import com.itteam.yutang.activity.DataCenterActivity;
import com.itteam.yutang.activity.WarningActivity;
import com.itteam.yutang.bean.DeviceListBean;
import com.itteam.yutang.bean.EngineBeaninfo;
import com.itteam.yutang.bean.EquipmentWarningBean;
import com.itteam.yutang.bean.Response_main;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.MyApplication;
import com.yzh.android.base.Operation;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;
import com.yzh.android.util.PreferencesUtils;
import com.yzh.android.view.EngineStatusView;
import com.yzh.android.view.ScaleBitmapView;
import com.yzh.android.view.StatusView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.BounceInterpolator;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeCustomELVAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter {

	private LayoutInflater vi;
	private Context context;
	BounceInterpolator bounceInterpolator;
	View v;

	private int resId;
	private List<EngineBeaninfo> list;

	private static final int GROUP_ITEM_RESOURCE = R.layout.item_home_status;
	private static final int CHILD_ITEM_RESOURCE = R.layout.view_item_engininfo_name;

	public HomeCustomELVAdapter(Context context, int resId, List<EngineBeaninfo> list) {
		this.context = context;
		this.resId = resId;
		this.list = list;
		vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		bounceInterpolator = new BounceInterpolator();
	}

	@SuppressLint("NewApi")
	@Override
	public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		final DeviceListBean bean =getChild(groupPosition, childPosition);
		Log.i("Cache", bean.ph);
		v = convertView;
		v = vi.inflate(CHILD_ITEM_RESOURCE, null);
		if (getChild(groupPosition, childPosition) != null&&bean.feedStatus!=null) {
			ViewHolder_name viewHolder_name =new ViewHolder_name();
			viewHolder_name.status_img =(ScaleBitmapView) v.findViewById(R.id.status_img);
			viewHolder_name.status_tv =(TextView) v.findViewById(R.id.status_tv);
			viewHolder_name.warning_tv =(TextView) v.findViewById(R.id.warning_tv);
			viewHolder_name.warning_tv.setText("警报:"+bean.alarmCount);
			
			if (bean.status.equals("1")) {
				viewHolder_name.status_img.setImageDrawable(context.getResources().getDrawable(R.drawable.lamp_yellow));
				viewHolder_name.status_tv.setText("在线");
			}
			if (bean.status.equals("2")){
				viewHolder_name.status_img.setImageDrawable(context.getResources().getDrawable(R.drawable.lamp_blue));
				viewHolder_name.status_tv.setText("离线");
			}
			LinearLayout li =(LinearLayout) v.findViewById(R.id.view_item_engineinfo_ll);
			addStatusView(li,context.getResources().getDrawable(R.drawable.thermometer_blue),getChild(groupPosition, childPosition).tmp,"水体温度",getChild(groupPosition, childPosition).tmp+"℃");
			addStatusView(li,context.getResources().getDrawable(R.drawable.o2_blue),getChild(groupPosition, childPosition).o2,"溶氧浓度",getChild(groupPosition, childPosition).o2+"mg/L");
			//本来存在ph值功能addStatusView(li,context.getResources().getDrawable(R.drawable.ph_blue),getChild(groupPosition, childPosition).ph,"PH值",getChild(groupPosition, childPosition).ph+"");
			String status ="";
			//第一个为 定时开关，第四个为自动与否，第八个表示开关
			//0x80   0x10  0x01
		/*	if ((Integer.valueOf(bean.feedStatus)&0x10)!=0&&(Integer.valueOf(bean.feedStatus)&0x01)!=0) {
				addEngineStatusView(li, context.getResources().getDrawable(R.drawable.machine_blue), "投料机状态", "开启(定时:开)");
			}else if ((Integer.valueOf(bean.feedStatus)&0x10)!=0&&(Integer.valueOf(bean.feedStatus)&0x01)==0){
				addEngineStatusView(li, context.getResources().getDrawable(R.drawable.machine_blue), "投料机状态", "关闭(定时:开)");
			}else if ((Integer.valueOf(bean.feedStatus)&0x10)==0&&(Integer.valueOf(bean.feedStatus)&0x01)!=0){
				addEngineStatusView(li, context.getResources().getDrawable(R.drawable.machine_blue), "投料机状态", "开启(定时:关)");
			}else if ((Integer.valueOf(bean.feedStatus)&0x10)==0&&(Integer.valueOf(bean.feedStatus)&0x01)==0){
				addEngineStatusView(li, context.getResources().getDrawable(R.drawable.machine_blue), "投料机状态", "关闭(定时:关)");
			}
		本来有投料机	*/
			int o2Int =Integer.valueOf(bean.o2Status);
			if ((o2Int&0x02)!=0) {
				status = status+"1#:开  ";
			}else{
				status = status+"1#:关  ";
			}
			if ((o2Int&0x04)!=0) {
				status = status+"2#:开  ";
			}else{
				status = status+"2#:关  ";
			}
			if ((o2Int&0x08)!=0) {
				status = status+"3#:开  ";
			}else{
				status = status+"3#:关  ";
			}
			String status2="";
			if ((o2Int&0x10)!=0) {
				status2 = status2+"模式:开  ";
			}else{
				status2 = status2+"模式:关  ";
			}
			if ((o2Int&0x80)!=0) {
				status2 = status2+"定时:开";
			}else{
				status2 = status2+"定时:关 ";
			}
			addEngineStatusView(li, context.getResources().getDrawable(R.drawable.ison_blue), "增氧泵状态", status);
			addEngineStatusView(li, context.getResources().getDrawable(R.drawable.ison_blue), "增氧泵状态", status2);
			
			addEngineStatusView(li, context.getResources().getDrawable(R.drawable.clock_blue), "设备更新时间",  bean.recvTime);
			addEngineStatusView(li, context.getResources().getDrawable(R.drawable.imei_blue), "设备串口号", bean.imei);
			addEngineStatusView(li, context.getResources().getDrawable(R.drawable.groupid_blue), "设备组号", bean.group_id);
			
			v.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent mIntent = new Intent();
					mIntent.setClass(context, DataCenterActivity.class);
					context.startActivity(mIntent);
					MyApplication.assignData("deviceId",
							bean.deviceId);
					MyApplication.assignData("groupId",
							bean.group_id);
					MyApplication.assignData("pondId",
							list.get(groupPosition).pondId);
					((Activity)(context)).overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
				}
			});
			viewHolder_name.warning_tv.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ToolAlert.loading(context, "正在获取中....");
					getEquipmentWarningList(PreferencesUtils.getString(context, "userId"));
				}
			});
		}
		return v;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		View v = convertView;
		final ViewHolder holder;
		if (v == null) {
			v = vi.inflate(GROUP_ITEM_RESOURCE, null);
			holder = new ViewHolder();
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.image = (ImageView) v.findViewById(R.id.imageView1);
		holder.tangkouname_tv = (TextView) v.findViewById(R.id.tangkouname_tv);
		holder.arraw_img = (ImageView) v.findViewById(R.id.arraw_img);
		holder.title_lr = (RelativeLayout) v.findViewById(R.id.title_lr);
		if (isExpanded) {
			holder.arraw_img.setBackgroundResource(R.drawable.ic_arrowup);
		}else{
			holder.arraw_img.setBackgroundResource(R.drawable.ic_arrowdown);
		}
		holder.tangkouname_tv.setText(list.get(groupPosition).pondName);
		return v;
	}

	@Override
	public DeviceListBean getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return list.get(groupPosition).deviceList.get(0);
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return list.get(groupPosition).deviceList.size();
	}

	@Override
	public int getGroupCount() {
		return list.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	public String getGroup(int groupPosition) {
		return "group-" + groupPosition;
	}

	class ViewHolder {
		RelativeLayout title_lr;
		ImageView image;
		TextView tangkouname_tv;
		ImageView arraw_img;
	}
	class ViewHolder_name {
		ScaleBitmapView status_img;
		TextView status_tv;
		TextView warning_tv;
	}
	public View addStatusView(LinearLayout li,Drawable drawable,String progress,String name,String arr){
		LinearLayout.LayoutParams p;
		p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		StatusView mStatusView =new StatusView(context);
		if (progress==null) {
			mStatusView.setNum(0);
			mStatusView.setLeftDrawable(drawable);
			mStatusView.setTitleText(name);
			mStatusView.setAtrrText(arr);
			li.addView(mStatusView, p);
		}else{
			mStatusView.setNum(Float.valueOf(progress));
			mStatusView.setLeftDrawable(drawable);
			mStatusView.setTitleText(name);
			mStatusView.setAtrrText(arr);
			li.addView(mStatusView, p);
		}
		return mStatusView;
	}
	public View addEngineStatusView(LinearLayout li,Drawable drawable,String name,String arr){
		LinearLayout.LayoutParams p;
		p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		EngineStatusView mStatusView =new EngineStatusView(context);
		mStatusView.setLeftDrawable(drawable);
		mStatusView.setTitleText(name);
		mStatusView.setAtrrText(arr);
		li.addView(mStatusView, p);
		return mStatusView;
	}
	public View inflateView(int layoutID){
		return vi.inflate(layoutID, null);
	}
	public void getEquipmentWarningList(String userId) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		ToolHTTP.get(context, Contants.equipmentErrorList, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				try {
					Response_main<EquipmentWarningBean> resultBean = gson.fromJson(response.toString(),
							new TypeToken<Response_main<EquipmentWarningBean>>() {
							}.getType());
					if (resultBean.code.equals("1")) {
						goToWarningActivity(resultBean.result);
					}else if(resultBean.code.equals("2")){
						
					}
				} catch (Exception e) {
					
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
			}
			@Override
			public void onCancel() {
				ToolAlert.closeLoading();
				super.onCancel();
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}

	private void goToWarningActivity(List<EquipmentWarningBean> result) {
		Bundle b = new Bundle();
		b.putSerializable("list", (Serializable) result);
		Intent intent =new Intent();
		intent.putExtras(b);
		intent.setClass(context, WarningActivity.class);
		context.startActivity(intent);
	}
}
