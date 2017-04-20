package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ILoginListener;
import com.itteam.yutang.bean.UserBean;
import com.itteam.yutang.contants.Contants;
import com.itteam.yutang.manager.ModelManager;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.view.FButton;
import com.yzh.android.view.SimpleEditView;
import com.yzh.android.view.SmartImageView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

public class LoginActivity extends BaseActivity implements ILoginListener, OnClickListener,OnCheckedChangeListener {
	private ModelManager mManager;
	private FButton login_btn;
	private FButton tv_register_btn;
	private LinearLayout buttons;
	private SmartImageView	title_img;
	private SimpleEditView account;
	private SimpleEditView password;
	private SmartImageView mSmartImageView;
	private CheckBox rem_status;
	private boolean isLogined =true;
	@Override
	public int bindLayout() {
		return R.layout.activity_login;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void initView(View view) {
		// actionbar隐藏
		getActionBar().hide();
		login_btn = (FButton) findViewById(R.id.login);
		tv_register_btn= (FButton) findViewById(R.id.regiter_btn);
		buttons = (LinearLayout) findViewById(R.id.account_li);
		title_img = (SmartImageView) findViewById(R.id.title_img_rl);
		rem_status =(CheckBox)findViewById(R.id.rem_status);
		title_img.setRatio(2.5F);
		title_img.setImageDrawable(getResources().getDrawable(R.drawable.appicon_icon));
		account = addButton("请输入用户名", R.drawable.accounticon);
		password = addButton("请输入密码", R.drawable.lockicon);
		//addImage();
	}

	@Override
	public void doBusiness(Context mContext) {
		rem_status.setChecked(true);
		rem_status.setOnCheckedChangeListener(this);
		login_btn.setOnClickListener(this);
		tv_register_btn.setOnClickListener(this);
		mManager = new ModelManager(this);
		mManager.addEventListener(this);
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void handleButton(int event, Object msg) {
		switch (event) {
		case Contants.LOGINSUCCEED:
			// 跳转页面
			//if (isLogined) {
				UserBean userbean =(UserBean)msg;
				Log.i("1234", userbean.userName+"   "+userbean.account+"  "+userbean.password+"  "+userbean.userId+"  "+isLogined);
				mManager.saveAccount(userbean.userName,userbean.account, userbean.password, userbean.userId,isLogined);
			//}
			
			getOperation().forward(MainActivity.class);
			finish();
			break;
		case Contants.LOGINFAIL:
			// 跳转页面
			ToolAlert.toastShort((String)msg);
			break;
		case Contants.REGISTERFAIL:

			break;
		case Contants.REGISTERSUCCEED:

			break;

		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			/**
			 * accout输入框的账号 password输入的密码
			 */
			ToolAlert.loading(getContext(), "正在登陆中....");
			mManager.Login(account.getText().toString(), password.getText().toString());
			break;
		case R.id.regiter_btn:
			getOperation().forward(RegisterActivity.class);
			break;

		default:
			break;
		}
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		case R.id.rem_status:
			 if(isChecked){ 
				 isLogined =isChecked;
             }else{ 
            	 isLogined =isChecked;
             } 
			break;

		default:
			break;
		}
	}
	private SimpleEditView addButton(String txt, int icon) {
		if (txt == null || txt.isEmpty()) {
			return null;
		}
		LinearLayout.LayoutParams p;
		SimpleEditView bv = new SimpleEditView(this);
		p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		Log.i("Cache", txt);
		bv.setInfo(txt, getResources().getDrawable(icon));
		buttons.addView(bv, p);
		return bv;
	}

}
