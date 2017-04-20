package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.util.ViewFindUtils;

import android.content.Context;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FeedBackActivity extends BaseActivity {
	EditText feedback_et;
	@Override
	public int bindLayout() {
		return R.layout.activity_feedback;
	}

	@Override
	public void initView(View view) {
		feedback_et = ViewFindUtils.find(view, R.id.feedback_et);
	}

	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle = getResources().getString(R.string.my_feedback);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
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
			if (feedback_et.getText().toString().equals("")) {
				ToolAlert.toastLong("请输入信息");
			}else{
			ToolAlert.loading(getContext(), "正在上传中......");
			new Handler().postDelayed(new Runnable() {
				public void run() {
					ToolAlert.closeLoading();
				}
			}, 3000);
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
