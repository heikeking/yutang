package com.itteam.yutang.activity;

import com.itteam.yutang.R;
import com.itteam.yutang.adapter.HomeCustomELVAdapter;
import com.itteam.yutang.base.IHomeListener;
import com.itteam.yutang.bean.EngineBeaninfo;
import com.itteam.yutang.bean.Response_main;
import com.itteam.yutang.manager.ModelManager;
import com.yzh.android.base.BaseFragmentV4;
import com.yzh.android.base.BaseHandler;
import com.yzh.android.util.PreferencesUtils;

import android.content.Context;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class HomeFragment extends BaseFragmentV4 implements IHomeListener {
	private RelativeLayout home_weather_rl,home_engineinfo_rl;
	private MainHandler uiHandler;
	private ModelManager mManager;
	private ExpandableListView mCustomListView;
	private SwipeRefreshLayout swipeRefreshLayout;
	
	@Override
	public int bindLayout() {
		return R.layout.fragment_home;
	}

	@Override
	public void initView(View view) {
		home_weather_rl = (RelativeLayout) view.findViewById(R.id.home_weather_rl);
		home_engineinfo_rl = (RelativeLayout) view.findViewById(R.id.home_engineinfo_rl);
		mCustomListView =(ExpandableListView) view.findViewById(R.id.mylist);
		swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeLayout);
	}

	@Override
	public void doBusiness(Context mContext) {
		uiHandler = new MainHandler(this);
		mManager = new ModelManager(mContext);
		mManager.addEventHomeListener(this);
		mManager.getEngineInfo(PreferencesUtils.getString(mContext, "userId"));
	}

	@Override
	protected void lazyLoad() {

	}
	@Override
	public void show(boolean isGet, Object content) {
		swipeRefreshLayout.setRefreshing(false);
		if (isGet) {
			Response_main<EngineBeaninfo> info =(Response_main<EngineBeaninfo>)content;
			HomeCustomELVAdapter mHomeAdapter =mManager.BindAdapter(getContext(),R.layout.item_home_status, info.result);
			mCustomListView.setGroupIndicator(null);
			mCustomListView.setAdapter(mHomeAdapter);
			for(int i = 0; i < mHomeAdapter.getGroupCount(); i++){  
				mCustomListView.expandGroup(i);  
			} 
			swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
	            @Override
	            public void onRefresh() {
	            	mManager.getEngineInfo(PreferencesUtils.getString(getContext(), "userId"));
	            }
	        });
			
		}else{
			String msg = (String)content;
		}
	}
	
	static class MainHandler extends BaseHandler<HomeFragment> {
		public MainHandler(HomeFragment mainPage) {
			super(mainPage);
		}

		@Override
		public void onHandleMessage(Message msg, HomeFragment t) {
			switch (msg.what) {
			case 0:
				
				break;
			default:
				break;
			}
		}

	}

}
