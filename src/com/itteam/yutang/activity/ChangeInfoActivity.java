package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.service.ChangeInfoModel;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.util.PreferencesUtils;
import com.yzh.android.util.ViewFindUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChangeInfoActivity extends BaseActivity implements View.OnClickListener,ISimpleListener {
	RelativeLayout username_rl, sex_rl, phone_rl, changepassword_rl;
	TextView my_changeinfo_username2, my_changeinfo_sex2, my_changeinfo_phone2, my_changeinfo_password2;
	ChangeInfoModel model;
	@Override
	public int bindLayout() {
		return R.layout.activity_changeinfo;
	}

	@Override
	public void initView(View view) {
		username_rl = ViewFindUtils.find(view, R.id.username_rl);
		//sex_rl = ViewFindUtils.find(view, R.id.sex_rl);
		//phone_rl = ViewFindUtils.find(view, R.id.phone_rl);
		changepassword_rl = ViewFindUtils.find(view, R.id.changepassword_rl);
		my_changeinfo_username2 = ViewFindUtils.find(view, R.id.my_changeinfo_username2);
		//my_changeinfo_sex2 = ViewFindUtils.find(view, R.id.my_changeinfo_sex2);
		//my_changeinfo_phone2 = ViewFindUtils.find(view, R.id.my_changeinfo_phone2);
		my_changeinfo_password2 = ViewFindUtils.find(view, R.id.my_changeinfo_password2);
	}

	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle = getResources().getString(R.string.change_info);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		model =new ChangeInfoModel(mContext);
		model.addDatatCenterListener(this);
		if (PreferencesUtils.getString(getContext(), "userName")==null) {
			my_changeinfo_username2.setText("用户名为空");
		}else{
			my_changeinfo_username2.setText(PreferencesUtils.getString(getContext(), "userName"));
		}
		username_rl.setOnClickListener(this);
		//sex_rl.setOnClickListener(this);
		//phone_rl.setOnClickListener(this);
		changepassword_rl.setOnClickListener(this);
		
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
			String userId = PreferencesUtils.getString(getContext(), "userId");
			if (my_changeinfo_username2.getText().equals("")||userId==null) {
				ToolAlert.toastLong("用户名为空");
			}else{
				ToolAlert.loading(getContext(), "正在上传后台......");
				model.UpdateUsername((String)my_changeinfo_username2.getText(), userId);
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.username_rl:
			final EditText et = new EditText(this);
			new AlertDialog.Builder(this).setTitle("请输入用户名").setView(et).setPositiveButton("确定", new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					String input = et.getText().toString();
					my_changeinfo_username2.setText(input);
				}
			}).setNegativeButton("取消", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			}).show();
			break;
//					case R.id.sex_rl:
//			new AlertDialog.Builder(this).setTitle("修改性别").setPositiveButton("男", new OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//					my_changeinfo_sex2.setText("男");
//				}
//			}).setNegativeButton("女", new OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					my_changeinfo_sex2.setText("女");
//				}
//			}).show();
//			break;		
//			case R.id.phone_rl:
//			final EditText et1 = new EditText(this);
//			new AlertDialog.Builder(this).setTitle("请输入手机号").setView(et1).setPositiveButton("确定", new OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//					String input = et1.getText().toString();
//					my_changeinfo_phone2.setText(input);
//				}
//			}).setNegativeButton("取消", new OnClickListener() {
//				
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			}).show();
//			break;
		case R.id.changepassword_rl:
			getOperation().forward(ChangePasswordActivity.class);
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onFail(int status, Object msg) {
		if (status==2) {
			ToolAlert.dialog(getContext(), "提示",(String)msg,new OnClickListener() {
				
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
		if (status==2) {
			ToolAlert.dialog(getContext(), "提示",(String)msg,new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
		}
		return false;
	}

}
