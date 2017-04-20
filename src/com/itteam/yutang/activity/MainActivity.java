package com.itteam.yutang.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.itteam.yutang.R;
import com.itteam.yutang.service.WarningService;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseHandler;
import com.yzh.android.common.ActionBarManager;
import com.yzh.android.util.PreferencesUtils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends FragmentActivity {
	public static int userId = 0;
	private WarningService msgService; 
	Fragment homeFragment;
	Fragment tangkouFragment;
	Fragment rizhiFragment;
	Fragment moreFragment;
	Intent intent;
	ServiceConnection conn ;
	private static final String TAG = "MainActivity";
	private ArrayList<Fragment> fragmentList;
	private RadioButton view1, view2, view3, view4;
	private RadioButton[] viewArray = new RadioButton[4];
	private int formerIndex = 0;
	private MainHandler uiHandler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initTextView();
		//绑定Service  
		conn = new ServiceConnection() {  
	       @Override  
	        public void onServiceDisconnected(ComponentName name) {  
	              
	        }  
	          
	        @Override  
	        public void onServiceConnected(ComponentName name, IBinder service) {  
	            //返回一个MsgService对象  
	            msgService = ((WarningService.MsgBinder)service).getService();  
	        }  
	    };
        intent = new Intent(this,WarningService.class);  
		bindService(intent, conn, Context.BIND_AUTO_CREATE); 
        startService(intent);
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// super.onSaveInstanceState(outState);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (!PreferencesUtils.getBoolean(this, "isLogined", false)) {
			PreferencesUtils.clearAll(this);
		}
		Log.e("1234", "FragmentActivity---->onDestroy");
		msgService.timer.cancel();
		unbindService(conn);
		stopService(intent);
		super.onDestroy();
	}

	public void initTextView() {
		uiHandler = new MainHandler(this);
		// 将当前Activity压入栈
		MyApplication application = (MyApplication) this.getApplicationContext();
		application.pushTask(new WeakReference<Activity>(this));
		view1 = (RadioButton) findViewById(R.id.home);
		view2 = (RadioButton) findViewById(R.id.tangkou);
		view3 = (RadioButton) findViewById(R.id.rizhi);
		view4 = (RadioButton) findViewById(R.id.more);

		viewArray[0] = view1;
		viewArray[1] = view2;
		viewArray[2] = view3;
		viewArray[3] = view4;

		view1.setOnClickListener(new TxListener(0));
		view2.setOnClickListener(new TxListener(1));
		view3.setOnClickListener(new TxListener(2));
		view4.setOnClickListener(new TxListener(3));

		formerIndex = 0;
		setTabSelection(formerIndex);
		view1.setTextColor(getResources().getColor(R.color.main_color));
	}

	private void setTabSelection(int index) {
		// 开启一个Fragment事务
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			view1.setChecked(true);
			view2.setChecked(false);
			view3.setChecked(false);
			view4.setChecked(false);
			// 初始化带返回按钮的标题栏
			String strCenterTitle = getResources().getString(R.string.home);
			ActionBarManager.initCenterTitle(this, this.getActionBar(), strCenterTitle);
			if (homeFragment == null) {
				// 如果MessageFragment为空，则创建一个并添加到界面上
				homeFragment = new HomeFragment();
				transaction.add(R.id.tab, homeFragment);
			} else {
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(homeFragment);
			}
			break;
		case 1:
			view1.setChecked(false);
			view2.setChecked(true);
			view3.setChecked(false);
			view4.setChecked(false);
			// 初始化带返回按钮的标题栏
			String strCenterTitle2 = getResources().getString(R.string.tangkou);
			ActionBarManager.initCenterTitle(this, this.getActionBar(), strCenterTitle2);
			if (tangkouFragment == null) {
				// 如果ContactsFragment为空，则创建一个并添加到界面上
				tangkouFragment = new TangkouFragment();
				transaction.add(R.id.tab, tangkouFragment, "tangkou");
			} else {
				// 如果ContactsFragment不为空，则直接将它显示出来
				transaction.show(tangkouFragment);
			}
			break;
		case 2:
			// 初始化带返回按钮的标题栏
			String strCenterTitle3 = getResources().getString(R.string.rizhi);
			ActionBarManager.initCenterTitle(this, this.getActionBar(), strCenterTitle3);
			view1.setChecked(false);
			view2.setChecked(false);
			view3.setChecked(true);
			view4.setChecked(false);
			if (rizhiFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				rizhiFragment = new RizhiFragment();
				transaction.add(R.id.tab, rizhiFragment);
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(rizhiFragment);
			}
			break;
		case 3:
			// 初始化带返回按钮的标题栏
			String strCenterTitle4 = getResources().getString(R.string.more);
			ActionBarManager.initCenterTitle(this, this.getActionBar(), strCenterTitle4);
			view1.setChecked(false);
			view2.setChecked(false);
			view3.setChecked(false);
			view4.setChecked(true);
			if (moreFragment == null) {
				// 如果NewsFragment为空，则创建一个并添加到界面上
				moreFragment = new MoreFragment();
				transaction.add(R.id.tab, moreFragment);
			} else {
				// 如果NewsFragment不为空，则直接将它显示出来
				transaction.show(moreFragment);
			}
			break;
		default:
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (homeFragment != null) {
			transaction.hide(homeFragment);
		}
		if (tangkouFragment != null) {
			transaction.hide(tangkouFragment);
		}
		if (rizhiFragment != null) {
			transaction.hide(rizhiFragment);
		}
		if (moreFragment != null) {
			transaction.hide(moreFragment);
		}
	}

	public class TxListener implements View.OnClickListener {
		private int index = 0;

		public TxListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			// mPager.setCurrentItem(index);
			changeTabStyle(index);
			setTabSelection(index);
		}

	}

	private void changeTabStyle(int currentIndex) {
		if (currentIndex != formerIndex) {
			uiHandler.obtainMessage(3, formerIndex, currentIndex).sendToTarget();
			formerIndex = currentIndex;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		// case R.id.takephpoto:
		//
		// break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/* 在这里，我们通过碎片管理器中的Tag，就是每个碎片的名称，来获取对应的fragment */
		Fragment f = getSupportFragmentManager().findFragmentByTag("tangkou");
		/* 然后在碎片中调用重写的onActivityResult方法 */
		f.onActivityResult(requestCode, resultCode, data);
	}

	static class MainHandler extends BaseHandler<MainActivity> {
		public MainHandler(MainActivity mainPage) {
			super(mainPage);
		}

		@Override
		public void onHandleMessage(Message msg, MainActivity t) {
			switch (msg.what) {
			case 3:
				int formerIndex = msg.arg1;
				int currentIndex = msg.arg2;
				t.viewArray[formerIndex].setTextColor(t.getResources().getColor(R.color.tab_text));
				t.viewArray[currentIndex].setTextColor(t.getResources().getColor(R.color.main_color));
				break;
			default:
				break;
			}
		}

	}

}
