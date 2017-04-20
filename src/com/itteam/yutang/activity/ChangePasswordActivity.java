package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.service.ChangePasswordModel;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.util.PreferencesUtils;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.EditTextWithDeleteButton;
import com.yzh.android.view.FButton;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class ChangePasswordActivity extends BaseActivity implements ISimpleListener, View.OnClickListener {
	EditTextWithDeleteButton editTextWithDeleteButton, editTextWithDeleteButton2, editTextWithDeleteButton3;
	ChangePasswordModel model;
	FButton submit;

	@Override
	public int bindLayout() {
		return R.layout.activity_changepassword;
	}

	@Override
	public void initView(View view) {
		editTextWithDeleteButton = ViewFindUtils.find(view, R.id.editTextWithDeleteButton);
		editTextWithDeleteButton2 = ViewFindUtils.find(view, R.id.editTextWithDeleteButton2);
		editTextWithDeleteButton3 = ViewFindUtils.find(view, R.id.editTextWithDeleteButton3);
		submit = ViewFindUtils.find(view, R.id.submit_btn);
	}

	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle = "修改密码";
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);

		model = new ChangePasswordModel(mContext);
		model.addDatatCenterListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submit_btn:
			if (!editTextWithDeleteButton2.getText().equals(editTextWithDeleteButton3.getText())) {
				ToolAlert.toastLong("修改密码不一致");
				return;
			}
			if (editTextWithDeleteButton.getText().equals("") || editTextWithDeleteButton2.getText().equals("")) {
				ToolAlert.toastLong("填写信息完整");
				return;
			} else {
				ToolAlert.loading(getContext(), "正在上传后台中.....");
				model.UpdatePassword(editTextWithDeleteButton.getText(), editTextWithDeleteButton2.getText(),
						PreferencesUtils.getString(getContext(), "userId"));
			}
			break;

		default:
			break;
		}
	}
}
