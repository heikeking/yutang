package com.yzh.android.base;

import android.app.Service;

/**
 *  android 系统中的四大组件之一Service基类
 * @author yzh
 * @version 1.0
 *
 */
public abstract class BaseService extends Service {

	/**日志输出标志**/
	protected final String TAG = this.getClass().getSimpleName();
}
