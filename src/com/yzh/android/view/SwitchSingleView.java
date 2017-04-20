package com.yzh.android.view;

import com.itteam.yutang.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SwitchSingleView  extends FrameLayout {
	public TextView name;
	public ClickSwitchButton switchButton;

	public SwitchSingleView(Context context) {
		super(context);
		init();
	}

	public SwitchSingleView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
		/*TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StatusView);
		setLeftDrawable(ta.getDrawable(R.styleable.StatusView_tmpimage));
		setTitleText(ta.getText(R.styleable.StatusView_tmpText));
		setNum(ta.getFloat(R.styleable.StatusView_tmpnum, 0));
		ta.recycle();*/
	}
	
	public void setName(CharSequence txt){
		switchButton.setText(txt);
	}
	public void setSwitchStatus(boolean isSwitch){
		switchButton.setChecked(isSwitch);
	}
	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_item_switchsingleview, this);
		switchButton = (ClickSwitchButton) findViewById(R.id.switchButton);
	}

}
