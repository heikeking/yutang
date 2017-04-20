package com.itteam.yutang.activity;

import com.itteam.yutang.base.ISimpleListener;
import com.itteam.yutang.bean.Response_main;
import com.itteam.yutang.bean.WronginfoBean;
import com.itteam.yutang.manager.ModelManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itteam.yutang.R;
import com.yzh.android.MyApplication;
import com.yzh.android.base.BaseFragmentV4;
import com.yzh.android.util.ViewFindUtils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DevicFaultFragment extends BaseFragmentV4 implements ISimpleListener {
	private ModelManager mManager;
	private ListView wronginfolist;
	private List<Map<String, Object>> data_list;
	private SimpleAdapter sim_adapter;
	private TextView wronginfo1;

	public int bindLayout() {

       return R.layout.activity_devic_faulte;
       

   }
	@Override
	public void initView(View view) {
		wronginfolist=(ListView)view.findViewById(R.id.wronginfoList);
		wronginfo1=(TextView)view.findViewById(R.id.wronginfo1);
		
	}

	@Override
	public void doBusiness(Context mContext) {
		mManager = new ModelManager(getContext());
		mManager.addDeviceFaultListener(this);
		String groupId = (String) MyApplication.gainData("groupId");
		String deviceId = (String) MyApplication.gainData("deviceId");
		mManager.getPondInfo(deviceId, groupId);
		
		
     
		
	}

	@Override
	protected void lazyLoad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onFail(int status, Object msg) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSuccess(int status, Object msg) {
		// TODO Auto-generated method stub
	Response_main<WronginfoBean> result = (Response_main<WronginfoBean>) msg;
	data_list = new ArrayList<Map<String, Object>>();
	String [] from ={"text"};
    int [] to = {R.id.wronginfotext};
    sim_adapter = new SimpleAdapter(getContext(), data_list, R.layout.view_wronginfolist, from, to);
    wronginfolist.setAdapter(sim_adapter);
    for(int i=1;i<result.result.size();i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text",result.result.get(i).msg);
            data_list.add(map);
        }

       if(result.result.size()==1){
		wronginfo1.setText(result.result.get(0).msg);
	}
	
		
		return false;
	}
	
	
    
}
