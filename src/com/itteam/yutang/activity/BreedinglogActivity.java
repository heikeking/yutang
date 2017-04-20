package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.util.PreferencesUtils;
import com.yzh.android.util.ViewFindUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BreedinglogActivity extends BaseActivity{
	WebView fragment_datacenter_webview;
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
		String strCenterTitle = getResources().getString(R.string.breedinglog);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		 String url = "http://fish.zjit.org/Fish/log/Loglist?userId="+PreferencesUtils.getString(mContext, "userId","0");
		 loadUrl(fragment_datacenter_webview, url);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	public void loadUrl(WebView fragment_datacenter_webview,String url){
		if (url.equals("")) {
			return;
		}
		fragment_datacenter_webview.setWebViewClient(new WebViewClient());
		fragment_datacenter_webview.getSettings().setJavaScriptEnabled(true);
	    fragment_datacenter_webview.loadUrl(url);
	}
}
