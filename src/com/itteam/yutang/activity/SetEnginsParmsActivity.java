package com.itteam.yutang.activity;

import java.util.Calendar;

import com.itteam.yutang.R;
import com.itteam.yutang.base.IParamsListener;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.bean.PondConfigBean;
import com.itteam.yutang.service.SetEnginesParmsModel;
import com.itteam.yutang.service.SetEnginesParmsModel.Tipsinfo;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.BtnSwitchView;
import com.yzh.android.view.SetEnginesView;
import com.yzh.android.view.SetenginesTimeView;
import com.yzh.android.view.SwitchSingleView;

import android.R.bool;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class SetEnginsParmsActivity extends BaseActivity implements android.widget.RadioButton.OnCheckedChangeListener,
		OnClickListener, ISimpleListener, android.widget.RadioGroup.OnCheckedChangeListener, IParamsListener {
	LinearLayout content;
	SetEnginesView tmp_max, tmp_min, tmp_r, ph_max, ph_min, ph_r, o2_max, o2_min, o2_r;
	//SwitchSingleView feed_switch, o2_switch,dingshi;
	BtnSwitchView o2_switch_auto, feed_switch_auto,feed_switch, o2_switch,o2_switch2,o2_switch3,dingshi;
	SetenginesTimeView o2_am_b, o2_am_e, o2_pm_b, o2_pm_e, f_am_b, f_am_e, f_pm_b, f_pm_e;
	TextView update_fromengins, save_toengins;
	SetEnginesParmsModel model;
	final int RadioGroupid_o2 = View.generateViewId();
	final int RadioGroupid_feed = View.generateViewId();
	final int RadioButtonid_o2 = View.generateViewId();
	final int RadioButtonid_o2_2 = View.generateViewId();
	final int RadioButtonid_o2_3 = View.generateViewId();
	final int RadioButtonid_feed = View.generateViewId();
	final int RadioButtonid_dingshi = View.generateViewId();
	boolean flag = false;
	public static boolean ischecked_o2 = true;
	public static boolean ischecked_o2_2 = true;
	public static boolean ischecked_o2_3 = true;
	public static boolean ischecked_feed = true;
	public static boolean ischecked_auto_o2 = true;
	public static boolean ischecked_auto_feed = true;
	public static boolean ischecked_dingshi = true;

	@Override
	public int bindLayout() {
		return R.layout.activity_setenginsparms;
	}

	@Override
	public void initView(View view) {
		content = ViewFindUtils.find(view, R.id.content);
		update_fromengins = ViewFindUtils.find(view, R.id.update_fromengins);
		save_toengins = ViewFindUtils.find(view, R.id.save_toengins);
	}

	@Override
	public void doBusiness(Context mContext) {
		flag = false;
		String strCenterTitle = getResources().getString(R.string.setengineparms);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		model = new SetEnginesParmsModel(this);
		model.addDatatCenterListener(this);
		model.addParmsListener(this);
		ToolAlert.loading(getContext(), "后台获取中......");
		model.getPondConfig();
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
		case R.id.update_fromengins:
			ToolAlert.loading(getContext(), "后台获取中......");
			model.readControllerConfig();
			break;
		case R.id.save_toengins:
			Tipsinfo tipsinfo = model.judgeStatus(tmp_max.getEditTextString(),
					o2_max.getEditTextString());
			Tipsinfo tipsinfo2 = model.judgeStatus(tmp_min.getEditTextString(), 
					o2_min.getEditTextString());
			if (tipsinfo.code == -1 ) {
				ToolAlert.dialog(getContext(), "提示", tipsinfo.msg, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
			} 
			if (tipsinfo2.code == -1) {
				ToolAlert.dialog(getContext(), "提示", tipsinfo2.msg, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
			}else {
				Log.i("Cache", "yunxinglemei");
				ToolAlert.loading(getContext(), "后台保存中......");
				model.writeCommandThreshold(tmp_max.getEditTextString(), tmp_min.getEditTextString(),
						tmp_r.getEditTextString(),"0", "0",
						"0", o2_max.getEditTextString(), o2_min.getEditTextString(),
						o2_r.getEditTextString(), o2_am_b.getTextViewString(), o2_am_e.getTextViewString(),
						o2_pm_b.getTextViewString(), o2_pm_e.getTextViewString(), "00:00",
						"00:00","00:00", "00:00");
			}
			break;

		default:
			break;
		}
	}

	public void AfterGetDataToshow(String o2_switch_status, String feed_switch_status) {
		tmp_max = addSetEnginesView("温度上限", "℃");
		tmp_min = addSetEnginesView("温度下限", "℃");
		o2_max = addSetEnginesView("溶氧上限", "mg/L");
		o2_min = addSetEnginesView("溶氧下限", "mg/L");
		//ph_max = addSetEnginesView("PH上限", "");
		//ph_min = addSetEnginesView("PH下限", "");
		tmp_r = addSetEnginesView("温度修正值", "℃");
		o2_r = addSetEnginesView("溶氧修正值", "mg/L");
		//ph_r = addSetEnginesView("PH修正值", "");
		o2_am_b = addSetenginesTimeView("午间补氧", "时:分");
		o2_am_e = addSetenginesTimeView("补氧结束", "时:分");
		o2_pm_b = addSetenginesTimeView("夜间补氧", "时:分");
		o2_pm_e = addSetenginesTimeView("补氧结束", "时:分");
		//f_am_b = addSetenginesTimeView("午间投料机", "时:分");
		//f_am_e = addSetenginesTimeView("投料结束", "时:分");
		//f_pm_b = addSetenginesTimeView("夜间投料", "时:分");
		//f_pm_e = addSetenginesTimeView("投料结束", "时:分");
		//手动 ==开 左1
		//第一个为 定时开关，第四个为自动与否，第八个表示开关
		//0x80   0x10  0x01
		if ((Integer.valueOf(o2_switch_status)&0x10)!=0&&(Integer.valueOf(o2_switch_status)&0x02)!=0) {
			o2_switch = addBtnSwitchView("增氧泵1号状态", true,false, RadioButtonid_o2);
		}
		if ((Integer.valueOf(o2_switch_status)&0x10)!=0&&(Integer.valueOf(o2_switch_status)&0x02)==0) {
			o2_switch = addBtnSwitchView("增氧泵1号状态", true,false,  RadioButtonid_o2);
		}
		if ((Integer.valueOf(o2_switch_status)&0x10)==0&&(Integer.valueOf(o2_switch_status)&0x02)!=0) {
			o2_switch = addBtnSwitchView("增氧泵1号状态", false,  true, RadioButtonid_o2);
		}
		if ((Integer.valueOf(o2_switch_status)&0x10)==0&&(Integer.valueOf(o2_switch_status)&0x02)==0) {
			o2_switch = addBtnSwitchView("增氧泵1号状态", false,  true, RadioButtonid_o2);
		}
		int o2Int =Integer.valueOf(o2_switch_status);
		if ((o2Int&0x04)!=0) {
			o2_switch2= addBtnSwitchView("增氧泵2号状态", true,false, RadioButtonid_o2_2);
		}else{
			o2_switch2= addBtnSwitchView("增氧泵2号状态", false,true, RadioButtonid_o2_2);
		}
		if ((o2Int&0x08)!=0) {
			o2_switch3= addBtnSwitchView("增氧泵3号状态", true,false, RadioButtonid_o2_3);
		}else{
			o2_switch3= addBtnSwitchView("增氧泵3号状态", false,true, RadioButtonid_o2_3);
		}
		if ((Integer.valueOf(o2_switch_status)&0x80)!=0){
			dingshi = addBtnSwitchView("氧气机定时", true,false, RadioButtonid_dingshi);
		}else{
			dingshi = addBtnSwitchView("氧气机定时", false,true, RadioButtonid_dingshi);
		}
		if ((Integer.valueOf(o2_switch_status)&0x10)!=0&&(Integer.valueOf(o2_switch_status)&0x02)!=0) {
			o2_switch_auto = addBtnSwitchView("增氧泵模式", false, true, RadioGroupid_o2);
		}
		if ((Integer.valueOf(o2_switch_status)&0x10)!=0&&(Integer.valueOf(o2_switch_status)&0x02)==0) {
			o2_switch_auto = addBtnSwitchView("增氧泵模式", false, true, RadioGroupid_o2);
		}
		if ((Integer.valueOf(o2_switch_status)&0x10)==0&&(Integer.valueOf(o2_switch_status)&0x02)!=0) {
			o2_switch_auto = addBtnSwitchView("增氧泵模式", true, false, RadioGroupid_o2);
		}
		if ((Integer.valueOf(o2_switch_status)&0x10)==0&&(Integer.valueOf(o2_switch_status)&0x02)==0) {
			o2_switch_auto = addBtnSwitchView("增氧泵模式", true, false, RadioGroupid_o2);
		}
		/* if ((Integer.valueOf(feed_switch_status)&0x10)!=0&&(Integer.valueOf(feed_switch_status)&0x01)!=0) {
			feed_switch_auto = addBtnSwitchView("投料机定时", true, false, RadioGroupid_feed);
		}
		if ((Integer.valueOf(feed_switch_status)&0x10)!=0&&(Integer.valueOf(feed_switch_status)&0x01)==0) {
			feed_switch = addBtnSwitchView("投料机状态", false,true, RadioButtonid_feed);
			feed_switch_auto = addBtnSwitchView("投料机定时", true, false, RadioGroupid_feed);
		}
		if ((Integer.valueOf(feed_switch_status)&0x10)==0&&(Integer.valueOf(feed_switch_status)&0x01)!=0) {
			feed_switch = addBtnSwitchView("投料机状态", true,false, RadioButtonid_feed);
			feed_switch_auto = addBtnSwitchView("投料机定时", false, true, RadioGroupid_feed);
		}
		if ((Integer.valueOf(feed_switch_status)&0x10)==0&&(Integer.valueOf(feed_switch_status)&0x01)==0) {
			feed_switch = addBtnSwitchView("投料机状态", false,true, RadioButtonid_feed);
			feed_switch_auto = addBtnSwitchView("投料机定时",  false, true, RadioGroupid_feed);
		}
		*/
		
		
		dingshi.rb_shoudong.setText("开");
		dingshi.rb_zidong.setText("关");
		//feed_switch.rb_shoudong.setText("开");
		//feed_switch.rb_zidong.setText("关");
		o2_switch.rb_shoudong.setText("开");
		o2_switch.rb_zidong.setText("关");
		o2_switch2.rb_shoudong.setText("开");
		o2_switch2.rb_zidong.setText("关");
		o2_switch3.rb_shoudong.setText("开");
		o2_switch3.rb_zidong.setText("关");
		//feed_switch_auto.rb_shoudong.setText("开");
		//feed_switch_auto.rb_zidong.setText("关");
		Log.i("1234", "定时开关："+(Integer.valueOf(o2_switch_status)&0x80)+"");
		// 设置监听事件
		setTime(o2_am_b);
		setTime(o2_am_e);
		setTime(o2_pm_b);
		setTime(o2_pm_e);
		//setTime(f_am_b);
		//setTime(f_am_e);
		//setTime(f_pm_b);
		//setTime(f_pm_e);
		dingshi.fragment_settings_rg.setOnCheckedChangeListener(this);
		//feed_switch.fragment_settings_rg.setOnCheckedChangeListener(this);
		o2_switch.fragment_settings_rg.setOnCheckedChangeListener(this);
		o2_switch2.fragment_settings_rg.setOnCheckedChangeListener(this);
		o2_switch3.fragment_settings_rg.setOnCheckedChangeListener(this);
		o2_switch_auto.fragment_settings_rg.setOnCheckedChangeListener(this);
		//feed_switch_auto.fragment_settings_rg.setOnCheckedChangeListener(this);
		save_toengins.setOnClickListener(this);
		update_fromengins.setOnClickListener(this);
	}
	/**
	 * @param enginesView
	 */
	public void setTime(SetenginesTimeView enginesView) {
		final TextView et1 = enginesView.getEditText();
		et1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new TimePickerDialog(getContext(), new OnTimeSetListener() {

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						String result = "";
						if (hourOfDay < 10) {
							result = "0" + hourOfDay + result;
						} else {
							result = hourOfDay + result;
						}
						if (minute < 10) {
							result = result + ":0" + minute;
						} else {
							result = result + ":" + minute;
						}
						et1.setText(result);
					}
				}, Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), true)
						.show();

			}
		});
	}

	@Override
	public boolean onFail(int status, Object msg) {
		if (status == 1) {
			// ToolAlert.closeLoading();
			//msg ="操作成功";
			ToolAlert.dialog(getContext(), "提示", (String) msg, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		}
		if (status == 2) {
			//msg ="操作成功";
			ToolAlert.toastLong((String) msg);
			// ToolAlert.closeLoading();
		}
		if (status == 3) {
			//AfterFailGetDataToshow("", "");
			update_fromengins.setOnClickListener(this);
			ToolAlert.dialog(getContext(), "提示", "获取数据失败",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		}
		if (status == 4) {
			new Handler().postDelayed(new Runnable() {
				public void run() {
			model.getPondConfig();
				}
			}, 2000);
			//ToolAlert.toastLong((String) msg);
			//ToolAlert.closeLoading();
		}
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		switch (status) {
		case 1:
			Log.i("Cache", "yunxinglemei_success");
			ToolAlert.dialog(getContext(), "提示", (String) msg, new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			break;
		case 2:
			ToolAlert.toastLong((String) msg);
			// Log.i(TAG, (String)msg);
			//ToolAlert.closeLoading();
			break;
		case 3:
			//ToolAlert.closeLoading();
			PondConfigBean bean = (PondConfigBean) msg;
			if (!flag) {
				AfterGetDataToshow(bean.o2_switch, bean.feed_switch);
				flag = true;
			} else {
				SetEnginsParmsActivity.ischecked_o2 = false;
				SetEnginsParmsActivity.ischecked_o2_2 = false;
				SetEnginsParmsActivity.ischecked_o2_3 = false;
				SetEnginsParmsActivity.ischecked_feed = false;
				SetEnginsParmsActivity.ischecked_auto_o2 = false;
				SetEnginsParmsActivity.ischecked_auto_feed = false;
				SetEnginsParmsActivity.ischecked_dingshi = false;
				//第一个为 定时开关，第四个为自动与否，第八个表示开关
				//0x80   0x10  0x01
				if ((Integer.valueOf(bean.o2_switch)&0x10)!=0&&(Integer.valueOf(bean.o2_switch)&0x02)!=0) {
					o2_switch.rb_zidong.setChecked(false);
					o2_switch.rb_shoudong.setChecked(true);
					o2_switch_auto.rb_shoudong.setChecked(false);
					o2_switch_auto.rb_zidong.setChecked(true);
				}
				if ((Integer.valueOf(bean.o2_switch)&0x10)!=0&&(Integer.valueOf(bean.o2_switch)&0x02)==0) {
					o2_switch.rb_zidong.setChecked(true);
					o2_switch.rb_shoudong.setChecked(false);
					o2_switch_auto.rb_shoudong.setChecked(false);
					o2_switch_auto.rb_zidong.setChecked(true);
				}
				if ((Integer.valueOf(bean.o2_switch)&0x10)==0&&(Integer.valueOf(bean.o2_switch)&0x02)!=0) {
					o2_switch.rb_zidong.setChecked(false);
					o2_switch.rb_shoudong.setChecked(true);
					o2_switch_auto.rb_shoudong.setChecked(true);
					o2_switch_auto.rb_zidong.setChecked(false);
				}
				if ((Integer.valueOf(bean.o2_switch)&0x10)==0&&(Integer.valueOf(bean.o2_switch)&0x02)==0) {
					o2_switch.rb_zidong.setChecked(true);
					o2_switch.rb_shoudong.setChecked(false);
					o2_switch_auto.rb_shoudong.setChecked(true);
					o2_switch_auto.rb_zidong.setChecked(false);
				}
				int o2Int =Integer.valueOf(bean.o2_switch);
				if ((o2Int&0x04)!=0) {
					o2_switch2.rb_zidong.setChecked(false);
					o2_switch2.rb_shoudong.setChecked(true);
				}else{
					o2_switch2.rb_zidong.setChecked(true);
					o2_switch2.rb_shoudong.setChecked(false);
				}
				Log.i("1234", "o2_switch3.you:"+o2_switch3.rb_zidong.isChecked());
				Log.i("1234", "o2_switch3.zuo:"+o2_switch3.rb_shoudong.isChecked());
				if ((o2Int&0x08)!=0) {
					o2_switch3.rb_zidong.setChecked(false);
					o2_switch3.rb_shoudong.setChecked(true);
					Log.i("1234", "o2_switch3.zuo:"+o2_switch3.rb_shoudong.isChecked());
					Log.i("1234", "但是");
				}else{
					o2_switch3.rb_zidong.setChecked(true);
					o2_switch3.rb_shoudong.setChecked(false);
				}
				Log.i("1234", "o2_switch3.you:"+o2_switch3.rb_zidong.isChecked());
				Log.i("1234", "o2_switch3.zuo:"+o2_switch3.rb_shoudong.isChecked());
			/*	if ((Integer.valueOf(bean.feed_switch)&0x10)!=0&&(Integer.valueOf(bean.feed_switch)&0x01)!=0) {
					feed_switch.rb_zidong.setChecked(false);
					feed_switch.rb_shoudong.setChecked(true);
					feed_switch_auto.rb_shoudong.setChecked(true);
					feed_switch_auto.rb_zidong.setChecked(false);
				}//
				if ((Integer.valueOf(bean.feed_switch)&0x10)!=0&&(Integer.valueOf(bean.feed_switch)&0x01)==0) {
					feed_switch.rb_zidong.setChecked(true);
					feed_switch.rb_shoudong.setChecked(false);
					feed_switch_auto.rb_shoudong.setChecked(true);
					feed_switch_auto.rb_zidong.setChecked(false);
				}
				if ((Integer.valueOf(bean.feed_switch)&0x10)==0&&(Integer.valueOf(bean.feed_switch)&0x01)!=0) {
					feed_switch.rb_zidong.setChecked(false);
					feed_switch.rb_shoudong.setChecked(true);
					feed_switch_auto.rb_shoudong.setChecked(false);
					feed_switch_auto.rb_zidong.setChecked(true);
				}
				if ((Integer.valueOf(bean.feed_switch)&0x10)==0&&(Integer.valueOf(bean.feed_switch)&0x01)==0) {
					feed_switch.rb_zidong.setChecked(true);
					feed_switch.rb_shoudong.setChecked(false);
					feed_switch_auto.rb_shoudong.setChecked(false);
					feed_switch_auto.rb_zidong.setChecked(true);
				}
				*/
				
				
				if ((Integer.valueOf(bean.o2_switch)&0x80)!=0){
					dingshi.rb_zidong.setChecked(false); 
					dingshi.rb_shoudong.setChecked(true); 
				}else if ((Integer.valueOf(bean.o2_switch)&0x80)==0){
					dingshi.rb_zidong.setChecked(true); 
					dingshi.rb_shoudong.setChecked(false);
				}
			}
			tmp_max.getEditText().setText(bean.tmp_max);
			tmp_min.getEditText().setText(bean.tmp_min);
			tmp_r.getEditText().setText(bean.tmp_rv);
			//ph_max.getEditText().setText(bean.ph_max);
			//ph_min.getEditText().setText(bean.ph_min);
			//ph_r.getEditText().setText(bean.ph_rv);
			o2_max.getEditText().setText(bean.o2_max);
			o2_r.getEditText().setText(bean.o2_rv);
			o2_min.getEditText().setText(bean.o2_min);
			o2_am_b.getEditText().setText(bean.o2_am_begin);
			o2_am_e.getEditText().setText(bean.o2_am_end);
			o2_pm_b.getEditText().setText(bean.o2_pm_begin);
			o2_pm_e.getEditText().setText(bean.o2_pm_end);
			//f_am_b.getEditText().setText(bean.f_am_begin);
			//f_am_e.getEditText().setText(bean.f_am_end);
			//f_pm_b.getEditText().setText(bean.f_pm_begin);
			//f_pm_e.getEditText().setText(bean.f_pm_end);
			SetEnginsParmsActivity.ischecked_o2 = true;
			SetEnginsParmsActivity.ischecked_o2_2 = true;
			SetEnginsParmsActivity.ischecked_o2_3 = true;
			SetEnginsParmsActivity.ischecked_feed = true;
			SetEnginsParmsActivity.ischecked_auto_o2 = true;
			SetEnginsParmsActivity.ischecked_auto_feed = true;
			SetEnginsParmsActivity.ischecked_dingshi = true;
			break;
		case 4:
			new Handler().postDelayed(new Runnable() {
				public void run() {
			model.getPondConfig();
				}
			}, 2000);
			break;
		default:
			break;
		}
		return false;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		Log.i(TAG, "group id" + checkedId);
		if (group.getId() == RadioGroupid_o2 && ischecked_auto_o2) {
			switch (group.getCheckedRadioButtonId()) {
			case R.id.rb_shoudong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("03", "04", o2_switch_auto.rb_shoudong, null,
								o2_switch_auto.rb_shoudong.isChecked());
					}
				}, 3000);
				break;
			case R.id.rb_zidong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("02", "04", o2_switch_auto.rb_zidong, o2_switch_auto.rb_shoudong,
								o2_switch_auto.rb_zidong.isChecked());
					}
				}, 3000);
				break;
			default:
				break;
			}
			ischecked_auto_o2 = false;
		}
		if (group.getId() == RadioGroupid_feed && ischecked_auto_feed) {
			switch (group.getCheckedRadioButtonId()) {
			case R.id.rb_shoudong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("04", "02", null, null,
								false);
					}
				}, 3000);
				break;
			case R.id.rb_zidong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("04", "03", null, null,
								false);
					}
				}, 3000);
				break;
			default:
				break;
			}
			ischecked_auto_feed = false;
		}
		if (group.getId() == RadioButtonid_feed && ischecked_feed) {
			switch (group.getCheckedRadioButtonId()) {
			case R.id.rb_shoudong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("04", "00", null, null,
								false);
					}
				}, 3000);
				break;
			case R.id.rb_zidong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("04", "01", null, null,
								false);
					}
				}, 3000);
				break;
			default:
				break;
			}
			ischecked_feed = false;
		}
		if (group.getId() == RadioButtonid_o2 && ischecked_o2) {
			switch (group.getCheckedRadioButtonId()) {
			case R.id.rb_shoudong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("07", "04", null, null,
								false);
					}
				}, 3000);
				break;
			case R.id.rb_zidong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("08", "04", null, null,
								false);
					}
				}, 3000);
				break;
			default:
				break;
			}
			ischecked_o2 = false;
		}
		if (group.getId() == RadioButtonid_o2_2 && ischecked_o2_2) {
			switch (group.getCheckedRadioButtonId()) {
			case R.id.rb_shoudong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("09", "04", null, null,
								false);
					}
				}, 3000);
				break;
			case R.id.rb_zidong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("10", "04", null, null,
								false);
					}
				}, 3000);
				break;
			default:
				break;
			}
			ischecked_o2_2 = false;
		}
		if (group.getId() == RadioButtonid_o2_3 && ischecked_o2_3) {
			switch (group.getCheckedRadioButtonId()) {
			case R.id.rb_shoudong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("11", "04", null, null,
								false);
					}
				}, 3000);
				break;
			case R.id.rb_zidong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("12", "04", null, null,
								false);
					}
				}, 3000);
				break;
			default:
				break;
			}
			ischecked_o2_3 = false;
		}
		if (group.getId() == RadioButtonid_dingshi && ischecked_dingshi) {
			switch (group.getCheckedRadioButtonId()) {
			case R.id.rb_shoudong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("05", "04", null, null,
								false);
					}
				}, 3000);
				break;
			case R.id.rb_zidong:
				ToolAlert.loading(getContext(), "正在请求中....", false);
				new Handler().postDelayed(new Runnable() {
					public void run() {
						model.updateChangeUnitType("06", "04", null, null,
								false);
					}
				}, 3000);
				break;
			default:
				break;
			}
			ischecked_dingshi = false;
		}
	}

	//
	@Override
	public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
		Log.i(getClass().getSimpleName(), String.valueOf(isChecked));
		Log.i(getClass().getSimpleName(), "buttonView:" + String.valueOf(buttonView.isFocused()));
