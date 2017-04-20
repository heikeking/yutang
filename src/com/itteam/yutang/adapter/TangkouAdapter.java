package com.itteam.yutang.adapter;

import java.util.List;

import com.itteam.yutang.R;
import com.itteam.yutang.bean.PondBean;
import com.yzh.android.base.MyBaseAdapter;
import com.yzh.android.util.ViewFindUtils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TangkouAdapter extends MyBaseAdapter<PondBean> {
	private Context c;
	private List<PondBean> list;
	private int mRightWidth;
	private IOnItemRightClickListener mListener = null;

    public interface IOnItemRightClickListener {
        void onRightClick(View c, int position);
        void onSettingsClivk(View v,int position);
    }
	public TangkouAdapter(Context c, int resId, List<PondBean> list,int mRightWidth, IOnItemRightClickListener mListener) {
		super(c, resId, list);
		this.c = c;
		this.list = list;
		this.mRightWidth= mRightWidth;
		this.mListener = mListener;
	}

	@Override
	protected void initListCell(int position, View convertView, ViewGroup parent) {
		PondBean pondBean = list.get(position);
		final int thisPosition = position;
		if (pondBean != null) {
			LinearLayout item_left = (LinearLayout) ViewFindUtils.find(convertView, R.id.item_left);
			RelativeLayout item_right = (RelativeLayout) ViewFindUtils.find(convertView, R.id.item_right);
			/*LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			item_left.setLayoutParams(lp1);*/
			LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(mRightWidth, RelativeLayout.LayoutParams.MATCH_PARENT);
	        item_right.setLayoutParams(lp2);
	        ((ImageView)ViewFindUtils.find(convertView, R.id.settings_img)).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 if (mListener != null) {
		                    mListener.onSettingsClivk(v, thisPosition);
		             }
				}
			});; 
			((TextView) ViewFindUtils.find(convertView, R.id.tangkou_name)).setText(pondBean.pondName);
			((TextView) ViewFindUtils.find(convertView, R.id.tangkou_devicecount)).setText("设备："+pondBean.deviceCount);
			((TextView) ViewFindUtils.find(convertView, R.id.tangkou_online)).setText("在线："+pondBean.deviceOnlineCount);
			((TextView) ViewFindUtils.find(convertView, R.id.pondArea)).setText("池塘面积："+pondBean.pondArea);
			((TextView) ViewFindUtils.find(convertView, R.id.pondDepth)).setText("池塘深度："+pondBean.pondDepth);
			((TextView) ViewFindUtils.find(convertView, R.id.pondfishType)).setText("池塘品种："+pondBean.pondArea);// ?
			((TextView) ViewFindUtils.find(convertView, R.id.pondDensity)).setText("投放密度："+pondBean.pondDensity);
			((TextView) ViewFindUtils.find(convertView, R.id.pondUsage)).setText("池塘年龄："+pondBean.pondUsage);
			((TextView) ViewFindUtils.find(convertView, R.id.pondO2_power)).setText("增氧功率："+pondBean.pondO2_power);
			((TextView) ViewFindUtils.find(convertView, R.id.pondtudeepth)).setText("池塘厚度："+pondBean.pondDepth);// ?
			((TextView) ViewFindUtils.find(convertView, R.id.pondWaterSupplies)).setText("池塘水源："+pondBean.pondWaterSupplies);
			((TextView) ViewFindUtils.find(convertView, R.id.pondO2_add)).setText("增氧方式："+pondBean.pondO2_add);
			((TextView) ViewFindUtils.find(convertView, R.id.pondTop)).setText("池塘池顶："+pondBean.pondTop);
			((TextView) ViewFindUtils.find(convertView, R.id.pondBottom)).setText("池塘池底："+pondBean.pondBottom);
			((TextView) ViewFindUtils.find(convertView, R.id.item_right_txt)).setText("删除 ");
			item_right.setOnClickListener(new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                if (mListener != null) {
	                    mListener.onRightClick(v, thisPosition);
	                }
	            }
	        });
		}
	}
	class ViewHolder{
		RelativeLayout item_right;
		LinearLayout item_left;
		TextView tangkou_name;
		TextView tangkou_devicecount;
		TextView tangkou_online;
		TextView pondArea;
		TextView pondDepth;
		TextView pondfishType;
		TextView pondDensity;
		TextView pondUsage;
		TextView pondO2_power;
		TextView pondtudeepth;
		TextView pondWaterSupplies;
		TextView pondO2_add;
		TextView pondTop;
		TextView pondBottom;
		TextView item_right_txt;
	}
}
