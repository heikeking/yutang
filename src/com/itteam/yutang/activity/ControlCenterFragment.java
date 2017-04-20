package com.itteam.yutang.activity;

import java.util.List;

import com.itteam.yutang.R;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.manager.ModelManager;
import com.yzh.android.base.BaseFragmentV4;
import com.yzh.android.util.ViewFindUtils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ControlCenterFragment extends BaseFragmentV4 implements ISimpleListener, OnItemClickListener {
	private ListView fragment_control_list;
	private ModelManager mManager;
	private List<String> list;

	@Override
	public int bindLayout() {
		return R.layout.fragment_controlcenter;
	}

	@Override
	public void initView(View view) {
		fragment_control_list = ViewFindUtils.find(view, R.id.fragment_control_list);
	}

	@Override
	public void doBusiness(Context mContext) {
		mManager = new ModelManager(getContext());
		mManager.addDatatCenterListener(this);
		fragment_control_list.setAdapter(mManager.loadListData());
		fragment_control_list.setOnItemClickListener(this);
	}

	@Override
	protected void lazyLoad() {

	}

	@Override
	public boolean onFail(int status, Object msg) {
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		switch (position) {
		case 0:
			getOperation().forward(SetEnginsParmsActivity.class);
			break;
		case 1:
			getOperation().forward(CalAndSyncDataActivity.class);
			break;
		case 2:
			getOperation().forward(QiandaoActivity.class);
			break;
		default:
			break;
		}
	}

}
