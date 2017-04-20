package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseFragmentV4;
import com.yzh.android.util.PreferencesUtils;
import com.yzh.android.util.ViewFindUtils;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class MoreFragment extends BaseFragmentV4 implements View.OnClickListener {
	TextView my_changeinfo, my_feedback, my_aboutsoftware, my_logout;

	@Override
	public int bindLayout() {
		return R.layout.fragment_more;
	}

	@Override
	public void initView(View view) {
		my_changeinfo = ViewFindUtils.find(view, R.id.my_changeinfo);
		my_feedback = ViewFindUtils.find(view, R.id.my_feedback);
		my_aboutsoftware = ViewFindUtils.find(view, R.id.my_aboutsoftware);
		my_logout = ViewFindUtils.find(view, R.id.my_logout);
	}

	@Override
	public void doBusiness(Context mContext) {
		my_changeinfo.setOnClickListener(this);
		my_feedback.setOnClickListener(this);
		my_aboutsoftware.setOnClickListener(this);
		my_logout.setOnClickListener(this);
	}

	@Override
	protected void lazyLoad() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.my_changeinfo:
			getOperation().forward(ChangeInfoActivity.class);
			break;
		case R.id.my_feedback:
			getOperation().forward(FeedBackActivity.class);
			break;
		case R.id.my_aboutsoftware:
			getOperation().forward(AboutSoftwareActivity.class);
			break;
		case R.id.my_logout:
			if (PreferencesUtils.clearAll(getContext())) {
				MyApplication application =(MyApplication)getActivity().getApplicationContext();
				application.removeAll();
				getOperation().forward(LoginActivity.class);
			}
			break;

		default:
			break;
		}
	}
}
