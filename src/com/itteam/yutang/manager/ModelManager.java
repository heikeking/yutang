package com.itteam.yutang.manager;

import android.content.Context;
import android.webkit.WebView;

import java.util.List;

import com.itteam.yutang.activity.DevicFaultModel;
import com.itteam.yutang.adapter.DataCenterAdapter;
import com.itteam.yutang.adapter.HomeCustomELVAdapter;
import com.itteam.yutang.base.IHomeListener;
import com.itteam.yutang.base.ILoginListener;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.base.DisPatcher;
import com.itteam.yutang.bean.EngineBeaninfo;
import com.itteam.yutang.service.DataCenterModel;
import com.itteam.yutang.service.HomeFragmentModel;
import com.itteam.yutang.service.LoginActivityModel;
import com.itteam.yutang.service.PondModel;

public class ModelManager extends DisPatcher {
	private Context mContext;
	private LoginActivityModel loginActivityModel;
	private HomeFragmentModel homeFragmentModel;

	private DataCenterModel dataCenterModel;
	private PondModel podModel;
	private DevicFaultModel devicFaultModel;

	public ModelManager(Context context) {
		this.mContext = context;
		loginActivityModel = new LoginActivityModel(context);
		homeFragmentModel = new HomeFragmentModel(context);
		dataCenterModel = new DataCenterModel(mContext);
		podModel = new PondModel(mContext);
		devicFaultModel=new DevicFaultModel(mContext);
	}

	public void Login(String accout, String password) {
		loginActivityModel.Login(accout, password);
	}

	public void saveAccount(String userName, String accout, String password, String userId, boolean islogined) {
		loginActivityModel.saveAccount(userName, accout, password, userId, islogined);
	}

	public void getEngineInfo(String userId) {
		homeFragmentModel.getEngineInfo(userId);
	}
	public void getPondInfo(String deviceId,String group_id){
		devicFaultModel.getPondInfo(deviceId, group_id);
	}

	public HomeCustomELVAdapter BindAdapter(Context mContext, int resId, List<EngineBeaninfo> list) {
		return homeFragmentModel.BindAdapter(mContext, resId, list);
	}

	public void loadUrl(WebView fragment_datacenter_webview, String url) {
		dataCenterModel.loadUrl(fragment_datacenter_webview, url);
	}

	public DataCenterAdapter loadListData() {
		return dataCenterModel.loadListData();
	}

	// 塘口信息
	public void getPondInfo(String userId) {
		podModel.getPondInfo(userId);
	}

	public void deletePondInfo(String userId, String pondId) {
		podModel.deletePondInfo(userId, pondId);
	}

	@Override
	public void addEventListener(ILoginListener listener) {
		loginActivityModel.addEventListener(listener);
		super.addEventListener(listener);
	}

	@Override
	public void addEventHomeListener(IHomeListener listener) {
		homeFragmentModel.addEventHomeListener(listener);
		super.addEventHomeListener(listener);
	}

	@Override
	public void addDatatCenterListener(ISimpleListener listener) {
		dataCenterModel.addDatatCenterListener(listener);
		super.addDatatCenterListener(listener);
	}
//	public void addDevicFaultListener(ISimpleListener listener) {
//		devicFaultModel.addDevicFaultListener(listener);
//		super.addDevicFaultListener(listener);
//	}


	public void addSimpleListener(ISimpleListener listener) {
		podModel.addDatatCenterListener(listener);
		super.addDatatCenterListener(listener);
	}
	public void addDeviceFaultListener(ISimpleListener listener) {
		devicFaultModel.addDatatCenterListener(listener);
		super.addDatatCenterListener(listener);
	}
}
