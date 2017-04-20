package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;

import android.content.Context;
import android.view.View;

public class DeleteEnginesActivity extends BaseActivity {

	@Override
	public int bindLayout() {
		return R.layout.activity_deleteengines;
	}

	@Override
	public void initView(View view) {

	}

	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle = getResources().getString(R.string.manage_tangkou_delete);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
