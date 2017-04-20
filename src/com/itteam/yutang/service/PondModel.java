package com.itteam.yutang.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.MsgBean;
import com.itteam.yutang.bean.PondBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.bean.Response_main;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;
import com.yzh.android.util.PreferencesUtils;

import android.content.Context;
import android.util.Log;

public class PondModel extends DisPatcher {
	private Context mContext;

	public PondModel(Context mContext) {
		this.mContext = mContext;
	}

	//获取塘口信息
	public void getPondInfo(String userId) {
		userId = PreferencesUtils.getString(mContext, "userId","0");
		Log.i("Cache", "userId:"+userId);
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		ToolHTTP.get(mContext, Contants.pondList, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				try {
					Gson gson = new Gson();
					Response_main<PondBean> resultBean = gson.fromJson(response.toString(),
							new TypeToken<Response_main<PondBean>>() {
							}.getType());
					if (Integer.valueOf(resultBean.code) == 1) {
						handleonSuccess(1, resultBean.result);
					}
				} catch (Exception e) {
					ToolAlert.toastShort("没有相关塘口信息");
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				handleonFail(0, responseString);
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}
	//删除塘口信息
	public void deletePondInfo(String userId,String pondId) {
			userId = PreferencesUtils.getString(mContext, "userId","0");
			Log.i("Cache", "userId:"+userId);
			Map<String, String> map = new HashMap<>();
			map.put("userId", userId);
			map.put("pondId", pondId);
			
			ToolHTTP.get(mContext, Contants.pondDelete, map, new JsonHttpResponseHandler() {
				@Override
				public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
					Log.i("Cache", response.toString());
					// tips:msg是服务器返回的消息
					Gson gson = new Gson();
					Response<MsgBean> resultBean = gson.fromJson(response.toString(),
							new TypeToken<Response<MsgBean>>() {
							}.getType());
					if (Integer.valueOf(resultBean.code) == 1) {
						Log.i("1234", "好好好好好的");
						handleonSuccess(2, resultBean.result);
					}else if (Integer.valueOf(resultBean.code) == 2) {
						handleonFail(2, resultBean.result);
					}
				}

				@Override
				public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
					// 处理失败
					handleonFail(2, responseString);
				}

			}, "UTF-8");

		}
}
