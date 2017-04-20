package com.itteam.yutang.bean;

import java.util.List;

public class Response_main<T> {
	public String code;
	public String rows;
	public List<T> result;
}