//		if (buttonView.getId() == RadioButtonid_feed && ischecked_feed) {
//		ToolAlert.loading(getContext(), "正在请求中....", false);
//		new Handler().postDelayed(new Runnable() {
//			public void run() {
//				if (buttonView.getId() == RadioButtonid_feed && ischecked_feed) {
//					if (isChecked) {
//						model.updateChangeUnitType("04", "00", feed_switch, feed_switch,
//								isChecked);
//					} else {
//						model.updateChangeUnitType("04", "01", feed_switch, feed_switch,
//								isChecked);
//					}
//					ischecked_feed = false;
//				}
//			}
//		}, 3000);
//		}
//		Log.i("Cache", "onFailView-RadioButtonid_o2");
//		if (buttonView.getId() == RadioButtonid_o2 && ischecked_o2) {
//			ToolAlert.loading(getContext(), "正在请求中....", false);
//			new Handler().postDelayed(new Runnable() {
//				public void run() {
//					if (buttonView.getId() == RadioButtonid_o2 && ischecked_o2) {
//						if (isChecked) {
//							model.updateChangeUnitType("00", "04", o2_switch, o2_switch,
//									isChecked);
//						} else {
//							model.updateChangeUnitType("01", "04", o2_switch, o2_switch,
//									isChecked);
//						}
//						ischecked_o2 = false;
//					}
//				}
//			}, 3000);
//		}
//		if (buttonView.getId() == RadioButtonid_dingshi && ischecked_dingshi) {
//			ToolAlert.loading(getContext(), "正在请求中....", false);
//			new Handler().postDelayed(new Runnable() {
//				public void run() {
//					if (buttonView.getId() == RadioButtonid_dingshi && ischecked_dingshi) {
//						if (isChecked) {
//							model.updateChangeUnitType("05", "04", dingshi, dingshi,
//									isChecked);
//						} else {
//							model.updateChangeUnitType("06", "04", dingshi, dingshi,
//									isChecked);
//						}
//						ischecked_dingshi = false;
//					}
//				}
//			}, 3000);
//		}
	}

	public SetEnginesView addSetEnginesView(String name, String name_duliang) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		SetEnginesView mEnginesView = new SetEnginesView(getContext());
		mEnginesView.setName(name);
		mEnginesView.setName_duliang(name_duliang);
		content.addView(mEnginesView, params);
		return mEnginesView;
	}

	public SetenginesTimeView addSetenginesTimeView(String name, String name_duliang) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		SetenginesTimeView mEnginesView = new SetenginesTimeView(getContext());
		mEnginesView.setName(name);
		mEnginesView.setHintText("请选择时间段");
		mEnginesView.setName_duliang(name_duliang);
		content.addView(mEnginesView, params);
		return mEnginesView;
	}

	public SwitchSingleView addSetSwitchView(String name, boolean isSwitched, int id) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		SwitchSingleView switchSingleView = new SwitchSingleView(getContext());
		switchSingleView.setName(name);
		switchSingleView.setSwitchStatus(isSwitched);
		switchSingleView.switchButton.setId(id);
		content.addView(switchSingleView, params);
		return switchSingleView;
	}

	public BtnSwitchView addBtnSwitchView(String name, boolean isSwitched, boolean isAuto, int id) {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		BtnSwitchView btnSwitchView = new BtnSwitchView(getContext());
		btnSwitchView.fragment_settings_rg.setId(id);
		btnSwitchView.setName(name);
		btnSwitchView.setSwitchStatus(isSwitched, isAuto);
		content.addView(btnSwitchView, params);
		return btnSwitchView;
	}

	@Override
	public void onSuccessView(int code, Object msg, View view, View view2, boolean ischeck) {
		if (code == 2) {
			new Handler().postDelayed(new Runnable() {
				public void run() {
			model.readControllerConfig();
				}
			}, 3000);
				}
	}

	@Override
	public void onFailView(int code, Object msg, View view, View view2, boolean ischeck) {
		if (code == 2) {
			new Handler().postDelayed(new Runnable() {
				public void run() {
			model.readControllerConfig();
				}
			}, 3000);
		}
	}
}
