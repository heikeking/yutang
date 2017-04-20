package com.itteam.yutang.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.ErrorBean;
import com.itteam.yutang.bean.LoginBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.bean.UserBean;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;
import com.yzh.android.util.PreferencesUtils;

import android.content.Context;
import android.util.Log;

public class LoginActivityModel extends DisPatcher{
	private Context mContext;
	public LoginActivityModel(Context context) {
		this.mContext =context;
	}
	//登陆操作
	public void Login(final String account,final String password){
		Map<String, String> parmsMap =new HashMap<>();
		parmsMap.put("userNumber", account);
		parmsMap.put("userPassword", password);
		ToolHTTP.get(mContext, Contants.loginUrl, parmsMap, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				Log.i("Cache", response.toString());
				Log.i("1234", "isUseRFC5179CompatibilityMode:"+this.isUseRFC5179CompatibilityMode());
				//tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<LoginBean>	resultBean = gson.fromJson(response.toString(), new TypeToken<Response<LoginBean>>() {
				}.getType());
				int code =resultBean.code;
				Log.i("Cache", resultBean.code+"");
				if (code==1) {
					LoginBean loginBean = resultBean.result;
					handleButtonSucceed(Contants.LOGINSUCCEED,new UserBean(loginBean.userId,loginBean.userName, loginBean.userNumber, account, password));
				}
				if (code==2) {
					Response<ErrorBean>	resultBean_error = gson.fromJson(response.toString(), new TypeToken<Response<ErrorBean>>() {
					}.getType());
					handleButtonSucceed(Contants.LOGINFAIL,((ErrorBean)resultBean_error.result).msg);
				}
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
			@Override
			public void onCancel() {
				ToolAlert.closeLoading();
				super.onCancel();
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				//处理失败
				handleButtonSucceed(Contants.LOGINFAIL,responseString);
			}
			
		}, "UTF-8");
		
	}
	public void saveAccount(String userName,String accout,String password,String userId,boolean islogined){
		PreferencesUtils.putString(mContext, "userId", userId);
		PreferencesUtils.putString(mContext, "userName", userName);
		PreferencesUtils.putString(mContext, "accout", accout);
		PreferencesUtils.putString(mContext, "password", password);
		PreferencesUtils.putBoolean(mContext, "isLogined", islogined);
	}
	
}
