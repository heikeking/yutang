package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.itteam.yutang.adapter.DataCenterAdapter;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.service.TangkouManageModel;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseActivity;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.CustomListView;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class TangkouManageActivity extends BaseActivity implements ISimpleListener ,OnItemClickListener{
	CustomListView fragment_managetangkou_list;
	TangkouManageModel tangkouManageModel;
	@Override
	public int bindLayout() {
		return R.layout.activity_tangkoumanage;
	}

	@Override
	public void initView(View view) {
		fragment_managetangkou_list =ViewFindUtils.find(view, R.id.fragment_managetangkou_list);
	}

	@Override
	public void doBusiness(Context mContext) {
		String strCenterTitle = getResources().getString(R.string.manage_tangkou);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		tangkouManageModel =new TangkouManageModel(getContext());
		tangkouManageModel.addDatatCenterListener(this);
		fragment_managetangkou_list.setAdapter(new DataCenterAdapter(mContext, R.layout.view_item_datacenter, tangkouManageModel.loadListData()));
		fragment_managetangkou_list.setOnItemClickListener(this);
	}

	@Override
	public void resume() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public boolean onFail(int status, Object msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				MyApplication.removeData("pondId_addengines");
				finish();
				//关闭窗体动画显示  
//			    overridePendingTransition(R.anim.activity_close,R.anim.alpha_out);
			    overridePendingTransition(0, R.anim.base_slide_right_out);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		switch (position) {
		case 0:
			getOperation().forward(TangkouInfoActivity.class);
			break;
		case 1:
			getOperation().forward(ChangeTangkouinfoActivity.class);
			break;
		case 2:
			getOperation().forward(AddEnginesActivity.class);
			break;
		case 3:
			getOperation().forward(DeleteEnginesActivity.class);
			break;

		default:
			break;
		}
	}

}
