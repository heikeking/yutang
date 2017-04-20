package com.yzh.android.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类(V4包)
 * 
 * @author 曾繁添
 * @version 1.0
 *
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment {

	/** 当前Fragment渲染的视图View **/
	private View mContextView = null;
	/** 共通操作 **/
	private Operation mBaseOperation = null;
	/** 日志输出标志 **/
	protected final String TAG = this.getClass().getSimpleName();

	/** Fragment当前状态是否可见 */
	protected boolean isVisible;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG, "BaseFragmentV4-->onAttach()");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "BaseFragmentV4-->onCreate()");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d(TAG, "BaseFragmentV4-->onCreateView()");

		// 渲染视图View(防止切换时重绘View)
		if (null != mContextView) {
			ViewGroup parent = (ViewGroup) mContextView.getParent();
			if (null != parent) {
				parent.removeView(mContextView);
				Log.d(TAG, "BaseFragmentV4-->onCreate()+mContextView");
			}
		} else {
			mContextView = inflater.inflate(bindLayout(), null);
			// 控件初始化
			Log.d(TAG, "BaseFragmentV4-->onNotCreate()");
			initView(mContextView);
			// 实例化共通操作
			mBaseOperation = new Operation(getActivity());
		}
		//fragment的菜单可以生效
		setHasOptionsMenu(true);
		// 业务处理
		doBusiness(getActivity());

		return mContextView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Log.d(TAG, "BaseFragmentV4-->onActivityCreated()");
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		Log.d(TAG, "BaseFragmentV4-->onSaveInstanceState()");
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onStart() {
		Log.d(TAG, "BaseFragmentV4-->onStart()");
		super.onStart();
	}

	@Override
	public void onResume() {
		Log.d(TAG, "BaseFragmentV4-->onResume()");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.d(TAG, "BaseFragmentV4-->onPause()");
		super.onPause();
	}

	@Override
	public void onStop() {
		Log.d(TAG, "BaseFragmentV4-->onStop()");
		super.onStop();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "BaseFragmentV4-->onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.d(TAG, "BaseFragmentV4-->onDetach()");
		super.onDetach();
	}
	@Override
	public void onDestroyView() {
		Log.d(TAG, "BaseFragmentV4-->onDestroyView()");
		super.onDestroyView();
	}

	/**
	 * 获取当前Fragment依附在的Activity
	 * 
	 * @return
	 */
	@Override
	public Activity getContext() {
		return getActivity();
	}

	/**
	 * 获取共通操作机能
	 */
	public Operation getOperation() {
		return this.mBaseOperation;
	}

	@Override
	public int bindLayout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initView(View view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doBusiness(Context mContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}

	/**
	 * 可见
	 */
	protected void onVisible() {
		lazyLoad();
	}

	/**
	 * 不可见
	 */
	protected void onInvisible() {

	}

	/**
	 * 延迟加载 子类必须重写此方法
	 */
	protected abstract void lazyLoad();

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

}
