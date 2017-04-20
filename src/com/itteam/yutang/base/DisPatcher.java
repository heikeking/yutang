package com.itteam.yutang.base;

import android.view.View;

public class DisPatcher {
	private ILoginListener mListener;
	private IHomeListener mHomeListener;
	private ISimpleListener mSimpleListener;
	private IParamsListener mIParamsListener;
	public void addDatatCenterListener(ISimpleListener listener) {
		mSimpleListener = listener;
	}
	public void addParmsListener(IParamsListener listener) {
		mIParamsListener = listener;
	}
	
	public void handleonSuccessOnparms(int status,Object msg,View view ,View view2,boolean ischeck){
		mIParamsListener.onSuccessView(status, msg, view,view2, ischeck);
	}
	public void handleonFailOnparms(int status,Object msg,View view,View view2, boolean ischeck){
		mIParamsListener.onFailView(status, msg, view,view2, ischeck);
	}
	
	public void handleonSuccess(int status,Object msg){
		mSimpleListener.onSuccess(status,msg);
	}
	public void handleonFail(int status,Object msg){
		mSimpleListener.onFail(status,msg);
	}
	
	public void addEventListener(ILoginListener listener) {
		mListener = listener;
	}
	
	public void addEventHomeListener(IHomeListener listener) {
		mHomeListener = listener;
	}
	
	public void handleButtonSucceed(int event,Object msg) {
		mListener.handleButton(event,msg);
	}
	
	public void handleHomeContentSucceed(boolean isGet,Object content) {
		mHomeListener.show(isGet, content);
	}
}
