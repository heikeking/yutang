package com.itteam.yutang.service;

import java.util.ArrayList;
import java.util.List;

import com.itteam.yutang.R;
import com.itteam.yutang.adapter.DataCenterAdapter;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.contants.Contants;
import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DataCenterModel extends DisPatcher{
	Context mContext;
	public DataCenterModel(Context mContext) {
		this.mContext = mContext;
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	public void loadUrl(WebView fragment_datacenter_webview,String url){
		if (url.equals("")) {
			handleonFail(Contants.FAIL,"网站地址错误");
			return;
		}
		fragment_datacenter_webview.setWebViewClient(new WebViewClient());
		fragment_datacenter_webview.getSettings().setJavaScriptEnabled(true);
	    fragment_datacenter_webview.loadUrl(url);
	    handleonSuccess(Contants.SUCCESS,"操作成功");
	}
	
	public DataCenterAdapter loadListData(){
		List<String> list = new ArrayList<>();
		list.add("设置设备工作参数");
		list.add("传感器校准&设备时间同步");
		list.add("签到记录");
		if (list.size()==0) {
			handleonFail(0, "数据为空");
		}
		return new DataCenterAdapter(mContext, R.layout.view_item_datacenter, list);
	}
}
