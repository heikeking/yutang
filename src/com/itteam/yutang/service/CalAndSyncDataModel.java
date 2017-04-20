package com.itteam.yutang.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.MsgBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.MyApplication;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;

import android.content.Context;
import android.util.Log;

public class CalAndSyncDataModel extends DisPatcher {
	Context mContext;

	public CalAndSyncDataModel(Context mContext) {
		this.mContext = mContext;
	}

	// 请求参数校准
	public void calibrationEquipment(String type) {
		Map<String, String> map = new HashMap<>();
		map.put("deviceId", (String) MyApplication.gainData("deviceId"));
		map.put("groupId", (String) MyApplication.gainData("groupId"));
		map.put("type", type);
		ToolHTTP.get(mContext, Contants.calibrationEquipment, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(), new TypeToken<Response<MsgBean>>() {
				}.getType());
				if (resultBean.code==1) {
					handleonSuccess(1, resultBean.result.msg);
				}else if(resultBean.code==2){
					handleonFail(1, resultBean.result.msg);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				Log.i("1234", "statusCode:"+statusCode+"   "+"responseString:"+responseString+"  throwable:"+throwable);
				handleonFail(statusCode, responseString);
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}

	public void calibrationTime() {
		Map<String, String> map = new HashMap<>();
		map.put("deviceId", (String) MyApplication.gainData("deviceId"));
		Log.i("1234", "deviceId:"+(String) MyApplication.gainData("deviceId"));
		ToolHTTP.get(mContext, Contants.calibrationTime, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(), new TypeToken<Response<MsgBean>>() {
				}.getType());
				Log.i("1234", resultBean.result.msg);
				if (resultBean.code==1) {
					handleonSuccess(2, resultBean.result.msg);
				}else if(resultBean.code==2){
					handleonFail(2, resultBean.result.msg);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				Log.i("1234", "statusCode:"+statusCode+"   "+"responseString:"+responseString+"  throwable:"+throwable);
				handleonFail(statusCode, responseString);
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}
}
