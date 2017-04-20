package com.itteam.yutang.activity;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.R;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.PondBean;
import com.itteam.yutang.bean.Response_main;
import com.itteam.yutang.bean.WronginfoBean;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;
import com.yzh.android.util.PreferencesUtils;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class DevicFaultModel extends DisPatcher{
	 private Context mContext;
	
	 
	    public DevicFaultModel(Context mContext) {
	        this.mContext = mContext;
	    }

	    //获取塘口信息
	    public void getPondInfo(String deviceId,String groupId) {
	    	
	    	Map<String, String> map = new HashMap<>();
	        map.put("deviceId", deviceId);
	        map.put("groupId", groupId);
	        ToolHTTP.get(mContext, Contants.equipmentHitch, map, new JsonHttpResponseHandler() {
	            @Override
	            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
	                Log.i("Cache", response.toString());
	                // tips:msg是服务器返回的消息

	                   Gson gson = new Gson();
	                    Response_main<WronginfoBean> resultBean = gson.fromJson(response.toString(),
	                            new TypeToken<Response_main<WronginfoBean>>() {
	                            }.getType());
	                    if ((resultBean.result).isEmpty() ) {
	                      
	                       ToolAlert.toastLong("没有设备故障");
	                    //   handleonSuccess(1,resultBean );
	                    }
	                else  {
	                	 handleonSuccess(1, resultBean);

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
	}

