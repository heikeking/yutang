package com.yzh.android.view;

import com.itteam.yutang.R;
import com.yzh.spinnerview.NiceSpinner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SetenginesTimeView  extends FrameLayout {
	public NiceSpinner setengines_tv_time;
	public TextView name;
	public TextView name_duliang;

	public SetenginesTimeView(Context context) {
		super(context);
		init();
	}

	public SetenginesTimeView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
		/*TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StatusView);
		setLeftDrawable(ta.getDrawable(R.styleable.StatusView_tmpimage));
		setTitleText(ta.getText(R.styleable.StatusView_tmpText));
		setNum(ta.getFloat(R.styleable.StatusView_tmpnum, 0));
		ta.recycle();*/
	}
	
	public String getTextViewString(){
		return (String) setengines_tv_time.getText();
	}
	public TextView getEditText(){
		return setengines_tv_time;
	}
	public void setHintText(CharSequence txt){
		setengines_tv_time.setText(txt);;
	}
	public void setName(CharSequence txt){
		name.setText(txt);
	}
	public void setName_duliang(CharSequence txt){
		name_duliang.setText(txt);
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_item_setengins_time, this);
		setengines_tv_time = (NiceSpinner) findViewById(R.id.setengines_tv_time);
		name = (TextView) findViewById(R.id.name);
		name_duliang = (TextView) findViewById(R.id.name_duliang);
	}

}
