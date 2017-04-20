package com.yzh.android;

import com.itteam.yutang.R;
import com.itteam.yutang.activity.LoginActivity;
import com.itteam.yutang.activity.MainActivity;
import com.itteam.yutang.service.WarningService;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.util.PreferencesUtils;

import android.R.bool;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

/**
 * 程序启动界面
 * 
 * @author yzh
 * @version 1.0
 *
 */
public class Launcher extends BaseActivity {
	boolean isLogined = false;
	@Override
	public int bindLayout() {
		return R.layout.activity_launcher;
	}

	@Override
	public void initView(View view) {
		// 添加动画效果
		AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
		animation.setDuration(2000);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				if ((isLogined = PreferencesUtils.getBoolean(getContext(), "isLogined", false))) {
					getOperation().forward(MainActivity.class);
					finish();
				} else {
					// 跳转界面
					getOperation().forward(LoginActivity.class);
					finish();
				}
				// 右往左推出效果
				 overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
				// 转动淡出效果1
				// overridePendingTransition(R.anim.scale_rotate_in,R.anim.alpha_out);
				// 下往上推出效果
				//overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
			}
		});
		view.setAnimation(animation);
	}
	@Override
	public void doBusiness(Context mContext) {
		
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}
}