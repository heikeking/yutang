package com.itteam.yutang.activity;

import java.util.List;

import com.itteam.yutang.R;
import com.itteam.yutang.adapter.TangkouAdapter;
import com.itteam.yutang.adapter.TangkouAdapter.IOnItemRightClickListener;
import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.bean.PondBean;
import com.itteam.yutang.manager.ModelManager;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseFragmentV4;
import com.yzh.android.tools.ToolAlert;
import com.yzh.android.util.ViewFindUtils;
import com.yzh.android.view.SwipeListView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class TangkouFragment extends BaseFragmentV4 implements ISimpleListener {
	private ModelManager mManager;
	private SwipeListView tangkou_listview;
	private TangkouAdapter adapter;

	@Override
	public int bindLayout() {
		return R.layout.fragment_tangkou;
	}

	@Override
	public void initView(View view) {
		tangkou_listview = ViewFindUtils.find(view, R.id.tangkou_listview);
	}

	@Override
	public void doBusiness(Context mContext) {
		mManager = new ModelManager(getContext());
		mManager.addSimpleListener(this);
		ToolAlert.loading(getContext(), "正在加载中.....");
		mManager.getPondInfo("");
	}

	@Override
	protected void lazyLoad() {

	}

	@Override
	public boolean onFail(int status, Object msg) {
		return false;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_tangkou, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		 super.onActivityResult(requestCode, resultCode, data);
	        switch (requestCode) {
	        case 1:
	            Log.i(TAG, "pondId:");
	            String result;
	            if (!(result=data.getStringExtra("pondId")).equals("")) {
	            	 Log.i(TAG, "pondId:"+result);
	            	ToolAlert.loading(getContext(), "正在加载中.....");
	            	mManager.getPondInfo("");
				}
	            break;

	        default:
	            break;
	        }

	    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.addTangkou:
			// getOperation().forward(AddTangkouActivity.class);
			startActivityForResult(new Intent(getContext(), AddTangkouActivity.class), 1);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		if (status ==1) {
			final List<PondBean> bean = (List<PondBean>) msg;
			Log.i("1234", "size:"+bean.size());
			tangkou_listview.setRightViewWidth(100);
			adapter = new TangkouAdapter(getContext(), R.layout.view_item_frament_tangkou, bean,
					tangkou_listview.getRightViewWidth(), new IOnItemRightClickListener() {

						@Override
						public void onRightClick(View v, int position) {
							mManager.deletePondInfo("", bean.get(position).pondId);
							adapter.remove(position);
						}

						@Override
						public void onSettingsClivk(View v, int position) {
							MyApplication.assignData("pondId_addengines", bean.get(position).pondId);
							Log.i("Cache","pondId:"+(String) MyApplication.gainData("pondId_addengines"));
							getOperation().forward(TangkouManageActivity.class);
						}
					});
			tangkou_listview.setAdapter(adapter);
			return false;
		}
		if (status==2) {
			
		}
		return false;
	}
}
