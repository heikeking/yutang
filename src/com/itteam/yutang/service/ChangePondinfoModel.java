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
import com.itteam.yutang.bean.MsgBean;
import com.itteam.yutang.bean.PondInfoBean;
import com.itteam.yutang.bean.PondSetUpInfo;
import com.itteam.yutang.bean.PondSetUpListBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.MyApplication;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;

import android.content.Context;
import android.util.Log;

public class ChangePondinfoModel extends DisPatcher {
	private Context mContext;

	public ChangePondinfoModel(Context mContext) {
		this.mContext = mContext;
	}
	public void getPonddetailInfo(String pondId) {
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
					handleonSuccess(3, resultBean.result);
				} else {
					handleonFail(3, resultBean.result);
				}

			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				handleonFail(3, responseString);
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");
	}

	public void getPondInfo(String pondName, String pondArea, String pondDepth, String pondDensity, String pondUsage,
			String pondO2_power, String pondRental, String pondO2_addId, String pondWaterSuppliesId, String pondTopId,
			String pondBottomId, String pondDischargeModeId, String pondThicknessId) {
		String pondId = (String) MyApplication.gainData("pondId_addengines");
		Map<String, String> map = new HashMap<>();
		map.put("pondId", pondId);
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
		ToolHTTP.get(mContext, Contants.pondUpdate, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(), new TypeToken<Response<MsgBean>>() {
				}.getType());
				if (resultBean.code == 1) {
					handleonSuccess(2, resultBean.result.msg);
				} else {
					handleonFail(2, resultBean.result.msg);
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

			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}

	public List<String> spellList(List<PondSetUpInfo> infos) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < infos.size(); i++) {
			list.add(infos.get(i).pondSetUpInfo);
		}
		return list;
	}

}
