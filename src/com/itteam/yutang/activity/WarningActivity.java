package com.itteam.yutang.activity;

import java.util.ArrayList;

import com.itteam.yutang.R;
import com.itteam.yutang.adapter.WarningAdapter;
import com.itteam.yutang.bean.EquipmentWarningBean;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.util.ViewFindUtils;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class WarningActivity extends BaseActivity {
	ListView activity_warnings_lv;
	@Override
	public int bindLayout() {
		return R.layout.activity_warnings;
	}

	@Override
	public void initView(View view) {
		activity_warnings_lv =ViewFindUtils.find(view, R.id.activity_warnings_lv);
	}

	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle2 = getResources().getString(R.string.warnings);
		ActionBarManager.initBackTitle(this, this.getActionBar(), strCenterTitle2);
		Bundle bundle = this.getIntent().getExtras();  
		ArrayList<EquipmentWarningBean> groups = (ArrayList<EquipmentWarningBean>) bundle.getSerializable("list");
		WarningAdapter adapter =new WarningAdapter(getContext(), R.layout.view_item_activity_warnings, groups);
		activity_warnings_lv.setAdapter(adapter);
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}

}
