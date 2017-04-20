package com.itteam.yutang.base;

public interface ISimpleListener {
		public boolean onFail(int status,Object msg);
		public boolean onSuccess(int status,Object msg);
}
