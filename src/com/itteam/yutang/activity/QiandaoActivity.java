package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.manager.ModelManager;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.util.PreferencesUtils;
import com.yzh.android.util.ViewFindUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

public class QiandaoActivity extends BaseActivity implements ISimpleListener {
	WebView fragment_datacenter_webview;
	private ModelManager mManager;

	@Override
	public int bindLayout() {
		return R.layout.fragment_datacenter;
	}

	@Override
	public void initView(View view) {
		fragment_datacenter_webview = ViewFindUtils.find(view, R.id.fragment_datacenter_webview);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle = "签到记录";
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		mManager = new ModelManager(getContext());
		mManager.addDatatCenterListener(this);
		String userId = (String) PreferencesUtils.getString(getContext(), "userId", "");
		String url = "http://fish.zjit.org/Fish/Index/dateList?userId=" + userId;
		Log.i("1234", "Url:"+url);
		mManager.loadUrl(fragment_datacenter_webview, url);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onFail(int status, Object msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		// TODO Auto-generated method stub
		return false;
	}
}
