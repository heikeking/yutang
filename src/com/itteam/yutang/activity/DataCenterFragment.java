package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.manager.ModelManager;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseFragmentV4;
import com.yzh.android.util.ViewFindUtils;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;

public class DataCenterFragment extends BaseFragmentV4 implements ISimpleListener{
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
		 mManager = new ModelManager(getContext());
		 mManager.addDatatCenterListener(this);
		 String pondId = (String) MyApplication.gainData("pondId");
		 String url = "http://fish.zjit.org/Fish/?pondId="+pondId;
		 mManager.loadUrl(fragment_datacenter_webview, url);
	}

	@Override
	protected void lazyLoad() {
		
	}

	@Override
	public boolean onFail(int status, Object msg) {
		
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		
		return false;
	}
}
