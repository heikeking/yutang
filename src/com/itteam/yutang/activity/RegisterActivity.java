package com.itteam.yutang.activity;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itteam.yutang.R;
import com.itteam.yutang.bean.MsgBean;
import com.itteam.yutang.bean.Response;
import com.itteam.yutang.contants.Contants;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.tools.ToolHTTP;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.EditTextWithDeleteButton;
import com.yzh.android.view.FButton;

import android.content.Context;
import android.util.Log;
import android.view.View;

public class RegisterActivity extends BaseActivity {
	EditTextWithDeleteButton account,password,password2;
	FButton regiter_btn;
	@Override
	public int bindLayout() {
		return R.layout.activity_register;
	}

	@Override
	public void initView(View view) {
		account = ViewFindUtils.find(view, R.id.account);
		password = ViewFindUtils.find(view, R.id.password);
		password2 = ViewFindUtils.find(view, R.id.password2);
		regiter_btn  = ViewFindUtils.find(view, R.id.regiter_btn);
	}

	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle = getResources().getString(R.string.register);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		regiter_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (!password.getText().equals("")&&!password2.getText().equals("")&&!account.getText().equals("")) {
					if (password.getText().equals(password2.getText())) {
						if (MyApplication.isNetworkReady()) {
							UserRegister(account.getText(), password.getText());
						}else{
							ToolAlert.toastShort("网络连接失败");
						}
					}else {
						ToolAlert.toast("请将密码输入一致", 2000);
					}
				}else{
					ToolAlert.toast("请将信息填写完整", 2000);
				}
			}
		});
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}
	public void UserRegister(String userNumber, String userPassword) {
		Map<String, String> map = new HashMap<>();
		map.put("userNumber", userNumber);
		map.put("userPassword", userPassword);
		ToolHTTP.get(getContext(), Contants.userRegister, map, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.e("1234", response.toString());
				// tips:msg是服务器返回的消息
				Gson gson = new Gson();
				Response<MsgBean> resultBean = gson.fromJson(response.toString(),
						new TypeToken<Response<MsgBean>>() {
						}.getType());
				if (resultBean.code==1) {
					finish();
				}else if(resultBean.code==2){
					ToolAlert.toast(resultBean.result.msg, 2000);
				}
				
			}
			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
				ToolAlert.toast(errorResponse.toString(), 2000);
				super.onFailure(statusCode, headers, throwable, errorResponse);
			}
			@Override
			public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
				// 处理失败
				ToolAlert.toast(responseString, 2000);
			}
			@Override
			public void onFinish() {
				ToolAlert.closeLoading();
				super.onFinish();
			}
		}, "UTF-8");

	}
}
