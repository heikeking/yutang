package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class EngineStatusView extends FrameLayout {
	private ScaleBitmapView image;
	private TextView name;
	private TextView atrr;

	public EngineStatusView(Context context) {
		super(context);
		init();
	}

	public EngineStatusView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StatusView);
		setLeftDrawable(ta.getDrawable(R.styleable.StatusView_tmpimage));
		setTitleText(ta.getText(R.styleable.StatusView_tmpText));
		//setNum(ta.getFloat(R.styleable.StatusView_tmpnum, 0));
		ta.recycle();
	}


	public void setLeftDrawable(Drawable drawable) {
		image.setImageDrawable(drawable);
	}

	public void setTitleText(CharSequence text) {
		name.setText(text);
	}
	public void setAtrrText(CharSequence text){
		atrr.setText(text);
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_item_enginestatus, this);
		image = (ScaleBitmapView) findViewById(R.id.image);
		name = (TextView) findViewById(R.id.name);
		atrr = (TextView) findViewById(R.id.atrr);
	}

}
