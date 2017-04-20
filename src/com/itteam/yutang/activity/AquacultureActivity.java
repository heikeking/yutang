package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.util.ViewFindUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AquacultureActivity extends BaseActivity{
	WebView fragment_datacenter_webview;
	@Override
	public int bindLayout() {
		return R.layout.add_work;  //后来改的增值业务页面
	}

	@Override
	public void initView(View view) {
		fragment_datacenter_webview = ViewFindUtils.find(view, R.id.fragment_datacenter_webview);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void doBusiness(Context mContext) {
		 Intent intent=getIntent();
	     String title=intent.getStringExtra("title");
		ActionBarManager.initBackTitle(this, getActionBar(),title);
		 String url = "";
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
