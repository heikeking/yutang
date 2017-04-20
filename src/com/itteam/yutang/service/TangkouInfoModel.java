package com.itteam.yutang.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.PondInfoBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;

import android.content.Context;
import android.util.Log;

public class TangkouInfoModel extends DisPatcher {
	private Context mContext;

	public TangkouInfoModel(Context mContext) {
		this.mContext = mContext;
	}
	public void getPondInfo(String pondId) {
		Map<String, String> map = new HashMap<>();
		map.put("pondId", pondId);
		ToolHTTP.get(mContext, Contants.pondInfo, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<PondInfoBean> resultBean = gson.fromJson(response.toString(),
						new TypeToken<Response<PondInfoBean>>() {
						}.getType());
				if (resultBean.code == 1) {
					handleonSuccess(2, resultBean.result);
				} else {
					handleonFail(2, resultBean.result);
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
