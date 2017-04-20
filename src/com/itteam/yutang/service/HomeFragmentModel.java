package com.itteam.yutang.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.R;
import com.itteam.yutang.adapter.HomeCustomELVAdapter;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.EngineBeaninfo;
import com.itteam.yutang.bean.Response_main;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.base.MyBaseAdapter;
import com.yzh.android.tools.ToolHTTP;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeFragmentModel extends DisPatcher{
	private Context mContext;
	public HomeFragmentModel(Context context) {
		this.mContext =context;
	}
	//登陆操作
	public void getEngineInfo(String userId){
		Map<String, String> parmsMap =new HashMap<>();
		parmsMap.put("userId", userId);
		Log.i("Cache", userId.toString());
		ToolHTTP.get(mContext, Contants.equipmentBrief, parmsMap, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				Log.i("Cache", response.toString());
				//tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response_main<EngineBeaninfo>	resultBean = gson.fromJson(response.toString(), new TypeToken<Response_main<EngineBeaninfo>>() {
				}.getType());
				if (Integer.valueOf(resultBean.code)==1) {
					handleHomeContentSucceed(true, resultBean);
					return;
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				//处理失败
				Log.i("1234", responseString);
				handleHomeContentSucceed(false, responseString);
			}
			
		}, "UTF-8");
		
	}
	
	//加载数据进入
	public HomeCustomELVAdapter BindAdapter(Context mContext,int resId, List<EngineBeaninfo> list){
		return new HomeCustomELVAdapter(mContext, resId,list);
	}
}
