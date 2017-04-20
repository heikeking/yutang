package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.bean.PondInfoBean;
import com.itteam.yutang.service.TangkouInfoModel;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.TangkouinfoView;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class TangkouInfoActivity extends BaseActivity implements ISimpleListener{
	LinearLayout tangkou_infoll;
	TangkouInfoModel model;
	@Override
	public int bindLayout() {
		return R.layout.activity_tangkouinfo;
	}

	@Override
	public void initView(View view) {
		tangkou_infoll =ViewFindUtils.find(view, R.id.tangkou_infoll);
	}

	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle = getResources().getString(R.string.manage_tangkouinfo);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		model =new TangkouInfoModel(mContext);
		model.addDatatCenterListener(this);
		model.getPondInfo((String) MyApplication.gainData("pondId_addengines"));
		
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}
	public TangkouinfoView addTangkouinfoView(String name,String name2) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		TangkouinfoView switchSingleView = new TangkouinfoView(getContext());
		switchSingleView.setTangkouinfo_txt1(name);
		switchSingleView.setTangkouinfo_txt2(name2);
		//switchSingleView.switchButton.setId(id);
		tangkou_infoll.addView(switchSingleView, params);
		return switchSingleView;
	}
	@Override
	public boolean onFail(int status, Object msg) {
		
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		PondInfoBean bean =(PondInfoBean) msg;
		addTangkouinfoView("塘口名称:", bean.pondName);
		addTangkouinfoView("塘口面积:", bean.pondArea);
		addTangkouinfoView("塘口深度:", bean.pondDepth);
		addTangkouinfoView("增氧方式:", bean.pondO2_add);
		addTangkouinfoView("塘口水源:", bean.pondWaterSupplies);
		addTangkouinfoView("塘口池顶:", bean.pondTop);
		addTangkouinfoView("塘口池底:", bean.pondBottom);
		addTangkouinfoView("排污方式:", bean.pondDischargeMode);
		addTangkouinfoView("塘口租金:", bean.pondRental);
		addTangkouinfoView("塘口密度:", bean.pondDensity);
		addTangkouinfoView("塘口年龄:", bean.pondUsage);
		addTangkouinfoView("增氧功率:", bean.pondO2_power);
		return false;
	}
}
