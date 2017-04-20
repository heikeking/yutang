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
import com.itteam.yutang.bean.PondSetUpInfo;
import com.itteam.yutang.bean.PondSetUpListBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;
import com.yzh.android.util.PreferencesUtils;

import android.content.Context;
import android.util.Log;

public class AddTangkouModel extends DisPatcher {
	private Context mContext;

	public AddTangkouModel(Context mContext) {
		this.mContext = mContext;
	}

	// 添加塘口信息
	public void getPondInfo(String pondName, String pondArea, String pondDepth, String pondDensity,
			String pondUsage, String pondO2_power, String pondRental, String pondO2_addId, String pondWaterSuppliesId,
			String pondTopId, String pondBottomId, String pondDischargeModeId, String pondThicknessId) {
		String userId = PreferencesUtils.getString(mContext, "userId", "0");
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("pondName", pondName);
		map.put("pondArea", pondArea);
		map.put("pondDepth", pondDepth);
		map.put("pondDensity", pondDensity);
		map.put("pondUsage", pondUsage);
		map.put("pondO2_power", pondO2_power);
		map.put("pondRental", pondRental);
		map.put("pondO2_addId", pondO2_addId);
		map.put("pondWaterSuppliesId", pondWaterSuppliesId);
		map.put("pondTopId", pondTopId);
		map.put("pondBottomId", pondBottomId);
		map.put("pondDischargeModeId", pondDischargeModeId);
		map.put("pondThicknessId", pondThicknessId);
		ToolHTTP.get(mContext, Contants.pondAdd, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<AddtangkouBean> resultBean = gson.fromJson(response.toString(),
						new TypeToken<Response<AddtangkouBean>>() {
						}.getType());
				if (resultBean.code == 1) {
					handleonSuccess(2, resultBean.result.pondId);
				} else {
					Response<MsgBean> msg = gson.fromJson(response.toString(), new TypeToken<Response<MsgBean>>() {
					}.getType());
					handleonFail(2, msg.result.msg);
				}

			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				handleonFail(1, responseString);
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}

	// 塘口信息
	public void getPondSetUpList() {
		ToolHTTP.get(mContext, Contants.pondSetUpList, null, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<PondSetUpListBean> resultBean = gson.fromJson(response.toString(),
						new TypeToken<Response<PondSetUpListBean>>() {
						}.getType());
				if (resultBean.code == 1) {
					handleonSuccess(1, resultBean.result);
				} else {
					handleonFail(1, resultBean.result);
				}

			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				handleonFail(1, responseString);
			}

		}, "UTF-8");

	}
	public List<String> spellList(List<PondSetUpInfo> infos){
		List<String> list =new ArrayList<>();
		for (int i = 0; i < infos.size(); i++) {
			list.add(infos.get(i).pondSetUpInfo);
		}
		return list;
	}
}
