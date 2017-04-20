package com.itteam.yutang.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.activity.SetEnginsParmsActivity;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.MsgBean;
import com.itteam.yutang.bean.PondConfigBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.MyApplication;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

public class SetEnginesParmsModel extends DisPatcher {
	Context mContext;

	public SetEnginesParmsModel(Context mContext) {
		this.mContext = mContext;
	}

	// 写入控制器各种控制单元状态
	public void updateChangeUnitType(String o2_switch, String feed_switch, final View view, final View view2,
			final boolean ischeck) {
		Map<String, String> map = new HashMap<>();
		map.put("deviceId", (String) MyApplication.gainData("deviceId"));
		map.put("groupId", (String) MyApplication.gainData("groupId"));
		map.put("o2_switch", o2_switch);
		map.put("feed_switch", feed_switch);
		Log.i("1234", "o2_switch:"+o2_switch);
		Log.i("1234", "feed_switch:"+feed_switch);
		ToolHTTP.get(mContext, Contants.changeUnitType, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(), new TypeToken<Response<MsgBean>>() {
				}.getType());
				if (resultBean.code == 1) {
					handleonSuccess(2, resultBean.result.msg);
					handleonSuccessOnparms(2, resultBean.result.msg, view, view2, ischeck);
				} else if (resultBean.code == 2) {
					handleonFailOnparms(2, resultBean.result.msg, view, view2, ischeck);
					handleonFail(2, resultBean.result.msg);
					Log.i("Cache", "handleonFailOnparms");
				}
				SetEnginsParmsActivity.ischecked_o2 = true;
				SetEnginsParmsActivity.ischecked_o2_2 = true;
				SetEnginsParmsActivity.ischecked_o2_3 = true;
				SetEnginsParmsActivity.ischecked_feed = true;
				SetEnginsParmsActivity.ischecked_auto_o2 = true;
				SetEnginsParmsActivity.ischecked_auto_feed = true;
				SetEnginsParmsActivity.ischecked_dingshi =true;
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				handleonFail(2, responseString);
				handleonFailOnparms(2, responseString, view, view2, ischeck);
				SetEnginsParmsActivity.ischecked_o2 = true;
				SetEnginsParmsActivity.ischecked_o2_2 = true;
				SetEnginsParmsActivity.ischecked_o2_3 = true;
				SetEnginsParmsActivity.ischecked_feed = true;
				SetEnginsParmsActivity.ischecked_auto_o2 = true;
				SetEnginsParmsActivity.ischecked_auto_feed = true;
				SetEnginsParmsActivity.ischecked_dingshi =true;
			}

			@Override
			public void onFinish() {
				//ToolAlert.closeLoading();
				super.onFinish();
			}

