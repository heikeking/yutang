package com.itteam.yutang.service;

import java.util.ArrayList;
import java.util.List;

import com.itteam.yutang.base.DisPatcher;

import android.content.Context;

public class TangkouManageModel extends DisPatcher{
	Context mContext;
	public TangkouManageModel(Context mContext) {
		this.mContext = mContext;
	}
	public List<String> loadListData(){
		List<String> list = new ArrayList<>();
		list.add("塘口信息查看");
		list.add("塘口信息修改");
		list.add("添加设备");
		return list;
	}
}
