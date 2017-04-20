package com.itteam.yutang.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.service.CalAndSyncDataModel;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.CalandsyncView;
import com.yzh.android.view.FButton;
import com.yzh.android.view.SetEnginesView;

import android.content.Context;
import android.content.DialogInterface;
import android.provider.CalendarContract.Calendars;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class CalAndSyncDataActivity extends BaseActivity implements OnClickListener, ISimpleListener {
	TextView enginesresult, currenttime;
	FButton startsync_time, startsync;
	CalAndSyncDataModel model;
	LinearLayout contents;
	CalandsyncView calandsyncPH4,calandsyncPH6,calandsyncPH9;
	final int calandsyncPH4_id = View.generateViewId();
	final int calandsyncPH6_id = View.generateViewId();
	final int calandsyncPH9_id = View.generateViewId();
	@Override
	public int bindLayout() {
		return R.layout.activity_calandsync;
	}

	@Override
	public void initView(View view) {
		enginesresult = ViewFindUtils.find(view, R.id.enginesresult);
		currenttime = ViewFindUtils.find(view, R.id.currenttime);
		startsync = ViewFindUtils.find(view, R.id.startsync1);
		startsync_time = ViewFindUtils.find(view, R.id.startsync_time);
		contents =ViewFindUtils.find(view, R.id.contents);
	}

	@Override
	public void doBusiness(Context mContext) {

		model = new CalAndSyncDataModel(mContext);
		model.addDatatCenterListener(this);
		String strCenterTitle = getResources().getString(R.string.calandsyncengine);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		startsync.setOnClickListener(this);
		startsync_time.setOnClickListener(this);
	   // calandsyncPH4 = addCalandsyncView("校准PH4.0",calandsyncPH4_id);
		//calandsyncPH6 =addCalandsyncView("校准PH6.86",calandsyncPH6_id);
		//calandsyncPH9 =addCalandsyncView("校准PH9.18",calandsyncPH9_id);
		currenttime.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		
		//calandsyncPH4.startsync1.setOnClickListener(this);
		//calandsyncPH6.startsync1.setOnClickListener(this);
		//calandsyncPH9.startsync1.setOnClickListener(this);
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startsync1:
			ToolAlert.loading(getContext(), "后台获取中......",false);
			model.calibrationEquipment("5");
			break;
		case R.id.startsync_time:
			ToolAlert.loading(getContext(), "后台获取中......",false);
			model.calibrationTime();
			break;
		default:
			break;
		}
		if (v.getId()==calandsyncPH4_id) {
			ToolAlert.loading(getContext(), "后台获取中......",false);
			model.calibrationEquipment("0");
		}
		if (v.getId()==calandsyncPH6_id) {
			ToolAlert.loading(getContext(), "后台获取中......",false);
			model.calibrationEquipment("1");
		}
		if (v.getId()==calandsyncPH9_id) {
			ToolAlert.loading(getContext(), "后台获取中......",false);
			model.calibrationEquipment("2");
		}
	}

	@Override
	public boolean onFail(int status, Object msg) {
		if (status==200) {
			ToolAlert.dialog(getContext(), "提示", "操作成功", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		}else{
			ToolAlert.dialog(getContext(), "提示", "操作失败", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		}
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		switch (status) {
		case 1:
			ToolAlert.dialog(getContext(), "提示", (String) msg, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			enginesresult.setTextColor(getResources().getColor(R.color.main_color));
			enginesresult.setText("校准结果:已校准");
			break;
		case 2:
			ToolAlert.dialog(getContext(), "提示", (String) msg, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			currenttime.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
			break;

		default:
			break;
		}
		return false;
	}
	public CalandsyncView addCalandsyncView(String name,int id) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		CalandsyncView mEnginesView = new CalandsyncView(getContext());
		mEnginesView.setName(name);
		mEnginesView.startsync1.setId(id);
		contents.addView(mEnginesView, params);
		return mEnginesView;
	}
}
