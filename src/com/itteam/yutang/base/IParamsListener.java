package com.itteam.yutang.base;

import android.view.View;

public interface IParamsListener {
		public void onSuccessView(int code,Object msg,View view,View view2,boolean ischeck);
		public void onFailView(int code,Object msg,View view,View view2,boolean ischeck);
}
