package com.itteam.yutang.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.R;
import com.itteam.yutang.activity.MainActivity;
import com.itteam.yutang.activity.WarningActivity;
import com.itteam.yutang.bean.EquipmentWarningBean;
import com.itteam.yutang.bean.Response_main;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.base.BaseService;
import com.yzh.android.model.NotificationMessage;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;
import com.yzh.android.util.PreferencesUtils;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class WarningService extends BaseService {
	public Timer timer;

	@Override
	public void onCreate() {
		super.onCreate();
		timer = new Timer(true);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new MsgBinder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				getEquipmentWarningList(PreferencesUtils.getString(getApplication(), "userId"));
			}
		};
		timer.schedule(new TimerTask() {	

			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 17;
				handler.sendMessage(msg);
			}
		}, 1000 * 6*5,1000*60*5);
		return super.onStartCommand(intent, flags, startId);
	}

	public class MsgBinder extends Binder {
		/**
		 * 获取当前Service的实例
		 * 
		 * @return
		 */
		public WarningService getService() {
			return WarningService.this;
		}
	}

	public void SendMsgNotificationMessage(List<EquipmentWarningBean> list){
		for (int i = 0; i < list.size(); i++) {
			NotificationMessage notificationMessage = new NotificationMessage();
			notificationMessage.setIconResId(R.drawable.appicon_icon);
			notificationMessage.setMsgTitle("智慧鱼塘");
			notificationMessage.setMsgContent(list.get(i).pondName+":"+list.get(i).msg);
			notificationMessage.setForwardComponent(WarningActivity.class);
			Bundle b = new Bundle();
			b.putSerializable("list", (Serializable) list);
			notificationMessage.setExtras(b);
			notificationMessage.setId(View.generateViewId());
			ToolAlert.notification(getApplicationContext(), notificationMessage);
		}
	}
	public void getEquipmentWarningList(String userId) {
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		ToolHTTP.get(getApplicationContext(), Contants.equipmentErrorList, map, new JsonHttpResponseHandler() {
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
						SendMsgNotificationMessage(resultBean.result);
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
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}
}
