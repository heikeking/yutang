package com.itteam.yutang.activity;

import java.util.List;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.bean.PondSetUpListBean;
import com.itteam.yutang.service.AddTangkouModel;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.AddTangkouView;
import com.yzh.android.view.AddTangkouView_2;
import com.yzh.android.view.SwitchSingleView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class AddTangkouActivity extends BaseActivity implements ISimpleListener{
	AddTangkouModel model;
	LinearLayout addtangkou_ll;
	AddTangkouView pondName,pondArea, pondDepth, pondDensity, pondUsage,pondO2_power,pondRental;
	AddTangkouView_2 pondThickness,pondO2_add,pondWaterSupplies,pondTop,pondBottom,pondDischargeMode;
	PondSetUpListBean bean;
	@Override
	public int bindLayout() {
		return R.layout.activvity_addtangkou;
	}

	@Override
	public void initView(View view) {
		addtangkou_ll =ViewFindUtils.find(view, R.id.addtangkou_ll);
	}

	@Override
	public void doBusiness(Context mContext) {
		model = new AddTangkouModel(getContext());
		model.addDatatCenterListener(this);
		String strCenterTitle = getResources().getString(R.string.add_tangkou);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		model.getPondSetUpList();
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
			String pondId ="";
			Intent intent=new Intent();
	        intent.putExtra("pondId", pondId);
	        Log.i(TAG, "pondId:"+pondId);
			setResult(1, intent);
			finish();
			// 关闭窗体动画显示
			// overridePendingTransition(R.anim.activity_close,R.anim.alpha_out);
			overridePendingTransition(0, R.anim.base_slide_right_out);
			break;
		case R.id.save:
			ToolAlert.loading(getContext(), "后台获取中......");
			model.getPondInfo(pondName.getEditText(), pondArea.getEditText(), pondDepth.getEditText(), pondDensity.getEditText(), pondUsage.getEditText(),
					pondO2_power.getEditText(), pondRental.getEditText(),bean.O2_addList.get((pondO2_add.getSelectedIndex())).pondSetUpId, 
					bean.waterSuppliesList.get((pondWaterSupplies.getSelectedIndex())).pondSetUpId,bean.pondTopList.get(pondTop.getSelectedIndex()).pondSetUpId, 
					bean.pondBottomList.get(pondBottom.getSelectedIndex()).pondSetUpId, bean.dischargeModeList.get(pondDischargeMode.getSelectedIndex()).pondSetUpId,
					bean.pondThicknessList.get(pondThickness.getSelectedIndex()).pondSetUpId);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onFail(int status, Object msg) {
		if (status ==2) {
			ToolAlert.dialog(getContext(),"提示",(String) msg,new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
		}
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		if (status==1) {
			bean =(PondSetUpListBean)msg;
			pondName = addSetSwitchView("塘口名称:", "请输入塘口名称或编号");
			pondArea =addSetSwitchView("塘口面积:", "请输入塘口面积(亩)");
			pondDepth =addSetSwitchView("塘口深度:", "请输入塘口水深(米)");
			//addSetSwitchView("塘口品种:", "请输入养殖品种(多种用,分开)");
			pondDensity =addSetSwitchView("投放密度:", "请输入投放密度(亩/尾)");
			pondUsage =addSetSwitchView("池塘年龄:", "请输入池塘年龄(年)");
			pondThickness = addSetSwitchView2("池塘厚度:", model.spellList(bean.pondThicknessList));
			pondO2_add = addSetSwitchView2("增氧方式:", model.spellList(bean.O2_addList));
			pondWaterSupplies =addSetSwitchView2("池塘水源:", model.spellList(bean.waterSuppliesList));
			pondTop = addSetSwitchView2("池塘池顶:", model.spellList(bean.pondTopList));
			pondBottom = addSetSwitchView2("池塘池底:", model.spellList(bean.pondBottomList));
			pondDischargeMode = addSetSwitchView2("排污方式:", model.spellList(bean.dischargeModeList));
			pondO2_power=addSetSwitchView("增氧功率:", "请输入增氧机总功率(kw)");
			pondRental = addSetSwitchView("池塘租金:", "请输入池塘租金(RMB)");	
		}
		if (status==2) {
			String pondId = (String) msg;
			Intent intent=new Intent();
	        intent.putExtra("pondId", pondId);
	        Log.i(TAG, "pondId:"+pondId);
			setResult(1, intent);
			finish();
		}
		return false;
	}
	public AddTangkouView addSetSwitchView(String name, String hinttxt) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		AddTangkouView switchSingleView = new AddTangkouView(getContext());
		switchSingleView.setNameText(name);
		switchSingleView.setHint(hinttxt);
		//switchSingleView.switchButton.setId(id);
		addtangkou_ll.addView(switchSingleView, params);
		return switchSingleView;
	}
	public AddTangkouView_2 addSetSwitchView2(String name,List<String> dataset) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		AddTangkouView_2 switchSingleView = new AddTangkouView_2(getContext());
		switchSingleView.setNameText(name);
		switchSingleView.getNiceSpinner_view().attachDataSource(dataset);
		//switchSingleView.switchButton.setId(id);
		addtangkou_ll.addView(switchSingleView, params);
		return switchSingleView;
	}
	@Override
	public void onBackPressed() {
		String pondId ="";
		Intent intent=new Intent();
        intent.putExtra("pondId", pondId);
		setResult(1, intent);
		finish();
		super.onBackPressed();
	}
}