			@Override
			public void onCancel() {
				SetEnginsParmsActivity.ischecked_o2 = true;
				SetEnginsParmsActivity.ischecked_feed = true;
				SetEnginsParmsActivity.ischecked_o2_2 = true;
				SetEnginsParmsActivity.ischecked_o2_3 = true;
				SetEnginsParmsActivity.ischecked_auto_o2 = true;
				SetEnginsParmsActivity.ischecked_auto_feed = true;
				SetEnginsParmsActivity.ischecked_dingshi =true;
				super.onCancel();
			}
		}, "UTF-8");

	}

	// 获取控制器最新控制信息
	public void getPondConfig() {
		Map<String, String> map = new HashMap<>();
		map.put("pondId", (String) MyApplication.gainData("pondId"));
		ToolHTTP.get(mContext, Contants.pondConfig, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				try {
					Response<PondConfigBean> resultBean = gson.fromJson(response.toString(),
							new TypeToken<Response<PondConfigBean>>() {
							}.getType());
					if (resultBean.code == 1) {
						handleonSuccess(3, resultBean.result);
					} else if (resultBean.code == 2) {
						handleonFail(3, resultBean.result);
					}
				} catch (Exception e) {
					handleonFail(3, "获取数据失败");
//					ToolAlert.dialog(mContext, "提示", "获取数据失败",new DialogInterface.OnClickListener() {
//						
//						@Override
//						public void onClick(DialogInterface dialog, int which) {
//							dialog.dismiss();
//						}
//					});
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

	// 读取控制器配置信息
	public void readControllerConfig() {
		Map<String, String> map = new HashMap<>();
		map.put("deviceId", (String) MyApplication.gainData("deviceId"));
		map.put("groupId", (String) MyApplication.gainData("groupId"));
		ToolHTTP.get(mContext, Contants.readControllerConfig, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(),
						new TypeToken<Response<MsgBean>>() {
						}.getType());
				if (resultBean.code == 1) {
					handleonSuccess(4, resultBean.result.msg);
				} else if (resultBean.code == 2) {
					handleonFail(4, resultBean.result.msg);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				handleonFail(4, responseString);
			}

			@Override
			public void onFinish() {
				//ToolAlert.closeLoading();
				super.onFinish();
			}

		}, "UTF-8");

	}

	// 写入控制器阀值
	public void writeCommandThreshold(String tmp_max, String tmp_min, String tmp_r, String ph_max, String ph_min,
			String ph_r, String o2_max, String o2_min, String o2_r, String o2_am_b, String o2_am_e, String o2_pm_b,
			String o2_pm_e, String f_am_b, String f_am_e, String f_pm_b, String f_pm_e) {
		Map<String, String> map = new HashMap<>();
		map.put("deviceId", (String) MyApplication.gainData("deviceId"));
		map.put("groupId", (String) MyApplication.gainData("groupId"));
		map.put("tmp_max", tmp_max);
		map.put("tmp_min", tmp_min);
		map.put("tmp_r", tmp_r);
		map.put("ph_max", ph_max);
		map.put("ph_min", ph_min);
		map.put("ph_r", ph_r);
		map.put("o2_max", o2_max);
		map.put("o2_min", o2_min);
		map.put("o2_r", o2_r);
		map.put("o2_am_b", o2_am_b);
		map.put("o2_am_e", o2_am_e);
		map.put("o2_pm_b", o2_pm_b);
		map.put("o2_pm_e", o2_pm_e);
		map.put("f_am_b", f_am_b);
		map.put("f_am_e", f_am_e);
		map.put("f_pm_b", f_pm_b);
		map.put("f_pm_e", f_pm_e);
		ToolHTTP.get(mContext, Contants.writeCommandThreshold, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.i("Cache", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(), new TypeToken<Response<MsgBean>>() {
				}.getType());
				if (resultBean.code == 1) {
					handleonSuccess(1, resultBean.result.msg);
				} else if (resultBean.code == 2) {
					handleonFail(1, resultBean.result.msg);
				} 
				Log.i("1234", "msg:"+resultBean.result.msg);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				handleonFail(1, responseString);
				Log.i("1234", "msg:"+throwable);
			}

			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}

		}, "UTF-8");

	}

	public Tipsinfo judgeStatus(String tmp, String o2) {
		Tipsinfo tipsinfo = new Tipsinfo();
		tipsinfo.code=1;
		tipsinfo.msg = "";
	/*if (tmp != null && (Float.valueOf(tmp) instanceof Float) && (Float.valueOf(ph) instanceof Float)
				&& (Float.valueOf(o2) instanceof Float)) {
			tipsinfo.code = -1;
			tipsinfo.msg = "请填写正确的信息";
			return tipsinfo;
		}*/
		try {
			Float.valueOf(tmp);
		//	Float.valueOf(ph);
			Float.valueOf(o2);
		} catch (NumberFormatException e) {
			tipsinfo.code = -1;
			tipsinfo.msg = "请输入数字!";
			return tipsinfo;
		}
		if (Float.valueOf(tmp) < 0 || Float.valueOf(tmp) >= 50) {
			tipsinfo.code = -1;
			tipsinfo.msg = "请填写符合范围的温度";
			return tipsinfo;
		}
	//	if (Float.valueOf(ph) < 0 || Float.valueOf(ph) >= 15) {
	//		tipsinfo.code = -1;
	//		tipsinfo.msg = "请填写符合范围的PH值";
	//		return tipsinfo;
	
	//	}
		if (Float.valueOf(o2) < 0 || Float.valueOf(02) > 20) {
			tipsinfo.code = -1;
			tipsinfo.msg = "请填写符合范围的氧气(0-20)";
			return tipsinfo;
		}
		return tipsinfo;
	}

	public class Tipsinfo {
		public int code;
		public String msg;
	}

	public String judgeO2Status(boolean isSwitch, boolean isAutoSwitch) {
		if (isSwitch && isAutoSwitch) {
			return "17";
		}
		if (isSwitch && isAutoSwitch == false) {
			return "1";
		}
		if (isSwitch == false && isAutoSwitch == false) {
			return "0";
		}
		if (isSwitch == false && isAutoSwitch == true) {
			return "16";
		}
		return null;
	}
}
