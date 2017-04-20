package com.itteam.yutang.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.AddtangkouBean;
import com.itteam.yutang.bean.MsgBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;

import android.content.Context;
import android.util.Log;

public class AddTangkouEnginesModel extends DisPatcher{
	private Context mContext;

	public AddTangkouEnginesModel(Context mContext) {
		this.mContext = mContext;
	}

	public List<String> getGroupId(){
		List<String> list =new ArrayList<>();
		list.add("0");
		list.add("1");
		return list;
	}
	// 添加塘口信息
	public void addPondInfo(String imei, String groupId, String pondId) {
		Map<String, String> map = new HashMap<>();
		map.put("pondId", pondId);
		map.put("imei", imei);
		map.put("groupId", groupId);
		ToolHTTP.get(mContext, Contants.equipmentAdd, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(),
						new TypeToken<Response<MsgBean>>() {
						}.getType());
				if (resultBean.code == 1) {
					handleonSuccess(2, resultBean.result.msg);
				} else {
					Response<MsgBean> msg = gson.fromJson(response.toString(), new TypeToken<Response<MsgBean>>() {
					}.getType());
					handleonFail(2, msg.result.msg);
				}

			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				handleonFail(2, responseString);
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}
}
