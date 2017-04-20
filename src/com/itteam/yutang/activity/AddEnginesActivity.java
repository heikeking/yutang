package com.itteam.yutang.activity;

import java.util.List;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.service.AddTangkouEnginesModel;
import com.itteam.yutang.service.AddTangkouModel;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.AddTangkouView;
import com.yzh.android.view.AddTangkouView_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class AddEnginesActivity extends BaseActivity implements ISimpleListener {
	LinearLayout addtangkouengines_ll;
	AddTangkouEnginesModel model;
	AddTangkouView switchSingleView;
	AddTangkouView_2 groupIdView;

	@Override
	public int bindLayout() {
		return R.layout.activity_addengines;
	}

	@Override
	public void initView(View view) {
		addtangkouengines_ll = ViewFindUtils.find(view, R.id.addtangkouengines_ll);
	}

	@Override
	public void doBusiness(Context mContext) {
		model = new AddTangkouEnginesModel(getContext());
		model.addDatatCenterListener(this);
		String strCenterTitle = getResources().getString(R.string.add_tangkouengines);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		switchSingleView = addSetSwitchView("主机串号:", "请输入主机串号");
		groupIdView = addSetSwitchView2("组号:", model.getGroupId());
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			// 关闭窗体动画显示
			// overridePendingTransition(R.anim.activity_close,R.anim.alpha_out);
			overridePendingTransition(0, R.anim.base_slide_right_out);
			break;
		case R.id.save:
			if (switchSingleView.getEditText().equals("") || groupIdView.getEditText().equals("")
					|| MyApplication.gainData("pondId_addengines") == null) {
				ToolAlert.dialog(getContext(), "提示", (String) "请把信息输入完全", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
			} else {
				ToolAlert.loading(getContext(), "正在上传中......");
				model.addPondInfo(switchSingleView.getEditText(), groupIdView.getEditText(),
						(String) MyApplication.gainData("pondId_addengines"));
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public AddTangkouView addSetSwitchView(String name, String hinttxt) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		AddTangkouView switchSingleView = new AddTangkouView(getContext());
		switchSingleView.setNameText(name);
		switchSingleView.setHint(hinttxt);
		// switchSingleView.switchButton.setId(id);
		addtangkouengines_ll.addView(switchSingleView, params);
		return switchSingleView;
	}

	public AddTangkouView_2 addSetSwitchView2(String name, List<String> dataset) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		AddTangkouView_2 switchSingleView = new AddTangkouView_2(getContext());
		switchSingleView.setNameText(name);
		switchSingleView.getNiceSpinner_view().attachDataSource(dataset);
		// switchSingleView.switchButton.setId(id);
		addtangkouengines_ll.addView(switchSingleView, params);
		return switchSingleView;
	}

	@Override
	public boolean onFail(int status, Object msg) {
		ToolAlert.dialog(getContext(), "提示", (String) msg, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		ToolAlert.dialog(getContext(), "提示", (String) msg, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		return false;
	}
}
