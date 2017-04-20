package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CalandsyncView extends FrameLayout {
	public TextView name;
	public TextView enginesresult;
	public FButton startsync1;

	public CalandsyncView(Context context) {
		super(context);
		init();
	}

	public CalandsyncView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
		/*TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StatusView);
		setLeftDrawable(ta.getDrawable(R.styleable.StatusView_tmpimage));
		setTitleText(ta.getText(R.styleable.StatusView_tmpText));
		setNum(ta.getFloat(R.styleable.StatusView_tmpnum, 0));
		ta.recycle();*/
	}
	
	public void setName(CharSequence txt){
		name.setText(txt);
	}
	public void setEnginesresult(CharSequence txt){
		enginesresult.setText(txt);
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_syncandcaldata, this);
		name = (TextView) findViewById(R.id.name);
		enginesresult = (TextView) findViewById(R.id.enginesresult);
		startsync1 =(FButton) findViewById(R.id.startsync1);
	}
}
