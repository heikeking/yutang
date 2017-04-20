package com.itteam.yutang.activity;

import java.util.ArrayList;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.TabEntity_txt;
import com.itteam.yutang.DevicFaulteActivity;
import com.itteam.yutang.R;
import com.yzh.android.MyApplication;
import com.yzh.android.common.ActionBarManager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class DataCenterActivity extends FragmentActivity {
	private CommonTabLayout mTabLayout_3;
	private String[] mTitles = {"数据中心", "控制中心","设备故障"};
	private ArrayList<Fragment> mFragments = new ArrayList<>();
	private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datacenter);
		initTextView();
		doBusiness();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//super.onSaveInstanceState(outState);
	}

	private void initTextView() {
		mTabLayout_3 = (CommonTabLayout)findViewById(R.id.tl_3);
		for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity_txt(mTitles[i]));
        }
		mFragments.add(new DataCenterFragment());
		mFragments.add(new ControlCenterFragment());
		mFragments.add(new DevicFaultFragment());
		
	}

	private void doBusiness() {
		
		String strCenterTitle = getResources().getString(R.string.engine_center);
		ActionBarManager.initBackTitle(this, getActionBar(), strCenterTitle);
		mTabLayout_3.setTabData(mTabEntities, this, R.id.fl_change, mFragments);
		mTabLayout_3.setOnTabSelectListener(new OnTabSelectListener() {
	         @Override
	         public void onTabSelect(int position) {
	        	 
	         }

	         @Override
	         public void onTabReselect(int position) {
	         }
	     });
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				MyApplication.removeData("deviceId");
				MyApplication.removeData("groupId");
				MyApplication.removeData("pondId");
				finish();
				//关闭窗体动画显示  
//			    overridePendingTransition(R.anim.activity_close,R.anim.alpha_out);
			    overridePendingTransition(0, R.anim.base_slide_right_out);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
